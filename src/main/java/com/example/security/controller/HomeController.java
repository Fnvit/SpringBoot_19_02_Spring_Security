package com.example.security.controller;

import com.example.security.dto.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/user")
    public String user(
            Authentication authentication,
            Principal p,
            @AuthenticationPrincipal UserDTO user
            ){
        var name = authentication.getName();
        var principal = authentication.getPrincipal();
        var isAuth = authentication.isAuthenticated();
        var credentials = authentication.getCredentials();

        System.out.println("name: " + name);
        System.out.println("principal: " + principal);
        System.out.println("isAuth: " + isAuth);
        System.out.println("credentials: " + credentials);
        System.out.println("p: " + p);


        return "user";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
