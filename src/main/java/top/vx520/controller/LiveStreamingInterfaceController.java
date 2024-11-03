package top.vx520.controller;


import com.mysql.cj.log.Log;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.vx520.pojo.*;
import top.vx520.service.LiveStreamingInterfaceService;
import top.vx520.webSocket.LiveWebSocket;

import java.util.*;

@Controller
//直播界面控制层
@RequestMapping("/live")
public class LiveStreamingInterfaceController {
    @Autowired
    private LiveStreamingInterfaceService service;
    @Autowired
    private LiveWebSocket liveWebSocket;

    /**
     * 从session里获取粉丝数和主播链接
     * @param session 拿取账号
     * @return 返回粉丝数 和ukey
     */
    @RequestMapping("/getUserFanAndUKey")
    @ResponseBody
    public Map<String,String> getUserFanAndUkey(HttpSession session) {
        return service.getUserFan(session);
    }

    /**
     * 获取主播信息
     * @param session 获取主播用户信息
     * @return 返回用户信息
     */
    @RequestMapping("/getUserInformation")
    @ResponseBody
    public UserInformationPojo getUserInformation(HttpSession session){
        return service.getUserInformation(session);
    }
    /**
     * 获取粉丝前一百位
     * @param session 拿取账号
     * @return 返回粉丝榜前一百位名称及金额
     */
    @RequestMapping("/getFenSi")
    @ResponseBody
    public List<UserFenSiPojo> getFenSi(HttpSession session) {
        return service.getUserFenSi(session);
    }

    /**
     * 获取今日收益榜
     * @param session
     * @return 用户名 及用户总金额
     */
    @RequestMapping("/getTodayMoneyBang")
    @ResponseBody
    public List<JinRiLiWuShouYiPojo> getTodayMoneyBang(HttpSession session) {
        return  service.getTodayMoneyBang(session);
    }

    /**
     * 获得账户今日收益月收益余额涨幅情况
     * @param session 请求账号
     * @return 查询结果
     */
    @RequestMapping("/getIncomeData")
    @ResponseBody
    public Map<String,String> getIncomeData(HttpSession session) {
        return  service.getIncomeData(session);
    }

    /**
     * 发送提现验证码
     * @param session 提现验证码手机号
     * @return 发送结果 code 200为成功 其他为失败
     */
    @RequestMapping("/getWithdrawalCode")
    @ResponseBody
    public Map<String,String> getWithdrawalCode(HttpSession session) {
        return  service.getWithdrawalCode(session);
    }

    /**
     * 进行提现
     * @param money 提现金额
     * @param code 验证码
     * @param payAccount 支付宝账号
     * @param session 存储账号位置
     * @return 是否提现成功
     */
    @RequestMapping("/SendWithdrawal")
    @ResponseBody
    public Map<String,String> SendWithdrawal( double money,String code,String payAccount,HttpSession session) {
        return service.SendWithdrawal(money,code,payAccount,session);
    }
    @RequestMapping("/getWithdrawalData")
    @ResponseBody
    public Map<String,List<WithdrawalPojo>> getWithdrawalList(HttpSession session) {
        return service.getWithdrawalList(session);
    }

    /**
     * 获取主播收益主播峰值
     * @param session 主播账号
     * @return 开播峰值 开播时长 粉丝数 月收益 粉丝总榜前一百位
     */
    @RequestMapping("/getAnchorData")
    @ResponseBody
    public Map getAnchorData(HttpSession session) {
       return service.getAnchorData(session);
    }

    /**
     * 获取粉丝总榜
     * @param session 获得账号
     * @return 返回礼物对象其中包含 头像地址 用户昵称 用户发送的总金额
     */
    @RequestMapping("/getAllGiftMoney")
    @ResponseBody
    public List<AllGiftPojo> getAllGiftMoney(HttpSession session) {
        return service.getAllGiftMoney(session);
    }

    /**
     * 修改密码验证码发送
     * @param phone 手机号
     * @param session 账号及验证码存储
     * @return 返回发送结果
     */
    @RequestMapping("/sendSetPwdYzm")
    @ResponseBody
    public Map<String,String> sendSetPwdYzm(String phone,HttpSession session) {
        return service.sendSetPwdYzm(phone,session);
    }

    /**
     * 修改密码验证码
     * @param phone 手机号
     * @param session 验证码
     * @return 结果 200为成功 ,500失败
     */
    @RequestMapping("/codePhone")
    @ResponseBody
    public int codePhone(String phone,String Yzm,HttpSession session) {
        return service.codePhone(phone,Yzm,session);
    }

    /**
     * 修改密码
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param session 拿取用户id
     * @return 结果json
     */
    @RequestMapping("/setPwd")
    @ResponseBody
    public Map<String,String> setPwd(String oldPwd,String newPwd,HttpSession session) {
        return service.setPwd(oldPwd, newPwd, session);

    }

    /**
     * 开播基本信息上传
     * @param pojo 基本信息 包含标题 描述 封面 直播key
     * @param file 封面文件
     * @param session 存储的信息
     * @return
     */
    @PostMapping("/uploadAnchorMap")
    @ResponseBody
    @Transactional
    public boolean uplodAnchorMap(AchorPojo pojo,@RequestParam("file") MultipartFile file,HttpSession session){
        String path =service.uploadCover(file);
//        上传文件就失败那就直接返回上传错误
        if(path==null){
            return false;
        }
//        修改为新地址名字
        pojo.setCoverName(path);
        LoginPojo login = (LoginPojo)session.getAttribute("login");

//        LoginPojo login =new LogsinPojo();
//        login.setAccount("admin");
        System.out.println(login);
        if(login==null||login.getAccount()==null){
            return false;
        }

//        通过账号保存主播信息
        LiveWebSocket.anchorMap.put(login.getAccount(), pojo);
        System.out.println(LiveWebSocket.anchorMap);
        return true;
    }

    /**
     * 获取主播基本信息
     * @param session 信息存放处
     * @return 返回主播账号 昵称 发送日期
     */
    @RequestMapping("getLoginPojo")
    @ResponseBody
    public SendChatUserPojo getLoginPojo(HttpSession session){
        LoginPojo login = (LoginPojo) session.getAttribute("login");
//        LoginPojo login=new LoginPojo();
//        login.setAccount("admin");
//        login.setuName("123");
        Date date =new Date();
//        发送用户
        SendChatUserPojo scup=new SendChatUserPojo(login.getuName(), login.getAccount(), date);
        return scup;
    }

    /**
     * 进行最后时间上传
     * @param liveTitle 直播标题
     * @param startTime 开启时间
     * @param overTime 结束时间
     * @param session 获取用户账号
     */
    @RequestMapping("/addLiveTime")
    @ResponseBody
    public void  addLiveTime(String liveTitle,String startTime,String overTime,HttpSession session){
        LoginPojo login = (LoginPojo) session.getAttribute("login");
//        LoginPojo login=new LoginPojo();
//        login.setAccount("admin");
//        通过账号查找其uid
        String account = login.getAccount();

        service.addLiveTime(account,liveTitle,startTime,overTime);
    }

    /**
     * 获取主播的今日峰值人气。直播总时长，粉丝数
     * @param session 获取主播id
     * @return 今日峰值人气。今日直播总时长，粉丝数
     */
    @RequestMapping("/getAnchorBasic")
    @ResponseBody
    public Map<String,Integer> getAnchorBasic(HttpSession session){
        LoginPojo login = (LoginPojo) session.getAttribute("login");
//        LoginPojo login =new LoginPojo();
//        login.setAccount("admin");
        return service.getAnchorBasic(login);
    }

    /**
     * 修改主播信息
     * @param setAnchorInformation 修改主播的信息
     * @param session 拿取账号
     * @return 修改结果
     */
    @RequestMapping("/SetAnchorInformation")
    @ResponseBody
    public String SetAnchorInformation(SetAnchorInformation setAnchorInformation,HttpSession session){
        LoginPojo login=(LoginPojo)session.getAttribute("login");
//        LoginPojo login =new LoginPojo();
//        login.setAccount("admin");
        setAnchorInformation.setAccount(login.getAccount());
        return service.SetAnchorInformation(setAnchorInformation,session);
    }

    /**
     * 修改主播头像
     * @param avatar 头像文件
     * @param session 用来拿取主播账号
     * @return 成功则返回新文件地址 失败则返回原因
     */
    @RequestMapping("/setAnchorImg")
    @ResponseBody
    public Map setAnchorImg(MultipartFile avatar,HttpSession session ){
        LoginPojo login =(LoginPojo) session.getAttribute("login");
        System.out.println(login.toString());
        return  service.setAnchorImg(avatar,login);
    }
}
