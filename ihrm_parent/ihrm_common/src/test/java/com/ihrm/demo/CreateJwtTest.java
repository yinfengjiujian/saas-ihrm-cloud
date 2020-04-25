package com.ihrm.demo;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

/**
 * <p>Title: com.ihrm.demo</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/23 22:27
 * Description: No Description
 */
public class CreateJwtTest {

    /**
     * 创建token
     */
    @Test
    public void createToken() {
        JwtBuilder jwtBuilder = Jwts.builder().setId("8888").setSubject("小白")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "ihrm")
                .claim("companyId","123456")
                .claim("companyName","爱尔眼科集团");
        String token = jwtBuilder.compact();
        System.out.println(token);

    }
}
