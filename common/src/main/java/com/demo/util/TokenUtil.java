package com.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.exception.ServiceException;

import java.io.Serializable;

/**
 * @author root
 * @description TODO
 * @date 2020/12/18 16:48
 */
public class TokenUtil  {
    public static String secretKey = "izVguZPRsBQ5Rqw6dhMvcIwy8_9lQnrO3vpxGwP";

    public static String getToken(Serializable userId){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new ServiceException("token生成失败");
        }
    }
    public static String getUserId(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException exception){
            throw new ServiceException("token解密失败");
        }
    }

    public static void main(String[] args) {
        String token = getToken(1);
        System.out.println(token);
        String userId = getUserId(token);
        System.out.println(userId);
    }
}
