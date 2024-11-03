package top.vx520.pojo;

import lombok.Data;

//订单表实体类
@Data
public class Order {

    private int orderId;//订单编号
    private int uid;//用户编号
    private double money;//订单金额
    private String orderTime;//订单时间


}
