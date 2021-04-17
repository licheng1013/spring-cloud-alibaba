package com.demo;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.istack.internal.NotNull;

import java.io.Serializable;

/**
 * @author root
 * @description TODO
 * @date 2020/12/18 16:48
 */
public class TokenUtil  {
    /** 密钥串 **/
    private static final String secretKey = "izVguZPRsBQ5Rqw6dhMvcIwy8_9lQnrO3vpxGwP";
    private static final String issuer = "auth-token";

    /**
     * 生成token
     * @author lc
     * @date 2020/12/19
     */
    public static String getToken(@NotNull Serializable keyId){
        try {
            Algorithm algorithm = getAlgorithm();
            return JWT.create().withKeyId(keyId.toString())
                    .withIssuer(issuer)
//                    .withExpiresAt(DateUtil.endOfDay(new Date())) // 过期时间,这里表示是未来的时间,否则无限报错
                    .sign(algorithm);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("token生成失败");
        }
    }

    private static Algorithm getAlgorithm(){
        return Algorithm.HMAC256(secretKey);
    }


    /**
     * 可以设置到期时间的token
     * @param keyId kid
     * @param dateField 调整的部分 {@link DateField}
     * @param offset 偏移时间
     * @author lc
     * @date 2021/4/17
     */
    public static String getToken(@NotNull  Serializable keyId, DateField dateField,int offset){
        DateTime time = DateUtil.date().offset(dateField, offset);
        Algorithm algorithm = getAlgorithm();
        return JWT.create().withKeyId(keyId.toString())
                .withExpiresAt(time)//到期时间
                .sign(algorithm); //签名
    }

    private static DecodedJWT getDecodedJWT(String token){
        try {
            Algorithm algorithm = getAlgorithm();
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer).build();
            return verifier.verify(token);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("token解密失败");
        }
    }


    /**
     * 获取userId
     * @author lc
     * @date 2020/12/19
     */
    public static String getUserId(String token){
        return getDecodedJWT(token).getKeyId();
    }

    public static void main(String[] args) {
        //测试
        String token = getToken(1);
        System.out.println(token);
        String userId = getUserId(token);
        System.out.println(userId);
        System.out.println(DateUtil.date().offset(DateField.HOUR_OF_DAY, 24*3).toString());
    }
}
