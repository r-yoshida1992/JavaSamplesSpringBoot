package com.example.javasamplesspringboot.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class RequestMappingIntercepter {

    /**
     * クライアントの接続情報を出力する
     *
     * @param jp JoinPoint
     */
    @Before("execution(* com.example.javasamplesspringboot.controller.*Controller.*(..))")
    public void startAspect(JoinPoint jp) {
        // Access log
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = Objects.requireNonNull(servletRequestAttributes).getRequest();
        // IP Address, User-Agentを出力
        log.info(String.format("IP Address : %s, User-Agent : %s", getRemoteAddr(req), req.getHeader("User-Agent")));
        // 実行メソッドを出力
        log.info(String.format("start : %s", jp.getSignature().toString()));
    }

    /**
     * 実行メソッドを出力する
     *
     * @param jp JoinPoint
     */
    @After("execution(* com.example.javasamplesspringboot.controller.*Controller.*(..))")
    public void endAspect(JoinPoint jp) {
        // 実行メソッドを出力
        log.info(String.format("end : %s", jp.getSignature().toString()));
    }

    /**
     * クライアントのIPアドレスを取得
     *
     * @param request HttpServletRequest
     * @return ip address
     */
    private String getRemoteAddr(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        return xForwardedFor != null ? xForwardedFor : request.getRemoteAddr();
    }
}
