package top.vx520.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.vx520.mapper.UserMapper;
import top.vx520.mapper.getName;
import top.vx520.pojo.AchorPojo;
import top.vx520.pojo.LoginPojo;
import top.vx520.pojo.UserAttention;
import top.vx520.webSocket.LiveWebSocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private getName nameMapper;


    @RequestMapping("/index")
    public Map<String, AchorPojo> getAuchor(){
        return LiveWebSocket.anchorMap;

    }
    @RequestMapping("/getImgUrl")
    public String getImgUrl(String account){
        LoginPojo loginPojo=new LoginPojo();
        loginPojo.setAccount(account);
        return "http://localhost:8080/avatarImg/"+mapper.getImgSrc(loginPojo);
    }
    @RequestMapping("/getName")
    public  String getName(String account){
       String name = nameMapper.getName(account);
        return name;
    }
    @RequestMapping("/addgiftrecord")
    @Transactional
    public int addgiftrecord(String giftName,int uId,String account){
            nameMapper.setUserattentionMoney(account,uId,giftName);
            nameMapper.upgift(uId, giftName);
            nameMapper.addBlance(account,giftName);
        return nameMapper.add(giftName, uId, account);
    }
    @RequestMapping("/adduserattention")
    public void adduserattention(int uId,String account){
        nameMapper.adduserattention(account, uId);
    }
    @RequestMapping("/getfan")
    public  int getFan(String account){

        int fen = nameMapper.fan(account);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++"+fen);
        return fen;
    }
    @RequestMapping("/deluserattention")
    public  int deluserattention(int uId ,String account){
        return  nameMapper.deluserattention(uId,account);

    }
    @RequestMapping("/chaxuuserattention")
    public  int chaxuuserattention(int uId,String account){
        UserAttention i = nameMapper.chaxuuserattention(uId, account);
        if(i!=null){
            return 1;
        }
        return 0;
    }
}
