package com.example.security.handler;

import com.example.security.dto.SNSUserDTO;
import com.example.security.dto.UserDTO;
import com.example.security.mapper.UserMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Component
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 현재 principal이 UserDTO라면, SNS 연동 여부를 확인한 후, SNS 연동을 시킨다.
        Object principal = authentication.getPrincipal();
        System.out.println("principal : " + principal);
        SNSUserDTO snsUser = (SNSUserDTO) principal;
        System.out.println("oAuth2User 입니다");

        switch (snsUser.getSns().toUpperCase()){
            case "NAVER":
                UserDTO user = userMapper.selectUserBySNSId(snsUser.getId());
                if(user == null){
                    System.out.println("User not found");
                    throw new OAuth2AuthenticationException("ACCESS_DENIED");
                }
                break;
            case "GOOGLE":
                break;
            default:
                break;
        }

        if(principal instanceof UserDTO){
            // 로그인 된 유저를 가져옴
            UserDTO user = (UserDTO) principal;
            System.out.println("user 입니다");
            // SNS 연동 확인 (DB)
            UserDTO user = userMapper.selectUserBySNSId(snsUser.getId());
            if(user == null){
                System.out.println("User not found");
                // INSERT 작업
            }
        }
        // 로그인 창에서 OAuth2 로그인 했을 시
        else if(principal instanceof OAuth2User){

        }

        response.sendRedirect("/user/mypage/info");
    }
}











