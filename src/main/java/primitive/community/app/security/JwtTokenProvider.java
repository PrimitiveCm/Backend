package primitive.community.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String SECRET_KEY = "your-256-bit-long-secret-key-here";
    private static final String BASE64_ENCODED_SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());  // Base64로 인코딩된 키
    private static final long EXPIRATION_TIME = 86400000; // 1일 (밀리초 단위)

    // 토큰 생성
    public String createToken(String studentNumber) {
        /*Claims claims = Jwts.claims().setSubject(studentNumber);
        Date now = new Date();
        Date validity = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();*/

        Claims claims = Jwts.claims().setSubject(studentNumber);
        Date now = new Date();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, BASE64_ENCODED_SECRET_KEY)
                .compact();
        return token;
    }

    // 토큰 검증
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(BASE64_ENCODED_SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // 토큰에서 사용자 이름 추출
    public static String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(BASE64_ENCODED_SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getStudentNumber(String token) {
        return Jwts.parser()
                .setSigningKey(BASE64_ENCODED_SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}