package top.vx520.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.vx520.service.OrderService;
import top.vx520.service.UserService;
import top.vx520.until.RandomNum;
import top.vx520.until.ZFB;

@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;


    @ResponseBody
    @RequestMapping("/zfb")
    public String zfb(int uId,double price){
        boolean flag=true;
        String  no= RandomNum.getRanNum(6)+""+RandomNum.getRanNum(9);
        return ZFB.SendZFB(uId,no, price, "造梦工厂金币");
    }
    @RequestMapping("/ZfSuccess")
    public String  ZfSuccess(int uId,double price){
        //添加订单
        orderService.add(uId,price);
        //给用户价钱
        userService.addMoney(uId,price);
        return "redirect:http://localhost:3000/psl";
    }

}
