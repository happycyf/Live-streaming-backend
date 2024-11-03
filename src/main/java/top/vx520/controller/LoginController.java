package top.vx520.controller;


import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import top.vx520.pojo.LoginPojo;
import top.vx520.pojo.RegisterPojo;
import top.vx520.service.LoginService;


import java.util.Map;

@RestController
@RequestMapping("/login")

public class LoginController {

//    登录逻辑处理
    @Autowired
    private LoginService service;

    @RequestMapping("/getLogin")
    @ResponseBody
    public Map getLogin(LoginPojo loginPojo, HttpSession session){
        return service.query(loginPojo,session);
    }
//    发送验证码
    @PostMapping("/sendYzm")
    @ResponseBody
    public Map sendYzm(@RequestBody Map<String,String> map,HttpSession session){
        String phone = map.get("phone");

        return service.sendYzm(phone,session);
    }

    /**
     * 头像上传
     * @param registerUser 注册信息
     * @param avatar 头像文件
     * @return
     */
//   注册
    @PostMapping("/uploadRegister")
    @ResponseBody
    public Map uploadAvatar( RegisterPojo registerUser,@RequestParam("avatar") MultipartFile avatar,HttpSession session){
        return service.uploadAvatar(registerUser, avatar, session);
    }

    /**
     * 忘记密码
     * @param phone 手机号
     * @param session 存储忘记密码验证码
     * @return
     */
    @RequestMapping("/sendForgotPasswordYzm")
    public Map ForgotPasswordSendYzm(String phone,HttpSession session){
        return  service.ForgotPasswordSendYzm(phone,session);
    }

    /**
     *
     * @param phone 手机号
     * @param forgotPassWordYZM 验证码
     * @param session 存储验证码地区
     * @return
     */
    @RequestMapping("/ForgotPasswordPhone")
    @ResponseBody
    public Map ForgotPasswordPhone(String phone,String forgotPassWordYZM,HttpSession session){

        return  service.ForgotPasswordPhone(phone,forgotPassWordYZM,session);
    }

    /**
     * 修改密码
     * @param pwd 新密码
     * @param state 忘记密码
     * @param session 查找账号
     * @return
     */
    @RequestMapping("/setPassWord")
    public Map setPassWord(String pwd,String state,HttpSession session){

        return service.setPassWord(pwd, state, session);
    }

    /**
     * 获取图片地址
     * @return 图片地址
     */
    @RequestMapping("/getImgSrc")
    public Map getImgSrc(HttpSession session){
        return service.getImgSrc(session);
    }
}