// This file is auto-generated, don't edit it. Thanks.
package top.vx520.until;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.*;
@Component
public class SendYzmUtil {
    @Value("${ALIBABA_CLOUD_ACCESS_KEY_ID}")
    private String ALIBABA_CLOUD_ACCESS_KEY_ID;
    @Value("${ALIBABA_CLOUD_ACCESS_KEY_SECRET}")
    private String ALIBABA_CLOUD_ACCESS_KEY_SECRET;
    /**
     * <b>description</b> :
     * <p>使用AK&amp;SK初始化账号Client</p>
     * @return Client
     *
     * @throws Exception
     */
    public Client createClient() throws Exception {
        // 工程代码泄露可能会导致 AccessKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考。
        // 建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html。
        Config config = new Config()
                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID。
                .setAccessKeyId(ALIBABA_CLOUD_ACCESS_KEY_ID)
                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
                .setAccessKeySecret(ALIBABA_CLOUD_ACCESS_KEY_SECRET);
        // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }

//发送验证码
    public Map sendYzm(String phone,String TemplateCode) {

        String Yzm=RandomYzm();
        Map map =new HashMap();
        Client client =null;
        try {
            client =createClient();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SendSmsRequest sendSmsRequest=new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("造梦工厂")
                .setTemplateCode(TemplateCode)
                .setTemplateParam("{\"code\":\""+Yzm+"\"}");
        SendSmsResponse smsResponse;
        try {
            smsResponse= client.sendSms(sendSmsRequest);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String code = smsResponse.body.code;
        if(code.equals("OK")){
            map.put("Yzm",Yzm);
            map.put("SendState","success");
            map.put("SendMessage","发送成功");
        }else if (code.equals("isv.MOBILE_NUMBER_ILLEGAL")){
            map.put("SendState","error");
            map.put("SendMessage","手机号有误");
        }else {
            map.put("SendState","error");
            map.put("SendMessage","发送失败");
        }
        return map;
    }




//    生成验证码
    public String RandomYzm(){
        List list =new ArrayList<>();
        for (int i = 0; i <=9 ; i++) {
            list.add(i);
        }
        for (int i = 65; i <=90 ; i++) {
            list.add((char)i);
        }
        for (int i = 97; i <=122 ; i++) {
            list.add((char)i);
        }
        StringBuffer str =new StringBuffer();
//        生成6位验证码
        for (int i = 0; i < 6; i++) {
            Random random= new Random();
            int rs =random.nextInt(list.size());
            str.append(list.get(rs));
        }
        return str.toString();
    }


}