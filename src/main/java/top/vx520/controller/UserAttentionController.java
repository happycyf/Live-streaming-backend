package top.vx520.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.vx520.pojo.Result;
import top.vx520.pojo.User;
import top.vx520.service.UserAttentionService;
import top.vx520.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/userAttention")
@CrossOrigin(origins = "*")
public class UserAttentionController {

    @Autowired
    UserAttentionService userAttentionService;
    @Autowired
    UserService userService;

    @RequestMapping("/findAll")
    public Result findAll(int uId){
        List<User> list = userAttentionService.findAll(uId);
        return Result.success(list);
    }


    @RequestMapping("/delAttention")
    public Result delAttention(int userUid,int followUid){
        int i = userAttentionService.delAttention(userUid,followUid);
        int j = userService.reduceFan(followUid);
        int z = userService.reduceAttention(userUid);
        if (i>0&&j>0&&z>0){
            return Result.success();
        }
        return Result.error("取关失败");
    }







}
