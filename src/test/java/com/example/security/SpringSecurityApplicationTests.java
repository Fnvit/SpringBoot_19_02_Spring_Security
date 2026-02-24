package com.example.security;

import com.example.security.service.PortoneService;
import com.example.security.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityApplicationTests {
    @Autowired private PortoneService portoneService;

    @Test
    void contextLoads2() {
        int a = 10;
        int b = 20;

        Assertions.assertEquals(30, a + b);
        System.out.println("끝!");
    }

    @Test
    void contextLoads() {
        boolean result = portoneService.phone_certificate("imp_663291770969", "01012345678");
        assert result == true;
    }

}
