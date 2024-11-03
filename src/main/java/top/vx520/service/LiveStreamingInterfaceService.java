package top.vx520.service;


import jakarta.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.vx520.mapper.LiverecordMapper;
import top.vx520.mapper.UserMapper;
import top.vx520.pojo.*;
import top.vx520.until.SendYzmUtil;
import top.vx520.webSocket.LiveWebSocket;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class LiveStreamingInterfaceService {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private SendYzmUtil sendYzmUtil;
    @Value("${httpurl}")
    private String url;
//    专门存放主播封面的位置
    private final String coverUrl ="src/main/resources/static/cover/";
    public Map<String,String> getUserFan(HttpSession session) {
//        模拟账号
//        LoginPojo login =new LoginPojo();
//        login.setAccount("admin");
        Map<String,String> map = new HashMap<>();
        LoginPojo login = (LoginPojo) session.getAttribute("login");
        System.out.println(login.toString());
        int fan = mapper.getFan(login.getAccount());
        map.put("fans",fan+"");
        map.put("tKey", mapper.getTKey(login.getAccount()));
        return map;
    }



    public List<UserFenSiPojo> getUserFenSi(HttpSession session) {
//        LoginPojo login =new LoginPojo();
//        login.setAccount("admin");
        LoginPojo login = (LoginPojo) session.getAttribute("login");
        return mapper.getUserFenSi(login.getAccount());

    }

    public List<JinRiLiWuShouYiPojo> getTodayMoneyBang(HttpSession session) {
//        LoginPojo login =new LoginPojo();
//        login.setAccount("admin");
        LoginPojo login = (LoginPojo) session.getAttribute("login");
        return mapper.getJinRiLiWu(login.getAccount());
    }

    public Map<String, String> getIncomeData(HttpSession session) {
//        LoginPojo login =new LoginPojo();
//        login.setAccount("admin");
        LoginPojo login = (LoginPojo) session.getAttribute("login");
        String account = login.getAccount();
        Map<String,String> map =new HashMap<>();
//        获得余额
        double blance = mapper.getBlance(account);
//        今日收益
        double toDayMoney = mapper.getToDayMoney(account);
//        昨日收益
        double yesterdayMoney = mapper.getYesterdayMoney(account);
//        本月收益
        double monthlyIncome = mapper.getMonthlyIncome(account);
//        上月收益
        double lastMonthMoney = mapper.getLastMonthMoney(account);
        map.put("blance",blance+"");
        map.put("todayMoney",toDayMoney+"");
        map.put("yesterdayMoney",yesterdayMoney+"");
        map.put("monthlyIncome",monthlyIncome+"");
        map.put("lastMonthMoney",lastMonthMoney+"");
        return map;
    }

    public Map<String, String> getWithdrawalCode(HttpSession session) {
        Map<String,String> map =new HashMap<>();
//        LoginPojo login =new LoginPojo();
//        login.setAccount("admin");
        LoginPojo login = (LoginPojo) session.getAttribute("login");
        String account = login.getAccount();
//        提现验证码
        Map map1 = sendYzmUtil.sendYzm(account, "SMS_474960149");
        if (map1.get("SendState").equals("success")) {
            map.put("code","200");
            session.setAttribute("WithdrawalCode",map1.get("Yzm"));
        }else {
            map.put("code","500");
        }
        return map;
    }
    @Transactional
    public Map<String, String> SendWithdrawal(double money, String code,String payAccount, HttpSession session) {
        Map<String,String> map =new HashMap<>();
        String withdrawalCode = session.getAttribute("WithdrawalCode")+"";
//        String withdrawalCode ="123456";
//        LoginPojo login =new LoginPojo();
        LoginPojo login = (LoginPojo) session.getAttribute("login");
//        login.setAccount("admin");
//        未登录情况
        if (login == null) {
            map.put("code","500");
            map.put("message","请先登录");
            return map;
        }
        if (code == null) {
            map.put("code","500");
            map.put("message","请先发送验证码");
        }
//        登录且验证码一致
        if (withdrawalCode.equalsIgnoreCase(code)) {
//            添加提现账号
            int line = mapper.addWithdrawal(login.getAccount(), money,payAccount);
//            扣款
            int deduction=mapper.Deduction(login.getAccount(),money);
            if (line > 0 && deduction>0) {
                map.put("code","200");
                map.put("message","提现成功");
            }else {
                map.put("code","500");
                map.put("message","提现失败");
            }
        }else {
            map.put("code","500");
            map.put("message","验证码不正确");
        }
        return map;
    }

    public Map<String, List<WithdrawalPojo>> getWithdrawalList(HttpSession session) {
        System.out.println("进入");
        Map<String, List<WithdrawalPojo>> map =new HashMap<>();
//        LoginPojo login =new LoginPojo();
        LoginPojo login = (LoginPojo) session.getAttribute("login");
//        login.setAccount("admin");
        List<WithdrawalPojo> withdrawal = mapper.getWithdrawal(login.getAccount());
        System.out.println(withdrawal.toString());
        map.put("withdrawal",withdrawal);
        return map;
    }

    public Map getAnchorData(HttpSession session) {
        Map map =new HashMap<>();
//        月收益
        double[] monthlyIncome=new double[12];
//        月最大日收益
        double[] maxMonthDayMoney=new double[12];
//        月最小日收益
        double[] minMonthDayMoney=new double[12];
//        LoginPojo login =new LoginPojo();
        LoginPojo login = (LoginPojo) session.getAttribute("login");
//        login.setAccount("admin");
//        月份数据
        List<MonthlyIncomePojo> monthlyIncomePojo = mapper.getMonthlyIncomePojo(login.getAccount());
//        获得主播月收益
        for (int i = 0; i <monthlyIncomePojo.size() ; i++) {

//            月份
            int month = monthlyIncomePojo.get(i).getMonth();
//            主播月收益
            double money = monthlyIncomePojo.get(i).getMoney();
//            赋值月收益
            monthlyIncome[month-1]=money;
//            获得日最大收益日最小收益
            MonthlyIncomePojo maxAndMinMonthlyIncomePojo = mapper.getMaxAndMinMonthlyIncomePojo(login.getAccount(), month);
            maxMonthDayMoney[month-1]=maxAndMinMonthlyIncomePojo.getMaxMoney();
            minMonthDayMoney[month-1]=maxAndMinMonthlyIncomePojo.getMinMoney();
        }

        map.put("MonthlyIncome",monthlyIncome);
        map.put("maxMonthDayMoney",maxMonthDayMoney);
        map.put("minMonthDayMoney",minMonthDayMoney);
        return map;
    }

    public List<AllGiftPojo> getAllGiftMoney(HttpSession session) {
//        LoginPojo login =new LoginPojo();
        LoginPojo login = (LoginPojo) session.getAttribute("login");
//        login.setAccount("admin");
        List<AllGiftPojo> allGiftList = mapper.getAllGiftList(login.getAccount());

        for (int i = 0; i <allGiftList.size() ; i++) {
            AllGiftPojo allGiftPojo = allGiftList.get(i);
            allGiftPojo.setUrl(url +"/avatarImg/"+allGiftPojo.getUrl());
            allGiftList.set(i,allGiftPojo);
        }
        return allGiftList;
    }

    public UserInformationPojo getUserInformation(HttpSession session) {
//        LoginPojo login =new LoginPojo();
          LoginPojo login = (LoginPojo) session.getAttribute("login");
//        login.setAccount("admin");
        return mapper.getUserInformation(login.getAccount());
    }

    public Map<String, String> sendSetPwdYzm(String phone, HttpSession session) {
//        LoginPojo login =new LoginPojo();
        LoginPojo login = (LoginPojo) session.getAttribute("login");
//        login.setAccount("admin");
        Map<String, String> map ;
//        判断手机号是否一致
        if (mapper.getUserPhone(login.getAccount()).equals(phone)) {
//            修改密码验证码
            map = sendYzmUtil.sendYzm(phone, "SMS_474885138");
            if(map.get("SendState").equals("success")) {
                session.setAttribute("setPwdYzm", map.get("Yzm"));
            }
        }else {
            map=new HashMap<>();
            map.put("SendState","error");
            map.put("SendMessage","绑定手机号与账号不一致");
        }
        return map;
    }

    public int codePhone(String phone, String Yzm,HttpSession session) {
        Object yzm = session.getAttribute("setPwdYzm");

//        String yzm="1234";
        LoginPojo login = (LoginPojo) session.getAttribute("login");
//        LoginPojo  login =new LoginPojo();
//        login.setAccount("admin");
        if (yzm == null) {
            return 500;
        }
        String userPhone = mapper.getUserPhone(login.getAccount());
//        验证码正确 且手机号一致
        if(Yzm.equals(yzm) && userPhone!=null && userPhone.equals(phone)){
            return 200;
        }else {
            return 500;
        }
    }


    public Map<String, String> setPwd(String oldPwd, String newPwd, HttpSession session) {
        Map<String, String> map =new HashMap<>();
        LoginPojo login = (LoginPojo) session.getAttribute("login");
//        LoginPojo login =new LoginPojo();
//        login.setAccount("admin");
        if (oldPwd == null && newPwd==null) {
            map.put("state","500");
            map.put("message","旧密码与新密码之间任意一个不能为空！！");
        }
        String oldPassword = mapper.getOldPassword(login.getAccount());
        System.out.println(oldPwd);
//        查询到的密码不是空 旧密码与查询到的密码一致
        if (oldPassword != null && oldPassword.equals(oldPwd)) {
            int spw = mapper.setPassWord(mapper.getUserPhone(login.getAccount()), newPwd);
            if (spw >0) {
                map.put("state","200");
                map.put("message","修改成功");
            }else {
                map.put("state","500");
                map.put("message","修改失败");
            }
        }else {
            map.put("state","500");
            map.put("message","旧密码不正确");
        }

        return map;
    }
    public String uploadCover(MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            String contentType = file.getContentType();

//            生成uuid当作文件名
            UUID uuid = UUID.randomUUID();
            String[] split = contentType.split("/");
            File toFile=new File(coverUrl);
//            判断是文件夹否存在
            if(!toFile.exists()){
//                不存在则创建
                boolean mkdirs = toFile.mkdirs();
                if (!mkdirs) {
                    System.out.println("文件夹创建失败");
//                    不是true说明失败了
                    return null;
                }
            }
            toFile=new File(coverUrl+uuid.toString()+"."+split[split.length-1]);
            FileUtils.copyToFile(inputStream,toFile);
            return uuid.toString()+"."+split[split.length-1];
        } catch (IOException e) {
            e.printStackTrace();
//            不做日志处理直接宣布失败
            return null;
        }
    }
    @Autowired
    private LiverecordMapper liverecordMapper;
    public void addLiveTime(String account, String liveTitle, String startTime, String overTime) {
        //        获取在线用户数
        AchorPojo achorPojo = LiveWebSocket.anchorMap.get(account);
        String s = achorPojo.gettKey();
        int seeNumber = LiveWebSocket.map.get(s).size();
        liverecordMapper.add(account,liveTitle,startTime,overTime,seeNumber);
    }

    public Map<String, Integer> getAnchorBasic(LoginPojo login) {
//        相差分钟
        int chaMin=0;
        int seeNumber=0;
//        这里获取开播时长
        List<liverecordPojo> dayHources = liverecordMapper.getDayHources(login.getAccount());
        for (liverecordPojo dayHource : dayHources) {
            LocalDateTime localDateTime = dayHource.getStartTime().toLocalDateTime();
            LocalDateTime localDateTime1 = dayHource.getOverTime().toLocalDateTime();
            Duration duration = Duration.between(localDateTime, localDateTime1);
            chaMin+=Math.abs(duration.toMinutes());
            seeNumber=Math.max(seeNumber,dayHource.getSeeNumber());
        }
        int fan = mapper.getFan(login.getAccount());
        Map<String, Integer> map = new HashMap<>();
        map.put("WorkingHours",chaMin);
        map.put("peakValue",seeNumber);
        map.put("fansNumbers",fan);
        return map;
    }

    public String SetAnchorInformation(SetAnchorInformation setAnchorInformation, HttpSession session) {
        Object setPwdYzm = session.getAttribute("setPwdYzm");
//        不管对不对先判断
        if (setPwdYzm != null) {
//            不满足条件全部改空
            if (!(setAnchorInformation.getSetPeopleYZM() != null && setPwdYzm.equals(setAnchorInformation.getSetPeopleYZM()))) {
                setAnchorInformation.setOldPhone(null);
                setAnchorInformation.setNewPhone(null);
            }
        }
        int i = mapper.SetAnchorInformation(setAnchorInformation);
        if (i >0) {
            return "修改成功";
        }else {
            return "修改失败";
        }
    }
    @Transactional
    public Map setAnchorImg(MultipartFile upload, LoginPojo loginPojo) {
        Map map = new HashMap();
//        获取原地地址
        String imgSrc = mapper.getImgSrc(loginPojo);
        File file=new File("src/main/resources/static/avatarImg/"+imgSrc);
        boolean delete =true;
        System.out.println(file.exists());
//        如果存在就删除源地址
        if (file.exists()) {
             delete=file.delete();
        }
        if (delete) {
            try {
                InputStream inputStream = upload.getInputStream();
                String[] split = upload.getContentType().split("/");
                String prefix= split[split.length-1];
//                新图片名称
                String uuid=UUID.randomUUID().toString()+"."+prefix;
                File newFile=new File("src/main/resources/static/avatarImg/"+uuid);
                FileUtils.copyToFile(inputStream,newFile);
                int i = mapper.setImgUrl(uuid, loginPojo.getAccount());
                if (i >0) {
                    map.put("state","200");
                    map.put("url",url+"/avatarImg/"+uuid);
                }else {
                    map.put("state","500");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return map;
    }


}
