package top.vx520.pojo;

import lombok.Data;

//用户表实体类
@Data
public class User {

    private int uId;//用户编号
    private String uName;//用户名称
    private String account;//账号
    private String pwd;//密码
    private String img;//头像
    private int attention;//关注数
    private int fan;//粉丝数
    private double blance;//余额
    private String phone;//手机号
    private char sex;//性别
    private String adress;//地址
    private int uTypeId;//用户类型(比如说管理员和普通用户)
    private String tKey;//主播推流的key

}
