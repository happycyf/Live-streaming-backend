package top.vx520;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import top.vx520.service.LiveStreamingInterfaceService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DebuutServiceApplicationTests {

    @Test
    public void contextLoads() {
        Map map=new HashMap();
        map.put("username","admin");
        map.put("password","123456");
        String user = JWT.create().withClaim("user", map)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000))
                .sign(Algorithm.HMAC256("造梦工厂"));
        System.out.println(user);

    }
    @Test
    public void  Test2(){

    }
    @Test
    public  void createJWT(){
        Map map = new HashMap();
        String account="admin";
        String pwd="123";
        int uTypeId=3;
        String jwt= JWT.create()
                .withHeader(map)
                .withClaim("account",account)
                .withClaim("pwd",pwd)
                .withClaim("uTypeId",uTypeId)
                .sign(Algorithm.HMAC256("zaomenggongchang"));
        System.out.println(jwt);
//        return jwt;
    }
    @InjectMocks
    LiveStreamingInterfaceService service;
    @Test
    public void TestgetAnchorBasic( ){
        System.out.println(service);
//        LoginPojo loginPojo = new LoginPojo();
//        loginPojo.setAccount("admin");
//        service.getAnchorBasic(loginPojo);
    }
}
