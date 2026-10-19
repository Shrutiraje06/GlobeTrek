package com.indiatour.etour;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("12345 = " + encoder.encode("12345"));
        System.out.println("rahul123 = " + encoder.encode("rahul123"));
        System.out.println("neha123 = " + encoder.encode("neha123"));
        System.out.println("priya123 = " + encoder.encode("priya123"));
    }
}