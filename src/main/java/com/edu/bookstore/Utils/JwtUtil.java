package com.edu.bookstore.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.edu.bookstore.entity.User;

import java.util.Date;

public class JwtUtil {
    private static Algorithm algorithm = Algorithm.HMAC256("asdwqewqhidhwhdqwh");
    static public String generateToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60*60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create()
                .withClaim("userid",user.getId())
//                .withClaim("username",user.getUsername())
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(algorithm);
        return token;
    }

    static public Integer parseToken( String token ) {
        JWTVerifier jwtVerifier=JWT.require(algorithm).build();
        //        String s=jwtVerifier.verify(token).getClaims().toString();
//        System.out.println(s);
        return jwtVerifier.verify(token).getClaim("userid").asInt();
    }

    static public void checkToken(String token){
        if(token==null)
            token="";
        JWTVerifier jwtVerifier=JWT.require(algorithm).build();
        jwtVerifier.verify(token);
    }
}