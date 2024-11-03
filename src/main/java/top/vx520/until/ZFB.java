package top.vx520.until;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class ZFB {
    public static String SendZFB(int uId,String no,double price,String shopName){
//        私钥
        final String privateKey ="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCu0+0haDssKgjb67IbzdRL/fM3itq2Y1hEoCxyWj3w61Hnk8JFNY8nB/FX293WlShMvE7o/s5Z3R+qZz3YOxDaXfsVTnAeMZ8rGbo7DGBtVOyMuggdAqioiPmXfoSz7Wv5YvhuL7HZGM6GO3aGiTggFzkwpLeL3xQXWM+i87pJ2ufOluqSGbBF5UJl9sB6sU6xAbm/C/PIZHL5+dPdIpu4seMZaez+7CrSJDBEP73TkUjIbpCyul/NQ2GqvLdMFiFPFJHmJEYniJ2NvIyvlcaPSBTQu5COKGphuZq0DhmkOH1itwGo9D2iG9ZVITuyLiFjqhebkTDX/Np4eQQToAu3AgMBAAECggEBAIRyhanTf1C8H64M6AhsbTONFt+zUHLw3IRRHJsdhuOoQ4q/xGQOaX9WYx7+sRfKNJe96cJWmBo4ja7ZPF8+kEv++yNvIz2ctks+2Hm3rK3nsrYCkfb4drIH459D+GEfs7dhpXhEpeYlBwFmtyrBdkXJkkgp4ewTevMdE2EOaMldy439LvmhwxSQGt4o1RDhPKWwEwnNqwjVwzvEgy8xo3aKjmhLTjBU3MxkW+O+iHUl8btb5ggRojqu3RutSZSrW92IbVs9wSO8QnqCz905ZWare1x8atf15z211BXheBlBxhh98N7buAyegP8o0nlNFHvQ69NT88U2qKLc577qYzECgYEA+M3kVV63DVseQFiVVaPFzkDlKwWYSBFS4mTenB5bJpybKt53CWZJyXYz/9DWlR9AAQnK+tzIaP9pNGWiFmK179U9I/l5BFDADP6frEXYf17IsL/Upe5E3n8gdnr6BYdACjBByKyd/oEMAihDYuw1g8KLyzOShKsO/wRa5/TE8M0CgYEAs+JTC7bnMiQwg9HHFDAOkMnNn9KZgHk46my0WUrTHf3P5ALslffMzyLdWPI8+GITZG0staYjvU0+TIX8esRe7A9d4AUs4R+nB0I1Sz9ByVQxoGR5HaW2HXQ2CDoUvRhfNtlMbk5OMCdz2ULKjffVNxHvzXuJnQARpCwkE9SY3pMCgYEAuZ35VAtWa3toBT9RIdQN1YMqtT72TgXGnSn9veETxameRYw0qGmMcOVjg91MyXHH8T5aFW+zgEnUNggAtcysySF//J+4vhR0Qv2PKBnvm46eEOb8gVJSRN/42wuCDa/Kgg9gdJL7SA0EZXOeWmW4wvTYECAIljt785tPKq+8YZ0CgYAUb1CJ0yRegNf2EZ2xAGiXMCWo007W1m6Nyv2N6z8ieBPmziI8QyNEIKzOju44NUQIs5JRrGcA+UZrgQkWlTpHTNOYQF5B0MuzumS9gJkjmunsU52irZGLtQjyVE7I0Hz4iSTynvio5cJ2Wu0Rxaa2E9aPHEqcyEY4xT7v9cXL+wKBgQCcp1AmInicMgr/34j0RWopyNM0rv1YaQoBBME9WoTZ6N6lfAJAqU5Ob0FocA+0F4XpbMH7b5fg843ty/4xXHWcE/xEVZghPloMDILgrpj67AhMHEbkS8zs/wjnEJs6/HpGK0HkwfN3fnJF/wf4BTD967PfGq3IAF4Xvojfg/tBnw==";
        final  String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArtPtIWg7LCoI2+uyG83US/3zN4ratmNYRKAsclo98OtR55PCRTWPJwfxV9vd1pUoTLxO6P7OWd0fqmc92DsQ2l37FU5wHjGfKxm6OwxgbVTsjLoIHQKoqIj5l36Es+1r+WL4bi+x2RjOhjt2hok4IBc5MKS3i98UF1jPovO6SdrnzpbqkhmwReVCZfbAerFOsQG5vwvzyGRy+fnT3SKbuLHjGWns/uwq0iQwRD+905FIyG6QsrpfzUNhqry3TBYhTxSR5iRGJ4idjbyMr5XGj0gU0LuQjihqYbmatA4ZpDh9YrcBqPQ9ohvWVSE7si4hY6oXm5Ew1/zaeHkEE6ALtwIDAQAB";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi-sandbox.dl.alipaydev.com/gateway.do","9021000122689510",privateKey,"json","UTF-8",publicKey,"RSA2");
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //设置接受编码

//异步接收地址，仅支持http/https，公网可访问
        request.setNotifyUrl("");

/******必传参数******/
        JSONObject bizContent = new JSONObject();
//        String  no =req.getParameter("id");
//商户订单号，商家自定义，保持唯一性
        bizContent.put("out_trade_no", no);
//        String price =req.getParameter("price");

//支付金额，最小值0.01元
        bizContent.put("total_amount", price);
//        String shopName=req.getParameter("ShopName");
//订单标题，不可使用特殊符号
        bizContent.put("subject", shopName);
//        超时设置
        bizContent.put("timeout_express","5m");
//电脑网站支付场景固定传值FAST_INSTANT_TRADE_PAY
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

        //同步跳转地址，仅支持http/https
//        回调地址
        request.setReturnUrl("http://localhost:8080/order/ZfSuccess?uId="+uId+"&price="+price);

/******可选参数******/
//bizContent.put("time_expire", "2022-08-01 22:00:00");

//// 商品明细信息，按需传入
//JSONArray goodsDetail = new JSONArray();
//JSONObject goods1 = new JSONObject();
//goods1.put("goods_id", "goodsNo1");
//goods1.put("goods_name", "子商品1");
//goods1.put("quantity", 1);
//goods1.put("price", 0.01);
//goodsDetail.add(goods1);
//bizContent.put("goods_detail", goodsDetail);

//// 扩展信息，按需传入
//JSONObject extendParams = new JSONObject();
//extendParams.put("sys_service_provider_id", "2088511833207846");
//bizContent.put("extend_params", extendParams);

        request.setBizContent(bizContent.toString());
        AlipayTradePagePayResponse response = null;
        try {
            response = alipayClient.pageExecute(request,"POST");
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
// 如果需要返回GET请求，请使用
//         AlipayTradePagePayResponse response = alipayClient.pageExecute(request,"GET");
        String pageRedirectionData = response.getBody();
        System.out.println(pageRedirectionData);
//        resp.setContentType("text/html;charset=utf-8");
//        resp.getWriter().print(pageRedirectionData);

        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return pageRedirectionData;
    }

}
