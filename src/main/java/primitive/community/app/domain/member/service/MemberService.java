package primitive.community.app.domain.member.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import primitive.community.app.domain.member.dto.MemberDto;
import primitive.community.app.domain.member.entity.Member;
import primitive.community.app.domain.member.repository.MemberRepository;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Member registerMember(MemberDto memberDto) {
        // 학번 중복 체크
        if (memberRepository.findByStudentNumber(memberDto.getStudentNumber()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 학번입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());

        // Member 객체 생성 및 저장
        // TODO 빌더 패턴으로 변경
        Member member = new Member();
        member.setStudentNumber(memberDto.getStudentNumber());
        member.setUserName(memberDto.getUserName());
        member.setPassword(encodedPassword);


        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findMemberByStudentNumber(String studentNumber) {
        return memberRepository.findByStudentNumber(studentNumber);
    }
}