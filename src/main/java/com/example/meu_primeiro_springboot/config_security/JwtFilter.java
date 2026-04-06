package com.example.meu_primeiro_springboot.config_security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {
    JwtUtil jwtUtil;
    @Autowired
    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String filterAccess = request.getHeader("Authorization");
        if(filterAccess != null && filterAccess.startsWith("Bearer ")){
            String extractedToken = filterAccess.substring(7);
            if(jwtUtil.validateToken(extractedToken)){
                String extractedUsername = jwtUtil.extractUsername(extractedToken);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(extractedUsername, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
