package com.ssafy.ssafit.config;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    // 생성자 주입
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // "Bearer <token>" 형식인지 확인
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // "Bearer " 제거
            String tokenUsername = jwtUtil.extractUsername(token);
            
            // 클라이언트가 요청에 포함한 userId 또는 username
            String pathUsername = request.getParameter("username");

            if (!tokenUsername.equals(pathUsername)) {
                throw new AccessDeniedException("다른 사용자의 요청입니다.");
            }
            
            if (jwtUtil.validateToken(token, pathUsername)) {
                String username = jwtUtil.extractUsername(token);
                String role = jwtUtil.extractRole(token);  // "USER", "ADMIN" 등

                // 인증 객체 생성 (권한도 함께 설정)
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
                        );

                // SecurityContextHolder에 인증 정보 등록
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 다음 필터로 진행
        filterChain.doFilter(request, response);
    }
}
