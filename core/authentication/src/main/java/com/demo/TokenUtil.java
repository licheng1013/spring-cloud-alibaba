package com.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.Serializable;

/**
 * @author root
 * @description TODO
 * @date 2020/12/18 16:48
 */
public class TokenUtil  {
    /** 密钥串 **/
    public static String secretKey = "izVguZPRsBQ5Rqw6dhMvcIwy8_9lQnrO3vpxGwP";

    /**
     * @author lc
     * @date 2020/12/19
     * @description 生成token
     */
    public static String getToken(Serializable userId){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create().withKeyId(userId.toString())
                    .withIssuer("auth0")
                    .sign(algorithm);
        } catch (Exception exception){
            throw new RuntimeException("token生成失败");
        }
    }
    /**
     * @author lc
     * @date 2020/12/19
     * @description 获取userId
     */
    public static String getUserId(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getKeyId();
        } catch (Exception exception){
            throw new RuntimeException("token解密失败");
        }
    }

    public static void main(String[] args) {
        //测试
        String token = getToken(1);
        System.out.println(token);
        String userId = getUserId(token);
        System.out.println(userId);
    }
}