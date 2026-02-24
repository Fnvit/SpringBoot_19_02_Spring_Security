package com.example.security.configuration;

import com.example.security.dto.UserDTO;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(config -> config.disable());
//        http.httpBasic(Customizer.withDefaults());


        http.authorizeHttpRequests( (registry) -> {
            registry.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .requestMatchers("/user/login", "/user/signup", "/user/id/*").permitAll()
                    .anyRequest().authenticated();
        });


        http.formLogin((config) -> {
            config.loginPage("/user/login"); // FORM 로그인을 할 때 사용하는 Controller(html)경로
            config.usernameParameter("id"); // id 적는 input의 name 속성명 (username이 기본)
            config.passwordParameter("password"); // pw 적는 input의 name 속성명 (password가 기본)
            config.loginProcessingUrl("/user/login"); // 로그인 시도시, form 태그의 action 경로값
//            config.defaultSuccessUrl("/"); // 로그인이 성공 시 자동으로 이동하려는 GET 경로(Redirection)
            config.permitAll();
        });

        return http.build();
    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//        var userDetailsService = new InMemoryUserDetailsManager();
//        var user = UserDTO.builder()
//                .id("korea")
//                .password("1234")
//                .build();
//        userDetailsService.createUser(user);
////        userDetailsService.createUser(user2);
//        return userDetailsService;
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }
}
