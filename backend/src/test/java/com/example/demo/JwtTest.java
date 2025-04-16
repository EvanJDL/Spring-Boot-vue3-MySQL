package com.example.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    private static final String KEY = "Evan";

    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims) // 放入业务数据
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // 12小时过期
                .sign(Algorithm.HMAC256(KEY)); // 用密钥签名
    }

    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token) // 校验签名、解密内容
                .getClaim("claims") // 取出 payload 中叫 "claims" 的数据
                .asMap(); // 转成 Map<String, Object>
    }


}
