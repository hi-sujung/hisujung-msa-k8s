package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/oauth2/auth")
    public String authenticate() {
        return "성공";
    }

    @GetMapping("/oauth2/start")
    public void start2(HttpServletResponse response) throws IOException {
        String loginId = "20211149@sungshin.ac.kr";
        long expireTimeMs = 3600000;

        String token = jwtTokenUtil.createToken(loginId, expireTimeMs);
        response.getWriter().write(token);
    }
}

