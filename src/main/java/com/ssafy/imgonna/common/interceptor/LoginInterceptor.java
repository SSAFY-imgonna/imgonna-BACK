package com.ssafy.imgonna.common.interceptor;

import com.ssafy.imgonna.common.annotation.CheckToken;
import com.ssafy.imgonna.exception.member.UnauthorizedMemberException;
import com.ssafy.imgonna.util.JWTUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final String HEADER_AUTH = "Authorization";
    private JWTUtil jwtUtil;

    public LoginInterceptor(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        System.out.println("[interceptor] : " + requestURI + "(" + request.getMethod() + ")");


        // 컨트롤러에 @WithoutTokenCheck 어노테이션이 사용되었는지 체크
        CheckToken checkToken = ((HandlerMethod) handler).getMethodAnnotation(CheckToken.class);

//        if (handler != HandlerMethod) return true;

        if (checkToken != null) {
            // @WithOutAuth 없으면 인증 체크
            final String token = request.getHeader(HEADER_AUTH);

            if (token == null) {
                throw new UnauthorizedMemberException();

            }

            jwtUtil.isValidToken(token);
        }
        return true;
    }
}