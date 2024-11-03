package top.vx520.service;

import jakarta.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import top.vx520.mapper.UserMapper;
import top.vx520.pojo.LoginPojo;
import top.vx520.pojo.RegisterPojo;
import top.vx520.until.SendYzmUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class LoginService {
//    用户操作mapper
    @Autowired
    private UserMapper mapper;
    @Autowired
    private SendYzmUtil sendYzmUtil;
    @Value("${httpurl}")
    private String url;
    /**
     * 登录判断
     *
     * @param loginPojo 登录信息
     * @param session
     * @return 登录结果
     */
    public Map query(LoginPojo loginPojo, HttpSession session){
        LoginPojo query = mapper.query(loginPojo);
        Map map = new HashMap();
        if(query != null){
            session.setAttribute("login",query);
            map.put("loginStatus","success");
            map.put("LoginMessage","登录成功");
        }else {
            map.put("loginStatus","error");
            map.put("LoginMessage","账号或密码有误请重新输入！！！");
        }
        return map;
    }

    /**
     * 注册发送验证码
     *
     * @param phone   注册手机
     * @param session
     * @return 注册结果
     */
    public Map sendYzm(String phone, HttpSession session) {
        //        发送短信验证码注册模板
        Map map = sendYzmUtil.sendYzm(phone, "SMS_474795190");
        if (map.get("SendState").equals("success")) {
            System.out.println("生成的验证码"+map.get("Yzm"));
            session.setAttribute("Yzm",map.get("Yzm"));
        }
        return map;

    }

    /**
     * 上传头像
     *
     * @param registerPojo 注册对象
     * @param avatar       头像文件
     * @param session
     * @return
     */
    public Map uploadAvatar(RegisterPojo registerPojo, MultipartFile avatar, HttpSession session){
        Map map = new HashMap();
//        如果手机号注册了则直接返回
        if (mapper.queryPhone(registerPojo.getPhone()) != null) {
            map.put("registerState","error");
            map.put("registerMessage","手机号已注册");
            return map;
        }

        Object yzm = session.getAttribute("Yzm");
//        测试模组
//        String yzm="123456";
        System.out.println("验证码："+yzm);
//            session未存代表没发送
        if (yzm == null) {
            map.put("registerState","error");
            map.put("registerMessage","请先发送验证码");
            return map;
//            两验证码不符说明验证码有误
        }else if (!yzm .equals(registerPojo.getRegisterYZM())) {
            map.put("registerState","error");
            map.put("registerMessage","验证码不正确");
            return map;
        }

//        获取文件名
            UUID uuid=UUID.randomUUID();
//            获取类型
            String contentType = avatar.getContentType();
//    存储地址
            File file=new File("src/main/resources/static/avatarImg");
//            后缀
            String[] split = contentType.split("/");
            String[] split1 = split[split.length - 1].split(";");
        //            不存在则创建
            if (!file.exists()) {
                file.mkdirs();
            }
//            文件地址
            File ReallyFile=new File(file.getAbsoluteFile()+"/"+uuid+"."+split1[0]);
            System.out.println(ReallyFile.toURI());
            try {
//                进行赋值
                FileUtils.copyToFile(avatar.getInputStream(),ReallyFile);
            } catch (IOException e) {
                map.put("registerState","error");
                map.put("registerMessage","文件上传异常请联系管理员解决");
                return map;
            }
//            设置图片值
            registerPojo.setImg(uuid.toString()+"."+split1[0]);
//            账号为手机号
            registerPojo.setAccount(registerPojo.getPhone());
            registerPojo.setuKey(UUID.randomUUID().toString());
//            设置key值
        if (mapper.registerUser(registerPojo) !=0) {
            map.put("registerState","success");
            map.put("registerMessage","注册成功");
            map.put("registerAccount",registerPojo.getAccount());
        }else {
            map.put("registerState","error");
            map.put("registerMessage","注册失败");
        }
        return map;
    }

    /**
     * 忘记密码发送验证码
     * @param phone
     * @param session
     * @return
     */
    public Map ForgotPasswordSendYzm(String phone, HttpSession session) {
        Map map =new HashMap<>();
//        未查到手机号禁止发送
        if (mapper.queryPhone(phone) == null) {
            map.put("SendState","error");
            map.put("SendMessage","手机号未注册");
            return map;
        }
//        忘记密码验证码
        map=sendYzmUtil.sendYzm(phone, "SMS_474985148");
        if (map.get("SendState").equals("success")) {
            session.setAttribute("forgotPasswordYzm",map.get("Yzm"));
            System.out.println(map.get("Yzm"));
        }
        return map;
    }

    /**
     * 手机验证码验证
     * @param phone 手机号
     * @param forgotPassWordYZM 验证码
     * @param session 存储位置
     * @return 返回结果是否成功
     */
    public Map ForgotPasswordPhone(String phone, String forgotPassWordYZM, HttpSession session) {
        Map map =new HashMap<>();
        if ((session.getAttribute("forgotPasswordYzm")+"").equalsIgnoreCase(forgotPassWordYZM)) {
//        if (forgotPassWordYZM.equalsIgnoreCase("123456")){
            session.setAttribute("forgotPasswordPhone",phone);
            map.put("forgotPasswordPhoneState","success");
            map.put("forgotPasswordPhoneMessage","验证成功");
        }else {
            map.put("forgotPasswordPhoneState","error");
            map.put("forgotPasswordPhoneMessage","验证失败");
        }
        return map;
    }

    /**
     * 修改密码
     * @param pwd 密码
     * @param state 修改状态 包含 注册修改密码状态 页面修改密码状态
     * @param session 存储验证码地址
     * @return 是否修改成功
     */
    public Map setPassWord(String pwd, String state, HttpSession session) {
        Map map =new HashMap<>();
        String phone ;
        if(state.equals("forgotPassword")){
//            模拟拿到手机号
//            phone="19873929472";
            phone=session.getAttribute("forgotPasswordPhone").toString();
        }else {
//            登录状态修改密码
            phone=session.getAttribute("phone").toString();
        }
        int i = mapper.setPassWord(phone, pwd);
        if (i == 0) {
            map.put("setPassWordState","error");
            map.put("setPassWordMessage","修改密码失败");
        }else {
            map.put("setPassWordState","success");
            map.put("setPassWordMessage","修改密码成功");
        }
        return map;
    }

    /**
     * 获取用户头像
     * @param session 用户账号存储地址
     * @return 界面结果 用户头像地址
     */
    public Map getImgSrc(HttpSession session) {
        Map map =new HashMap<>();
        System.out.println("sessionId为"+session.getId());
        LoginPojo login = (LoginPojo) session.getAttribute("login");
        System.out.println(login.toString());
        if (login == null) {
            map.put("State","error");
            map.put("message","请先登录");
            return map;
        }
        String imgSrc = mapper.getImgSrc(login);
        if (imgSrc == null) {
            map.put("State","error");
            map.put("message","账户有变动,请重新登录");
            return map;
        }
        map.put("state","success");
        map.put("imgSrc",url+"/avatarImg/"+imgSrc);
        login.setImg(url+"/avatarImg/"+imgSrc);
        session.setAttribute("login",login);
        return map;
    }
}
