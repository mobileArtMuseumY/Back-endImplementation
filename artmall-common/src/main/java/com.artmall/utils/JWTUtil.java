package com.artmall.utils;


import com.artmall.response.Const;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * jwt验证与签发
 * 工具类
 *
 * @author
 * @create 2018-08-10 9:13
 **/

public class JWTUtil {

    public static final String SECRET_KEY = "?::4343fdf4fdf6cvflalalala):";
    // 过期时间1天
    private static final long EXPIRE_TIME = 24*60*60*1000;

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param userId 用户id
//     * @param  loginType 用户类型
     * @return 是否正确
     */
    public static boolean verify(String token, Long userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userId", userId)
//                    .withClaim("userType", String.valueOf(loginType))
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 解析JWT的Payload
     * @return token中包含的用户编号
     */
    public static Long getUserNo(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    /**
     * 解析JWT的Payload
     * @return token中此用户的UserType
     */
    public static String getUserType(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userType").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,指定时间后过期,一经生成不可修改，令牌在指定时间内一直有效
     * @param userId 用户ID
     * @param  userType 用户类型
     * @return 加密的token
     */
    public static String sign(Long userId, Const.LoginType userType) {
        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        // 附带username信息
        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("userType", String.valueOf(userType))
                .withExpiresAt(date)
                .sign(algorithm);

    }

}
