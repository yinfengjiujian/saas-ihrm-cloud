package com.ihrm.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Test;

/**
 * <p>Title: com.ihrm.demo</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/23 22:36
 * Description: No Description
 */
public class ParseJwtTest {

    /**
     * 解析token
     */
    @Test
    public void parseToke() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODg4Iiwic3ViIjoi5bCP55m9IiwiaWF0IjoxNTg3NjUzMzY5LCJjb21wYW55SWQiOiIx" +
                "MjM0NTYiLCJjb21wYW55TmFtZSI6IueIseWwlOecvOenkembhuWboiJ9.mEMTR0EE35Wozcr8yXM4xyKLwFlk4DQ_hAm8ehYe9oM";
        Claims claims = Jwts.parser().setSigningKey("ihrm").parseClaimsJws(token).getBody();
        // 私有数据存放在  Claims对象中
        System.out.println(claims.getId());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getSubject());

        // 解析自定义中的claims中的内容
        String companyId = (String) claims.get("companyId");
        String companyName = (String) claims.get("companyName");

        System.out.println(companyId);
        System.out.println(companyName);

    }
}
