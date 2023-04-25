package com.joji.taowu.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joji.taowu.common.client.ProductClient;
import com.joji.taowu.common.entity.Order;
import com.joji.taowu.common.entity.Product;
import com.joji.taowu.common.param.OrderParam;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.param.ProductListParam;
import com.joji.taowu.common.param.ProductNumberParam;
import com.joji.taowu.common.utils.R;
import com.joji.taowu.common.vo.AdminOrderVO;
import com.joji.taowu.common.vo.CartVO;
import com.joji.taowu.common.vo.OrderVO;
import com.joji.taowu.order.mapper.OrderMapper;
import com.joji.taowu.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 订单业务实现层
 * */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ProductClient productClient;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    @Transactional
    public Object save(OrderParam orderParam) {
        //修改清空购物车的参数
        List<Integer> cartIds = new ArrayList<>();
        //修改批量插入数据库的参数
        List<Order>  orderList = new ArrayList<>();
        //商品修改库存参数集合
        List<ProductNumberParam>  productNumberParamList  =
                new ArrayList<>();

        Integer userId = orderParam.getUserId();
        List<CartVO> products = orderParam.getProducts();
        //封装order实体类集合
        //统一生成订单编号和创建时间
        //使用时间戳 + 做订单编号和事件
        long ctime = System.currentTimeMillis();

        for (CartVO cartVo : products) {
            cartIds.add(cartVo.getId()); //进行购物车订单保存
            //订单信息保存
            Order order = new Order();
            order.setOrderId(ctime);
            order.setUserId(userId);
            order.setOrderTime(ctime);
            order.setProductId(cartVo.getProductID());
            order.setProductNum(cartVo.getNum());
            order.setProductPrice(cartVo.getPrice());
            orderList.add(order); //添加用户信息

            //修改信息存储
            ProductNumberParam productNumberParam = new ProductNumberParam();
            productNumberParam.setProductId(cartVo.getProductID());
            productNumberParam.setProductNum(cartVo.getNum());
            productNumberParamList.add(productNumberParam); //添加集合
        }
        //批量数据插入
        this.saveBatch(orderList); //批量保存

        //修改商品库存 [product-service] [异步通知]
        /**
         *  交换机: topic.ex
         *  routingkey: sub.number
         *  消息: 商品id和减库存数据集合
         */
        rabbitTemplate.convertAndSend("topic.ex","sub.number",productNumberParamList);
        //清空对应购物车数据即可 [注意: 不是清空用户所有的购物车数据] [cart-service] [异步通知]
        /**
         * 交换机:topic.ex
         * routingkey: clear.cart
         * 消息: 要清空的购物车id集合
         */
        rabbitTemplate.convertAndSend("topic.ex","clear.cart",cartIds);

        R ok = R.ok("订单生成成功!");
        log.info("OrderServiceImpl.save业务结束，结果:{}",ok);
        return ok;
    }

    @Override
    public Object list(OrderParam orderParam) {
        Integer userId = orderParam.getUserId();
        //查询用户对应的全部订单数据
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("user_id",userId);
        List<Order> orderList = this.list(orderQueryWrapper);

        Set<Integer> productIds = new HashSet<>();
        for (Order order : orderList) {
            productIds.add(order.getProductId());
        }


        //数据按订单分组
        Map<Long, List<Order>> listMap = orderList.stream().
                collect(Collectors.groupingBy(Order::getOrderId));

        //结果集封装,返回即可
        ProductListParam productListParam = new ProductListParam();
        productListParam.setProductList(new ArrayList<>(productIds));

        List<Product> productList = productClient.ids(productListParam);
        //商品数据
        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));

        //结果封装
        List<List<OrderVO>> result = new ArrayList<>();

        for (List<Order> orders : listMap.values()) {
            List<OrderVO> orderVos = new ArrayList<>();
            for (Order order : orders) {
                //返回vo数据封装
                OrderVO orderVo = new OrderVO();
                Product product = productMap.get(order.getProductId());
                orderVo.setProductName(product.getProductName());
                orderVo.setProductPicture(product.getProductPicture());
                orderVo.setId(order.getId());
                orderVo.setOrderId(order.getOrderId());
                orderVo.setOrderTime(order.getOrderTime());
                orderVo.setProductNum(order.getProductNum());
                orderVo.setProductId(order.getProductId());
                orderVo.setProductPrice(order.getProductPrice());
                orderVo.setUserId(order.getUserId());
                orderVos.add(orderVo);
            }
            result.add(orderVos);
        }

        R ok = R.ok(result);
        log.info("OrderServiceImpl.list业务结束，结果:{}",ok);
        return ok;
    }

    @Override
    public Object check(Integer productId) {
        Order order = orderMapper.checkOrder(productId);

        if (order == null){

            return R.ok("订单中不存在要删除的商品!");
        }

        return R.fail("订单中存在要删除的商品,删除失败!");
    }

    @Override
    public Object adminList(PageParam pageParam) {
        int offset = (pageParam.getCurrentPage()-1)*pageParam.getPageSize();
        int number = pageParam.getPageSize();

        //查询数量
        Long total = orderMapper.selectCount(null);
        //自定义查询
        List<AdminOrderVO> adminOrderVoList = orderMapper.selectAdminOrders(offset,number);


        return R.ok("查询成功",adminOrderVoList,total);
    }
}
