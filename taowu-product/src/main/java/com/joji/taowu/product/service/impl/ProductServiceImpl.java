package com.joji.taowu.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joji.taowu.common.client.*;
import com.joji.taowu.common.entity.Category;
import com.joji.taowu.common.entity.Picture;
import com.joji.taowu.common.entity.Product;
import com.joji.taowu.common.param.*;
import com.joji.taowu.common.utils.R;
import com.joji.taowu.product.mapper.PictureMapper;
import com.joji.taowu.product.mapper.ProductMapper;
import com.joji.taowu.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 淘物商城商品业务实现层
 */
@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private PictureMapper pictureMapper;

    @Resource
    private SearchClient searchClient;

    //导入客户端
    @Resource
    private CategoryClient categoryClient;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private OrderClient orderClient;

    @Resource
    private CartClient cartClient;

    @Resource
    private WishlistClient wishlistClient;

    /**
     * 类别名称,查询商品集合,最多查询7条
     *
     * @param categoryName
     * @return
     */
    @Cacheable(value = "list.product", key = "#categoryName")
    @Override
    public Object promo(String categoryName) {
        //调用category-service,查询名称对应的类别信息
        Category category = categoryClient.detail(categoryName);
        //根据类别id查询商品集合,最多查询7条!
        if (category == null) {
            return R.fail("查询失败");
        }
        Integer categoryId = category.getCategoryId();

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1, 7);

        IPage<Product> iPage = productMapper.selectPage(page, queryWrapper);
        //结果封装
        List<Product> records = iPage.getRecords();
        long total = iPage.getTotal();
        System.out.println("total = " + total);

        log.info("ProductServiceImpl.promo业务结束，结果:{}", records);
        return R.ok(records);
    }


    /**
     * 热门商品查询,最多查询7条
     *
     * @param productCategoryParam
     * @return
     */
    @Cacheable(value = "list.product", key = "#productCategoryParam.categoryName")
    @Override
    public Object hots(ProductCategoryParam productCategoryParam) {
        //调用category-service,查询名称对应的类别id
        List<Integer> ids = categoryClient.names(productCategoryParam);

        //根据类别id查询商品集合,最多查询7条!
        if (ids == null || ids.size() == 0) {
            return R.fail("查询失败");
        }

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id", ids);
        queryWrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1, 7);

        IPage<Product> iPage = productMapper.selectPage(page, queryWrapper);
        //结果封装
        List<Product> records = iPage.getRecords();
        long total = iPage.getTotal();
        System.out.println("total = " + total);

        log.info("ProductServiceImpl.hots业务结束，结果:{}", records);
        return R.ok(records);
    }

    @Override
    @Cacheable(value = "list.category", key = "#root.methodName")
    public Object clist() {
        //1.查询类别数据集合 调用类别服务
        List<Category> list = categoryClient.list();
        //2.结果封装
        R data = R.ok(list);

        log.info("ProductServiceImpl.clist业务结束，结果:{}", data);

        return data;
    }


    /**
     * 类别商品查询 前端传递类别集合
     *
     * @param productParam
     * @return
     */
    @Cacheable(value = "list.product", key =
            "#productParam.categoryID+" +
                    "'-'+#productParam.currentPage+" +
                    "'-'+#productParam.pageSize")
    @Override
    public Object byCategory(ProductParam productParam) {
        //1.拆分请求参数
        List<Integer> categoryID = productParam.getCategoryID();
        int currentPage = productParam.getCurrentPage();
        int pageSize = productParam.getPageSize();
        //2.请求条件封装
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        if (categoryID != null && categoryID.size() > 0) {
            productQueryWrapper.in("category_id", categoryID);
        }
        IPage<Product> page = new Page<>(currentPage, pageSize);
        //3.数据查询
        IPage<Product> iPage = productMapper.selectPage(page, productQueryWrapper);
        //4.结果封装
        List<Product> productList = iPage.getRecords();
        long total = iPage.getTotal();

        R ok = R.ok(null, productList, total);

        log.info("ProductServiceImpl.byCategory业务结束，结果:{}", ok);
        return ok;
    }


    /**
     * 全部商品查询,可以进行类别集合数据查询业务复用
     *
     * @param productParam
     * @return
     */
    @Cacheable(value = "list.product", key = "#productParam.currentPage+" +
            "'-'+#productParam.pageSize")
    @Override
    public Object all(ProductParam productParam) {
        return byCategory(productParam);
    }

    /**
     * 查询商品详情
     *
     * @param productID 商品id
     * @return
     */
    @Override
    public Object detail(Integer productID) {
        Product product = productMapper.selectById(productID);

        R ok = R.ok(product);

        log.info("ProductServiceImpl.detail业务结束，结果:{}", ok);

        return ok;
    }

    /**
     * 查询商品图片
     *
     * @param productID
     * @return
     */
    @Cacheable(value = "picture", key = "#productID")
    @Override
    public Object pictures(Integer productID) {
        //参数封装
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productID);
        //数据库查询
        List<Picture> pictureList = pictureMapper.selectList(queryWrapper);
        //结果封装
        R r = R.ok(pictureList);

        log.info("ProductServiceImpl.pictures业务结束，结果:{}", r);

        return r;
    }

    @Override
    public List<Product> list() {
        List<Product> products = productMapper.selectList(null);

        return products;
    }

    /**
     * 关键字商品搜索
     *
     * @param productSearchParam
     * @return
     */
    @Cacheable(value = "list.product", key = "#productSearchParam.search+'-'+#productSearchParam.pageSize+'-'+#productSearchParam.currentPage")
    @Override
    public Object search(ProductSearchParam productSearchParam) {
        R r = searchClient.search(productSearchParam);

        log.info("ProductServiceImpl.search业务结束，结果:{}", r);
        return r;
    }

    /**
     * 查询商品集合
     *
     * @param productListParam
     * @return
     */
    @Cacheable(value = "list.product", key = "#productListParam.productIds")
    @Override
    public List<Product> ids(ProductListParam productListParam) {
        List<Integer> productIds = productListParam.getProductList();

        QueryWrapper<Product> queryWrapper =
                new QueryWrapper<>();
        queryWrapper.in("product_id", productIds);

        List<Product> productList = productMapper.selectList(queryWrapper);

        return productList;
    }

    /**
     * 修改商品库存
     *
     * @param productNumberParams
     */
    @Transactional
    @Override
    public void batchNumber(List<ProductNumberParam> productNumberParams) {
        //将productNumberParams转成map
        //使用id作为key, item做值, 比较相邻的两次key,如果相同,去掉重读!
        Map<Integer, ProductNumberParam> productNumberParamMap = productNumberParams.stream()
                .collect(Collectors.toMap(ProductNumberParam::getProductId, v -> v));

        //封装商品集合
        Set<Integer> productIds = productNumberParamMap.keySet();

        //查询
        List<Product> productList = baseMapper.selectBatchIds(productIds);
        //修改

        for (Product product : productList) {
            //设置新库存
            product.setProductNum(product.getProductNum() -
                    productNumberParamMap.get(product.getProductId()).getProductNum());
            //设置销售量
            product.setProductSales(product.getProductSales() +
                    productNumberParamMap.get(product.getProductId()).getProductNum());
        }

        //批量数据更新
        this.updateBatchById(productList);
    }

    @Override
    public Long categoryCount(Integer categoryId) {
        QueryWrapper<Product> queryWrapper =
                new QueryWrapper<>();

        queryWrapper.eq("category_id", categoryId);

        Long count = productMapper.selectCount(queryWrapper);

        log.info("ProductServiceImpl.categoryCount业务结束，结果:{}", count);
        return count;
    }

    @Transactional
    @Override
    public boolean save(Product product) {
        //商品数据保存
        int rows = productMapper.insert(product);

        if (rows == 0) {
            return false;
        }

        //保存成功,进行发送消息,product插入到es库中
        rabbitTemplate.convertAndSend("topic.ex", "insert.product", product);
        return true;
    }

    @Override
    public R update(Product product) {
        int rows = baseMapper.updateById(product);

        if (rows == 0) {
            return R.fail("商品数据更新失败!");
        }
        //es更新就是插入覆盖即可~
        rabbitTemplate.convertAndSend("topic.ex",
                "insert.product", product);

        return R.ok("商品数据更新成功!");
    }

    /**
     * 移除商品信息
     * 1.检查购物车是否存在  存在 不删除
     * 2.检查账单是否存在    存在 不删除
     * 3.删除商品数据 和 删除商品对应的图片详情
     * 3.检查收藏夹是否存在  删除 收藏夹商品
     * 4.通知es搜索服务删除
     * @param product
     * @return
     */
    @Transactional
    @Override
    public R remove(Product product) {
        int productId = product.getProductId();
        //1.检查购物车是否存在
        R r = cartClient.checkProduct(productId);

        if ("004".equals(r.getCode())){
            log.info("ProductServiceImpl.remove结束,{}",r.getMsg());
            return r;
        }

        //2.简单账单是否存在
        R or = orderClient.checkProduct(productId);
        if ("004".equals(or.getCode())){
            log.info("ProductServiceImpl.remove结束,{}",or.getMsg());
            return or;
        }

        //删除商品图片详情
        QueryWrapper<Picture> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id",productId);
        pictureMapper.delete(wrapper);
        //删除收藏中的商品
        wishlistClient.removeByPID(productId);

        //3.删除商品数据 和 删除商品对应的图片数据
        int rows = productMapper.deleteById(productId);

        if (rows == 0 ){
            log.info("ProductServiceImpl.remove业务结束，结果:{}","商品删除失败!");
            return R.fail("商品删除失败!");
        }


        //删除es缓存中对应商品的数据
        rabbitTemplate.convertAndSend("topic.ex","delete.product",productId);


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        R ok = R.ok("商品数据删除成功!");
        log.info("ProductServiceImpl.remove业务结束，结果:{}",ok);
        return ok;
    }
}
