package com.ssafy.imgonna.common.interceptor;

import com.ssafy.imgonna.exception.member.UnauthorizedMemberException;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        System.out.println("[interceptor] : " + requestURI);
        HttpSession session = request.getSession(false);

        // 조회일 경우엔 권한 체크 안함
        if(HttpMethod.GET.name().equals(request.getMethod())) {
            return true;
        }

        // 로그인 안되어있을 때
        if (session == null || session.getAttribute("accessToken") == null) {
            throw new UnauthorizedMemberException();
        }

        // 로그인 되어있을 때
        return true;
    }

}