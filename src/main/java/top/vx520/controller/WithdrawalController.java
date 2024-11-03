package top.vx520.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.vx520.pojo.Result;
import top.vx520.pojo.Withdrawal;
import top.vx520.service.WithdrawalService;

import java.util.List;

@RestController
@RequestMapping("/with")
public class WithdrawalController {

    @Autowired
    WithdrawalService withdrawalService;


    @RequestMapping("/findAll")
    public Result findAll(){
        List<Withdrawal> list = withdrawalService.findAll();
        return Result.success(list);
    }


    @RequestMapping("/upState")
    public Result upState(int id,int state){
        int i = withdrawalService.upState(id, state);
        if (i>0){
            return Result.success();
        }
        return Result.error("修改失败");
    }



}
