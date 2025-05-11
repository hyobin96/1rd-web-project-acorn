package com.ssafy.ssafit.config;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	// 서명에 사용할 key 객체
	private final Key key;
	
    // 토큰 만료 시간: 1시간
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;
    
    // 시크릿 키 주입
    public JwtUtil(@Value("${jwt.secret}") String secretKey) {
    	// 시크릿 키 객체 생성 (HMAC-SHA용 키)
    	this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}
    


    /**
     * ✅ 토큰 생성
     * @param username 사용자 식별자 (예: ID)
     * @return JWT 문자열
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)                      // 토큰의 주제: username
                .setIssuedAt(new Date())                   // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 만료 시간
                .signWith(key, SignatureAlgorithm.HS256)   // HMAC-SHA256 서명
                .compact();
    }

    /**
     * ✅ 토큰에서 사용자 이름 추출
     * @param token JWT
     * @return 사용자 이름
     */
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * ✅ 토큰이 만료되었는지 확인
     */
    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    /**
     * ✅ 토큰이 유효한지 확인
     * @param token JWT
     * @param expectedUsername 예상되는 사용자 이름
     * @return 유효성 여부
     */
    public boolean validateToken(String token, String expectedUsername) {
        final String username = extractUsername(token);
        return username.equals(expectedUsername) && !isTokenExpired(token);
    }

    /**
     * ✅ 내부에서 사용하는 Claims 추출 로직
     */
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
