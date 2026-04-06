package com.ecommerce.auth_service.controller;

import com.ecommerce.auth_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        if (username.equals("admin") && password.equals("password")) {
            return jwtUtil.generateToken(username);
        }

        return "Invalid Credentials";
    }
}