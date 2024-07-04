package com.example.demo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JwtTokenFilter extends OncePerRequestFilter{

    private final JwtTokenUtil jwtTokenUtil;

    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String originalUri = request.getHeader("X-Original-URI");

        //인증이 필요없을 경우 그냥 보내줌
        if (originalUri != null && !originalUri.contains("/auth")) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/plain");
            response.getWriter().write(originalUri);
            return;
        }

        String token = request.getHeader("Authorization");

        System.out.println("Original token: " + token);
        System.out.println("Original Path: " + originalUri);

        // Header의 Authorization의 값이 비어있으면 오류
        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid Token1");
            return;
        }

        String jwtToken = token.replace("Bearer ", "");
        // 전송받은 Jwt Token이 만료되었으면 오류
        if (jwtTokenUtil.isExpired(jwtToken)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid Token1");
            return;
        }

        // Jwt Token에서 loginId 추출
        String loginId = jwtTokenUtil.getLoginId(jwtToken);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain");
        response.setHeader("X-Authoization-Id", loginId);
        response.getWriter().write("success");
        return;

        //filterChain.doFilter(request, response);
    }

}
