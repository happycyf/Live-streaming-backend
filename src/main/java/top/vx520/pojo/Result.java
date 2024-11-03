package top.vx520.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//json响应类
@NoArgsConstructor//自动构造无参构造方法
@AllArgsConstructor//自动构造全参构造方法
@Data
public class Result<T> {
    private Integer code;//业务状态码 0-成功 1-失败
    private String message;//提示信息
    private T data;//响应数据

    //快速返回操作成功响应结果(带响应数据)
    public static <E> Result<E> success(E data){
        return new Result<>(0,"操作成功",data);
    }
    //快速返回操作成功响应结果
    public static Result success(){
        return new Result(0,"操作成功",null);
    }
    public static Result error(String message){
        return new Result(1,message,null);
    }

}
