package top.vx520.pojo;

import lombok.Data;

@Data
public class Withdrawal {
    private int id;//提现编号
    private String data;//提现日期
    private double money;//提现金额
    private int uId;//提现用户编号
    private int state;//提现状态 0在申请 1成功 2驳回
    private String payAccount;//支付宝账号
    private String name;
}
