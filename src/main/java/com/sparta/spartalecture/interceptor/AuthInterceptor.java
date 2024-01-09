package com.sparta.spartalecture.interceptor;

import com.sparta.spartalecture.user.entity.UserRoleEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.file.AccessDeniedException;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        UserRoleEnum role = (UserRoleEnum) request.getAttribute("role");

        if(!role.equals((UserRoleEnum.ADMIN))){
            throw new AccessDeniedException("관리자 권한이 필요합니다.");
        }
        return true;
    }
}
