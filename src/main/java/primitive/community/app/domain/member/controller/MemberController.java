package primitive.community.app.domain.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.http11.upgrade.UpgradeProcessorInternal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import primitive.community.app.domain.blacklistedToken.entity.service.BlacklistedTokenService;
import primitive.community.app.domain.member.dto.MemberDto;
import primitive.community.app.domain.member.entity.Member;
import primitive.community.app.domain.member.repository.MemberRepository;
import primitive.community.app.domain.member.service.MemberService;
import primitive.community.app.security.JwtAuthenticationFilter;
import primitive.community.app.security.JwtTokenProvider;
import primitive.community.app.security.principal.MemberPrincipal;

import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BlacklistedTokenService blacklistedTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private MemberRepository memberRepository;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/ok")
    public String test() {
        logger.debug("test");
        return "test";

    }

    /**
     * jwt 토큰으로 로그인
     * <p>
     * complete)
     * 회원가입
     * -> 토큰을 return
     * <p>
     * <p>
     * TODO)
     * 로그인,
     * -> 사용자 OK -> 토큰 return or 응답 헤더에 jwt 토큰 추가
     * <p>
     * 로그아웃
     * -> 토큰 만료 (응답 헤더에 토큰 제거)
     */

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> registerMember(@RequestBody MemberDto memberDto) {

        logger.debug("memberDto : " + memberDto.toString());

        try {
            String token = memberService.registerMember(memberDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginMember(@RequestBody MemberDto memberDto) {
        logger.debug("memberDto : " + memberDto.toString());
        Optional<Member> findMember = memberRepository.findByStudentNumber(memberDto.getStudentNumber());
        if (findMember.isPresent() && passwordEncoder.matches(memberDto.getPassword(), findMember.get().getPassword())) {
            String token = jwtTokenProvider.createToken(findMember.get().getStudentNumber());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token, @RequestBody MemberDto memberDto, HttpServletRequest request) {


        System.out.println("memberDto : " + memberDto.toString());
        System.out.println("token : " + token);

        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                Date expirationDate = jwtTokenProvider.extractClaims(token).getExpiration();
                blacklistedTokenService.blacklistToken(token, expirationDate);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body("토큰 무효화 완료\n" + token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 학번으로 회원 조회
    @GetMapping("/{studentNumber}")
    public ResponseEntity<Member> getMemberByStudentNumber(@PathVariable String studentNumber) {
        Optional<Member> member = memberService.findMemberByStudentNumber(studentNumber);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/info")
    public String getMemberInfo(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        System.out.println(memberPrincipal.getUsername().toString());
        return "ok";
    }
}
