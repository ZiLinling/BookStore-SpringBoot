package com.edu.bookstore.Utils;


import com.edu.bookstore.Exception.tokenException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception{
        String token = req.getHeader("token");
        //在拦截器中设置允许跨域(拦截器需设置跨域)

        try{
            JwtUtil.checkToken(token);
            return true;
        }catch(Exception e){
            throw new tokenException();
        }
    }
}