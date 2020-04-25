package com.aier.ihrm.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;
import java.util.Map;

/**
 * <p>Title: com.aier.ihrm.common.utils</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/23 22:54
 * Description: No Description
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt.config")
public class JWTUtils {
    // 签名的私钥
    private String key;
    // 设置签名的失效时间
    private Long ttl;

    /***
     * 设置认证token
     *    id : 登录用的id
     *    subject:登录用户名
     */
    public String createJWT(String userId, String name, Map<String,Object> map) {
        // 1、设置失效时间
        long timeMillis = System.currentTimeMillis();
        long exp = timeMillis + ttl;
        // 2、创建jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(userId).setSubject(name)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key);
        // 3、根据map设置claims
        for (Map.Entry<String,Object> entry : map.entrySet()) {
            jwtBuilder.claim(entry.getKey(),entry.getValue());
        }
        // 4、创建token
        jwtBuilder.setExpiration(new Date(exp));

        return jwtBuilder.compact();
    }


    /**
     * 解析Token字符串获取claims中自定义的内容
     */
    public Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
