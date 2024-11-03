package top.vx520.pojo;

import lombok.Data;

//礼物记录表实体类
@Data
public class GiftRecord {

    private int giftId;//礼物编号
    private int sendId;//发送用户编号
    private int reId;//接收用户编号
    private String sendTime;//送出时间
}
