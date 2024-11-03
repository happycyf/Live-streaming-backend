package top.vx520.pojo;

import lombok.Data;

@Data
public class UserAttention {

    private int userUid;//用户编号
    private int followUid;//被关注的用户编号
    private double monetary;//消费金额

}
