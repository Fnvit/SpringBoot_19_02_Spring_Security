package com.example.security.controller;

import com.example.security.dto.UserDTO;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired UserService userService;
    
    @GetMapping("/login")
    public void get_login(){

    }

    @GetMapping("/signup")
    public void get_signup(){

    }

    /**
     *  유저 회원가입 메서드
     * @param user HTML에서 받아온 정보를 가지는 UserDTO
     */
    @PostMapping("/signup")
    public String post_signup(UserDTO user){
        System.out.println("user: " + user);
        // 받아온 유저를 회원가입 시키기
        boolean result = userService.signup_user(user);
        // 회원가입 성공
        if(result){
            return "redirect:/user/login"; // 로그인 창으로 이동 (GET)
        }
        // 회원가입 실패
        return "redirect:/user/signup"; // 다시 회원가입창으로 이동 (GET)
    }
}
