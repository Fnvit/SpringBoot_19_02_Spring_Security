package com.example.security.handler;

import com.example.security.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CustomOAuth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("등록이 안된 사용자가 OAuth2 로그인을 시도함");
        // 로그인창으로 보내면서 안내 문구 함께 전달
        String message = "연동된 사용자 정보가 없습니다. 회원가입 후 회원 마이페이지에서 SNS 연동을 진행해주세요.";
        message = URLEncoder.encode(message, StandardCharsets.UTF_8);
        response.sendRedirect("/user/login?message=" + message);
    }
}











