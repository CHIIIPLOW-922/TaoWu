package com.joji.taowu.admin.controller;

/**
 * 【建议拷贝到本地环境编写后贴回考试系统，考试结束会自动收卷，请自行定时将代码拷回到系统中】
 *
 * 业务背景：这是一个可以售卖多种类目商品（酒店、门票、签证）的旅游类电商系统。 这个类中主要是接收到订单状态变更消息的逻辑处理。目前有订单支付（这里的支付是直接扣款，有免密协议）和订单完成两部分逻辑。
 *
 * 题目要求：希望你能够将这段代码进行重构，目标：拥有好的可读性（清晰、直观、简洁）、可扩展性（面向未来商品类的扩展、订单的状态扩展、公共逻辑扩展（比如增加入参、出参日志打印和异常控制逻辑加入））
 * 题目判分的优先级： 设计思路 > 代码结构 > 规范性 > 可读性 > 完整度
 * 参考建议：
 *  1. 可读性：
 *     1）代码的注释、命名
 *     2）枚举类
 *     3）重复代码归纳合并
 *  2. 扩展性考虑：
 *     1）设计模式 工厂、模板方法
 *     2）事件驱动方式 比如 SpringEvent 或者 EventBus。没有用过可以上网查哦。（代码中体现出结构和大概逻辑即可） 
 */
public class OrderStatusMQListener {
    private OrderService orderService = new OrderService();
    private NotifyService notifyService = new NotifyService();

    public void consume(String orderNo) {
        OrderInfo orderInfo = orderService.getOrderInfo(orderNo);
        OrderType orderType = OrderType.fromString(orderInfo.getOrderBizType());
        OrderStatus status = OrderStatus.fromCode(orderInfo.getStatus());

        if (status == OrderStatus.CREATED) {
            PaymentService paymentService = PaymentServiceFactory.createPaymentService(orderType);
            boolean paymentSuccess = paymentService.pay(orderInfo);

            if (!paymentSuccess) {
                paymentService.handlePaymentFailure(orderInfo);
            }
        } else if (status == OrderStatus.COMPLETED) {
            orderService.updateOrderStatus(orderNo, OrderStatus.COMPLETED.getCode());
            notifyService.notifyUsersAndOperations(orderType);
        } else if (status == OrderStatus.REFUND_APPROVED) {
            // 处理退款逻辑...
        }
        // 其他订单状态的处理...
    }
}

enum OrderType {
    HOTEL, TICKETS, VISA;

    public static OrderType fromString(String type) {
        switch (type.toLowerCase()) {
            case "hotel":
                return HOTEL;
            case "tickets":
                return TICKETS;
            case "visa":
                return VISA;
            default:
                throw new IllegalArgumentException("Unknown order business type: " + type);
        }
    }
}

enum OrderStatus {
    CREATED(1), COMPLETED(4), REFUND_APPROVED(9);

    private final int code;

    OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderStatus fromCode(int code) {
        for (OrderStatus status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown order status code: " + code);
    }
}

interface PaymentService {
    boolean pay(OrderInfo orderInfo);

    void handlePaymentFailure(OrderInfo orderInfo);
}

class HotelPaymentService implements PaymentService {
    @Override
    public boolean pay(OrderInfo orderInfo) {
        // 酒店订单支付逻辑...
        return true; // 支付成功返回true，失败返回false
    }

    @Override
    public void handlePaymentFailure(OrderInfo orderInfo) {
        // 酒店订单未支付关闭逻辑...
    }
}

class TicketsPaymentService implements PaymentService {
    @Override
    public boolean pay(OrderInfo orderInfo) {
        // 门票订单支付逻辑...
        return true; // 支付成功返回true，失败返回false
    }

    @Override
    public void handlePaymentFailure(OrderInfo orderInfo) {
        // 门票订单未支付关闭逻辑...
    }
}

class VisaPaymentService implements PaymentService {
    @Override
    public boolean pay(OrderInfo orderInfo) {
        // 签证订单支付逻辑...
        return true; // 支付成功返回true，失败返回false
    }

    @Override
    public void handlePaymentFailure(OrderInfo orderInfo) {
        // 签证订单未支付关闭逻辑...
    }
}

class PaymentServiceFactory {
    public static PaymentService createPaymentService(OrderType orderType) {
        switch (orderType) {
            case HOTEL:
                return new HotelPaymentService();
            case TICKETS:
                return new TicketsPaymentService();
            case VISA:
                return new VisaPaymentService();
            default:
                throw new IllegalArgumentException("Unsupported order business type: " + orderType);
        }
    }
}
// -------------------- 方便候选人本地阅读代码，↓↓↓↓↓↓↓不需要动 --------------------


/**
 * 订单服务类 (方便候选人本地阅读代码, 不需要动)
 */
class OrderService {
    OrderInfo getOrderInfo(String orderNo) {
        return new OrderInfo();
    }

    void updateOrderStatus(String orderNo, int status) {
        // 更新订单逻辑
    }

}

/**
 * 支付服务类 (方便候选人本地阅读代码, 不需要动)
 */
class PayService {

    /**
     * 信用支付
     * @param orderInfo
     */
    long payOfCredit(OrderInfo orderInfo) {
        //  信用支付逻辑，候选人不用理会支付内部逻辑 ...
        return 1;
    }

    /**
     * 支付宝支付
     * @param orderInfo
     */
    long payOfAliPay(OrderInfo orderInfo) {
        //  支付宝支付逻辑，候选人不用理会支付内部逻辑 ...
        return 1;
    }

    /**
     * 未支付成功后关闭
     * @param orderInfo
     */
    public void notPayedToClose(OrderInfo orderInfo) {
        //  支付宝支付逻辑，候选人不用理会支付内部逻辑 ...
    }
}

class NotifyService {
    /**
     * 短信通知
     */
    void sms(String msg) {}
    /**
     * 钉钉通知
     */
    void dingding(String msg) {}
    /**
     * 微信通知
     */
    void wechat(String msg) {}
    /**
     * 邮件通知
     */
    void email(String msg) {}
}


/**
 * 订单类 (方便候选人本地阅读代码, 不需要动)
 */
class OrderInfo {
    private String orderNo;
    private String orderBizType;
    private int status;
    private String anything;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderBizType() {
        return orderBizType;
    }

    public void setOrderBizType(String orderBizType) {
        this.orderBizType = orderBizType;
    }

    public String getAnything() {
        return anything;
    }

    public void setAnything(String anything) {
        this.anything = anything;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
// -------------------- 方便候选人本地阅读代码，↑↑↑↑↑↑不需要动 --------------------