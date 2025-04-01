package primitive.community.app.domain.member.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import primitive.community.app.domain.member.dto.MemberDto;
import primitive.community.app.domain.member.entity.Member;
import primitive.community.app.domain.member.repository.MemberRepository;
import primitive.community.app.domain.role.RoleType;
import primitive.community.app.security.JwtTokenProvider;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    public String registerMember(MemberDto memberDto) {
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
        member.setRoleType(RoleType.GUEST.toString());
        memberRepository.save(member);

        return jwtTokenProvider.createToken(member.getStudentNumber());
    }

    @Transactional(readOnly = true)
    public Member findMemberByStudentNumber(String studentNumber) {
        return memberRepository.findByStudentNumber(studentNumber).orElseThrow(() -> new RuntimeException("없음"));
    }

    @Transactional
    public void approveNewMember(Member member) {
        member.setRoleType(RoleType.USER.toString());
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public List<MemberDto> findApproveMemberList() {

        return memberRepository.findByRoleType(RoleType.GUEST.toString())
                .stream()
                .map(member -> MemberDto.builder()
                        .studentNumber(member.getStudentNumber())
                        .userName(member.getUserName())
                        .build()
                )
                .collect(Collectors.toList());


    }
}