package com.ssafy.ssafit.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
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

    	String token = null;

    	// 1. Authorization 헤더 우선 (optional)
    	String authHeader = request.getHeader("Authorization");
    	if (authHeader != null && authHeader.startsWith("Bearer ")) {
    	    token = authHeader.substring(7);
    	}

    	// 2. 없으면 쿠키에서 accessToken 찾기
    	if (token == null && request.getCookies() != null) {
    	    for (Cookie cookie : request.getCookies()) {
    	        if ("accessToken".equals(cookie.getName())) {
    	            token = cookie.getValue();
    	            break;
    	        }
    	    }
    	}

    	// 3. 토큰 유효성 검증 및 인증 처리
    	if (token != null && jwtUtil.validateToken(token)) {
    	    String username = jwtUtil.extractUsername(token);
    	    String role = jwtUtil.extractRole(token);

    	    UsernamePasswordAuthenticationToken authentication =
    	        new UsernamePasswordAuthenticationToken(
    	            username,
    	            null,
    	            List.of(new SimpleGrantedAuthority("ROLE_" + role))
    	        );

    	    System.out.println("검증 성공");
    	    SecurityContextHolder.getContext().setAuthentication(authentication);
    	}

        // 다음 필터로 진행
        filterChain.doFilter(request, response);
    }
}
