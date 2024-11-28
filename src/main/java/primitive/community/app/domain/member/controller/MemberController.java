package primitive.community.app.domain.member.controller;

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

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> registerMember(@RequestBody MemberDto memberDto) {
        try {
            memberService.registerMember(memberDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
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
