package top.vx520.until;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import top.vx520.pojo.LoginPojo;

import java.util.HashMap;

public class JwtUtil {
//    创建令牌

    /**
     *
     * @return jwt令牌
     */
    public static String createJWT(LoginPojo loginPojo){
        String jwt= JWT.create()
                .withHeader(new HashMap<>())
                .withClaim("account",loginPojo.getAccount())
                .withClaim("pwd",loginPojo.getPwd())
                .withClaim("uTypeId",loginPojo.getuTypeId())
                .sign(Algorithm.HMAC256("造梦工厂直播平台"));
        return jwt;
    }
//    返回令牌

    /**
     * 解析令牌
     * @param jwt 令牌
     * @return 登录实例
     */
    public static LoginPojo getJwt(String jwt){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("造梦工厂直播平台")).build();
        DecodedJWT verify = jwtVerifier.verify(jwt);
        LoginPojo loginPojo=new LoginPojo();
        loginPojo.setAccount(verify.getClaim("account").asString());
        loginPojo.setPwd(verify.getClaim("pwd").asString());
        loginPojo.setuTypeId(verify.getClaim("uTypeId").asInt());

        return loginPojo;
    }

}
