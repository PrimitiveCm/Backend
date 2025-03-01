package primitive.community.app.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import primitive.community.app.domain.blacklistedToken.entity.service.BlacklistedTokenService;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Autowired
    private BlacklistedTokenService blacklistedTokenService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);

        if (blacklistedTokenService.isTokenBlacklisted(token)) {
            System.out.println("블랙리스트 토큰");
        }  else if (token != null && jwtTokenProvider.validateToken(token)) {
            String studentNumber = jwtTokenProvider.getStudentNumber(token);

            UserDetails userDetails = userDetailsService.loadUserByUsername(studentNumber);
            if (userDetails != null) {
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("성공");
            } else {
                System.out.println("에러");
            }
            /*Authentication authentication = new UsernamePasswordAuthenticationToken(studentNumber, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);*/
        } else {
            // 로그를 추가하여 토큰 추출 및 검증 상태를 확인합니다.
            System.out.println("Invalid or missing JWT token, token : " + token);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}