package com.example.security.service;

import com.example.security.dto.SNSUserDTO;
import com.example.security.dto.UserDTO;
import com.example.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

// OAuth2 로그인을 시도하면 loadUser 메서드가 자동으로 실행됩니다!!
// OAuth2User 의 형태가 spring security oauth2가 사용하는 User의 형태입니다.
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired UserMapper userMapper;
    
    // userRequest 에는 application.properties 에 등록해놓은 내용들이 들어있음
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String snsName = userRequest.getClientRegistration().getClientName().toUpperCase(); // 로그인 한 SNS 의 종류 (NAVER, KAKAO, GOOGLE)
        System.out.println("로그인한 SNS: " +snsName);
        OAuth2User oAuth2User = super.loadUser(userRequest); // application.properties에 등록해놓은 내용을 통해 OAuth2 로그인을 실제로 진행함
        System.out.println("OAuth2User: " + oAuth2User);
        Map<String, Object> attributes = oAuth2User.getAttributes(); // 로그인 완료 후 동의항목들을 가져옴
        switch (snsName){
            case "NAVER":
                Map<String, Object> response = (Map<String, Object>) attributes.get("response");
                String snsId = response.get("id").toString();
                SNSUserDTO snsUserDTO = SNSUserDTO.builder()
                        .id(snsId).sns(snsName).attributes(response).build();
                return snsUserDTO;
//                user = userMapper.selectUserBySNSId(snsId);
//                if(user == null){
//                    System.out.println("User not found");
//                    throw new OAuth2AuthenticationException("ACCESS_DENIED");
//                }
            case "GOOGLE":
                break;
            default:
                throw new OAuth2AuthenticationException("ACCESS_DENIED");
        }

        return oAuth2User;
    }
}










