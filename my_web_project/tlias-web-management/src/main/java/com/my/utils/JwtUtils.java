package com.my.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "myjwt";
    private static Long expire = 43200000L;

    /**
     * generate Jwt
     */
    public static String GenerateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, signKey) //签名算法和签名
                .addClaims(claims) //自定义内容（载荷）
                .setExpiration(new Date(System.currentTimeMillis() + expire)) //设置令牌有效期为1h
                .compact();//生成字符串类型返回值
        return jwt;
    }

    /**
     * analysis JWT
     */
    public static Claims testParseJwt(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey) //设置签名
                .parseClaimsJws(jwt) //输入令牌
                .getBody(); //得到第二部分内容
        return claims;
    }
}
