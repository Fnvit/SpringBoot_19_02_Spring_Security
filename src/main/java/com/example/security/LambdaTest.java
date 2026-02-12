package com.example.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@FunctionalInterface
interface Calc{
    void add(int a, int b);
}

public class LambdaTest {
    public static void main(String[] args) {
        var encoded = new BCryptPasswordEncoder().encode("123");
        System.out.println(encoded);
//        Calc calc = new Calc() {
//            @Override
//            public void add(int a) {
//                System.out.println(a);
//            }
//        };
//
//        Calc cc = System.out::println;
//        cc.add(1);
    }
}
