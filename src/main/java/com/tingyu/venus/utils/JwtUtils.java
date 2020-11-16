package com.tingyu.venus.utils;

import com.tingyu.venus.common.BizCode;
import com.tingyu.venus.exception.ResultException;
import com.tingyu.venus.properties.JWTProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Author essionshy
 * @Create 2020/11/10 22:27
 * @Version venus-server
 *
 * JWT 由三部分组成，分别是头信息，载荷，签名
 *
 */

public final class JwtUtils {


    public static String create(Integer id,String username){

        String token= Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .setSubject("venus-server")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ JWTProperties.JWT_EXPIRE))

                .claim("id",id)
                .claim("username",username)

                .signWith(SignatureAlgorithm.HS256,JWTProperties.JWT_SECRET) //指定签名生成方式
                .compact();
        return token;
    }


    /**
     * 验证token
     * @param token
     */
    public static void validate(String token){
        if(StringUtils.isEmpty(token)){
            throw new ResultException(BizCode.USER_NOT_LOGIN.getCode(),BizCode.USER_NOT_LOGIN.getMessage());
        }

        try{

            Jwts.parser().setSigningKey(JWTProperties.JWT_SECRET).parse(token);

        }catch (Exception e){
            throw new ResultException(BizCode.TOKEN_EXPIRED.getCode(),BizCode.TOKEN_EXPIRED.getMessage());
        }
    }

    /**
     * 根据token获取用户的手机号码
     * @param token
     * @return
     */
    public static String getPhone(String token){
        String result="";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(JWTProperties.JWT_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        result= (String) claims.get("phone");
        return result;
    }

}
