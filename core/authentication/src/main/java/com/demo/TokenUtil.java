package com.demo;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
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
                    .sign(algorithm);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("token生成失败");
        }
    }
    /**
     * 生成带类型的token
     * @param type 获取登入类型 {@link EnumToken}
     * @author lc
     * @date 2020/12/19
     */
    public static String getToken(@NotNull Serializable keyId,EnumToken type){
        try {
            Algorithm algorithm = getAlgorithm();
            return JWT.create().withKeyId(keyId.toString())
                    .withIssuer(issuer)
                    .withSubject(type.getType())
                    .sign(algorithm);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("token生成失败");
        }
    }

    /**
     * 构建加密对象
     * @author lc
     * @date 2021/4/17
     */
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
                .withIssuer(issuer)
                .withExpiresAt(time)//到期时间
                .sign(algorithm); //签名
    }

    /**
     * 可以设置带到期时间和带类型的token
     * @param keyId kid
     * @param dateField 调整的部分 {@link DateField}
     * @param offset 偏移时间
     * @param type 获取登入类型 {@link EnumToken}
     * @author lc
     * @date 2021/4/17
     */
    public static String getToken(@NotNull  Serializable keyId, DateField dateField,int offset,EnumToken type){
        DateTime time = DateUtil.date().offset(dateField, offset);
        Algorithm algorithm = getAlgorithm();
        return JWT.create().withKeyId(keyId.toString())
                .withIssuer(issuer)
                .withExpiresAt(time)//到期时间
                .withSubject(type.getType())
                .sign(algorithm); //签名
    }

    /**
     * 获取token的主题
     * @param token token
     * @author lc
     * @date 2021/4/17
     * @return subject
     */
    public static String getSubject(String token){
        return getDecodedJWT(token).getSubject();
    }


    /**
     * 解码token
     * @author lc
     * @date 2021/4/17
     */
    private static DecodedJWT getDecodedJWT(String token){
        try {
            Algorithm algorithm = getAlgorithm();
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            return verifier.verify(token);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("token解密失败");
        }
    }


    /**
     * 获取keyId
     * @author lc
     * @date 2020/12/19
     */
    public static String getKeyId(String token){
        return getDecodedJWT(token).getKeyId();
    }



    public static void main(String[] args) throws InterruptedException {
        //测试
        String token = getToken(1);
        System.out.println(token);
        String userId = getKeyId(token);
        System.out.println(userId);
        System.out.println("--------------------------过期时间--------------------------");

        token = getToken(2, DateField.SECOND, 10);
        System.out.println(token);
        userId = getKeyId(token);
        System.out.println(userId);
        DecodedJWT jwt = getDecodedJWT(token);
        //发行时间 和 过期时间
        System.out.println(DateUtil.format(jwt.getExpiresAt(), DatePattern.NORM_DATETIME_PATTERN));

    }
}
