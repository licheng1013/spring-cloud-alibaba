package com.demo;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.istack.internal.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * @author root
 * @description TODO
 * @date 2020/12/18 16:48
 */
public class TokenUtil {

    /** 密钥 和 发行人 **/
    private static final String secretKey = "izVguZPRsBQ5Rqw6dhMvcIwy8_9lQnrO3vpxGwP";
    private static final String issuer = "auth-token";



    private static JWTCreator.Builder createToken(String keyId) {
        return JWT.create().withKeyId(keyId).withIssuer(issuer);
    }

    private static JWTCreator.Builder createToken(String keyId, EnumToken type) {
        return createToken(keyId).withSubject(type.getType());
    }

    private static JWTCreator.Builder createToken(String keyId, EnumToken type, Date date) {
        return createToken(keyId, type).withExpiresAt(date);
    }

    /**
     * 获取token的主题
     */
    public static String getSubject(String token) {
        return getDecodedJWT(token).getSubject();
    }

    /**
     * 构建加密对象
     */
    private static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secretKey);
    }


    /**
     * 解码token
     */
    private static DecodedJWT getDecodedJWT(String token) {
        Algorithm algorithm = getAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
        return verifier.verify(token);
    }


    /**
     * 获取keyId
     * @date 2020/12/19
     */
    public static String getKeyId(String token) {
        return getDecodedJWT(token).getKeyId();
    }

    /**
     * 生成token
     */
    public static String getToken(Serializable keyId) {
        return createToken(keyId.toString()).sign(getAlgorithm());
    }

    /**
     * 生成带类型的token
     * @param type 获取登入类型 {@link EnumToken}
     * @date 2020/12/19
     */
    public static String getToken(@NotNull Serializable keyId, EnumToken type) {
        return createToken(keyId.toString(), type).sign(getAlgorithm());
    }

    /**
     * 可以设置带到期时间和带类型的token
     * @param keyId     kid
     * @param dateField 调整的部分 {@link DateField}
     * @param offset    偏移时间
     * @param type      获取登入类型 {@link EnumToken}
     * @date 2021/4/17
     */
    public static String getToken(@NotNull Serializable keyId, DateField dateField, int offset, EnumToken type) {
        DateTime time = DateUtil.date().offset(dateField, offset);
        Algorithm algorithm = getAlgorithm();
        return createToken(keyId.toString(), type, time).sign(algorithm); //签名
    }

    public static void main(String[] args) throws InterruptedException {
        //测试
        String token = getToken("1");
        System.out.println(token);
        String userId = getKeyId(token);
        System.out.println(userId);
        System.out.println("--------------------------过期时间示例--------------------------");
        token = getToken(2, DateField.SECOND, 10, EnumToken.WEB);
        System.out.println(token);
        userId = getKeyId(token);
        System.out.println(userId);
        DecodedJWT jwt = getDecodedJWT(token);
        //发行时间 和 过期时间
        System.out.println(DateUtil.format(jwt.getExpiresAt(), DatePattern.NORM_DATETIME_PATTERN));

    }
}
