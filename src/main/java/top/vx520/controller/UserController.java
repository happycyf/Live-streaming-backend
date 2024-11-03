package top.vx520.controller;

import com.aliyun.tea.Validation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.vx520.pojo.Result;
import top.vx520.pojo.User;
import top.vx520.service.UserService;
import top.vx520.until.RandomNum;
import top.vx520.until.SendSms;

import java.sql.SQLOutput;
import java.time.Duration;


@RestController
@RequestMapping("/user")
@Validated//用于扫描校验注解
@CrossOrigin(origins = "*")
public class UserController {

    //Redis
    @Autowired
    RedisTemplate redisTemplate;
    //验证码工具类\
    @Autowired
    SendSms sendYzmUtil;
    @Autowired
    UserService userService;


    //注册 改
    @PostMapping("/reg")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String account, @Pattern(regexp = "^\\S{5,16}$") String pwd, String phone, String phoneKey){
        //查询用户名是
        User user = userService.findByAccount(account);

        //判断账号是否已被占用
        if (user==null){
            //开始注册
            User newUser=new User();
            //随机生成名称
            while(true) {
                String uName = "梦";
                //生成5位随机数
                int ranNum = RandomNum.getRanNum(5);
                //拼接字符串
                uName += ranNum;
                //查询该昵称是否已存在
                User byUName = userService.findByUName(uName);
                if (byUName==null){
                    System.out.println("+++++++++++++++++++++++++++++++++++++++"+redisTemplate.opsForValue().get(phone));
                    //判断手机验证码是否过期
                    if (redisTemplate.opsForValue().get(phone)==null||redisTemplate.opsForValue().get(phone).equals("")){
                        return Result.error("验证码已过期,或未发送验证码");
                    }
                    //短信验证
                    if (redisTemplate.opsForValue().get(phone).toString().equals(phoneKey)){
                        //加入数据
                        newUser.setAccount(account);
                        newUser.setPwd(pwd);
                        newUser.setUName(uName);
                        newUser.setPhone(phone);
                        //开始注册
                        userService.add(newUser);
                        //删除radis中的验证码以防被再次使用
                        redisTemplate.delete(phone);
                        return Result.success();
                    }else{
                        return Result.error("验证码错误");
                    }
                }
            }
        }
        return Result.error("账号已存在");
    }

    //登录
    @PostMapping("/log")
    public Result log(@Pattern(regexp = "^\\S{5,16}$") String account,@Pattern(regexp = "^\\S{5,16}$") String pwd){
        User user = userService.findByAccount(account);
        //判断账号是否存在
        if(user==null) return Result.error("用户不存在，请先完成注册");
        //判断账号密码是否对应
        if (user.getPwd().equals(pwd)){
            //发送整个用户信息回去
            return Result.success(user);
        }
        return Result.error("账号或密码输入有误");
    }

    //防刷新
    @RequestMapping("/findById")
    public Result findById(int uid){
        User user = userService.findById(uid);
        if (user!=null){
            return Result.success(user);
        }
        return Result.error("没有此用户");
    }

    //手机号登录
    @PostMapping("/logPhone")
    public Result logPhone(String phone,String Yzm){

        //判断手机验证码是否过期
        if (redisTemplate.opsForValue().get(phone)==null||redisTemplate.opsForValue().get(phone).equals("")){
            return Result.error("验证码已过期,或未发送验证码");
        }
        //短信验证
        if (redisTemplate.opsForValue().get(phone).toString().equals(Yzm)){
            User user = userService.findByPhone(phone);
            if (user!=null){
                return Result.success(user);
            }else {
                return Result.error("该手机号未注册");
            }
        }else {
            return Result.error("验证码错误");
        }
    }

    //修改信息 改
    @RequestMapping("/upInfo")
    public Result upInfo(int uId,String newUName,char newSex,String newAdress){
        User user = userService.findById(uId);
        user.setUName(newUName);
        user.setSex(newSex);
        user.setAdress(newAdress);

        int i = userService.upInfo(user);
        if (i>0){
            return Result.success();
        }
        return Result.error("修改失败");
    }

    //手机号验证
    @RequestMapping("/yzPhone")
    public Result yzPhone(int uId,String phone,String Yzm){
        System.out.println(uId);
        User user = userService.findById(uId);
        //判断绑定的手机号是否输入正确
        if (user.getPhone().equals(phone)){
            //判断手机验证码是否过期
            if (redisTemplate.opsForValue().get(phone)==null||redisTemplate.opsForValue().get(phone).equals("")){
                return Result.error("验证码已过期,或未发送验证码");
            }
            //短信验证
            if (redisTemplate.opsForValue().get(phone).toString().equals(Yzm)){
                return Result.success();
            }else {
                return Result.error("验证码错误");
            }
        }else {
            return Result.error("手机号输入错误");
        }
    }
    //修改绑定手机号
    @RequestMapping("/upPhone")
    public Result upPhone(int uId,String newPhone,String Yzm){
        //判断该手机号是否已被绑定
        User user = userService.findByPhone(newPhone);
        if (user!=null){
            return Result.error("该手机号已被绑定");
        }
        //判断手机验证码是否过期
            if (redisTemplate.opsForValue().get(newPhone)==null||redisTemplate.opsForValue().get(newPhone).equals("")){
                return Result.error("验证码已过期,或未发送验证码");
            }
            //短信验证
            if (redisTemplate.opsForValue().get(newPhone).toString().equals(Yzm)){
                //修改当前绑定手机号
                int i = userService.upPhone(uId, newPhone);
                if(i>0){
                    return Result.success();
                }else {
                    return Result.error("修改失败");
                }
            }else {
                return Result.error("验证码错误");
            }
    }
    //修改密码
    @RequestMapping("/upPwd")
    public Result upPwd(int uId,@Pattern(regexp = "^\\S{5,16}$") String newPwd){
            //修改密码
            int i = userService.upPwd(uId,newPwd);
            if(i>0){
                return Result.success();
            }else {
                return Result.error("修改失败");
            }

    }
    @RequestMapping("/findByAccount")
    //以账号查询账号
    public Result findByAccount(String account){
        User user = userService.findByAccount(account);
        return Result.success(user);
    }














    //获取短信，并存入redis中
    @GetMapping("/setKey")
    public Result setKey(String phone) throws Exception {
        System.out.println("++++++++++++++++++++++++++++++++++");
        int ranNum = RandomNum.getRanNum(6);
        System.out.println(phone);
        //判断验证码在redis中是否存在
        if (redisTemplate.hasKey(phone)){
            //若已经存在则先删除
            redisTemplate.delete(phone);
        }
        //将验证码存入redis中
        redisTemplate.opsForValue().set(phone,ranNum);
        //给验证码设置过期时间,过期时间为150秒(两分半)
        redisTemplate.expire(phone, Duration.ofSeconds(150));
        //开始发送验证码
        sendYzmUtil.sendYzm(phone, ranNum);
        System.out.println("发送成功");
        return Result.success();
    }




}
