package primitive.community.app.domain.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import primitive.community.app.domain.member.dto.MemberDto;
import primitive.community.app.domain.member.entity.Member;
import primitive.community.app.domain.member.service.MemberService;

import java.util.Optional;


@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

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
     *
     * complete)
     * 회원가입
     * -> 토큰을 return
     *
     *
     * TODO)
     * 로그인,
     * -> 사용자 OK -> 토큰 return or 응답 헤더에 jwt 토큰 추가
     *
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

    // 학번으로 회원 조회
    @GetMapping("/{studentNumber}")
    public ResponseEntity<Member> getMemberByStudentNumber(@PathVariable String studentNumber) {
        Optional<Member> member = memberService.findMemberByStudentNumber(studentNumber);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
