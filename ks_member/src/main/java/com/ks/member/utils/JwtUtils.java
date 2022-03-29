package com.ks.member.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ConfigurationProperties(prefix = "markerhub.jwt")
public class JwtUtils {

    @Value("${markerhub.jwt.secret}")
    private String secret;
    @Value("${markerhub.jwt.expire}")
    private long expire;
    @Value("${markerhub.jwt.header}")
    private String header;
    /**
     * 生成jwt token
     */
    public String generateToken(String userId) {
    Date newDate = new Date();
    Date expireDate = new Date(newDate.getTime()+expire*1000);
    return Jwts.builder()
            .setHeaderParam("typ","JWT")
            .setSubject(userId)
            .setIssuedAt(newDate)
            .setExpiration(expireDate)
            .signWith(SignatureAlgorithm.HS256,secret)
            .compact();
    }

    // 获取jwt的信息
    public Claims getClaimByToken(String token) {
    try{
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }catch (Exception e){
        e.printStackTrace();
        return null;
    }
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}

