package primitive.community.app.domain.member.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import primitive.community.app.domain.member.entity.Member;
import primitive.community.app.domain.member.repository.MemberRepository;
import primitive.community.app.security.principal.MemberPrincipal;

@Service
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public MemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String studentNumber) throws UsernameNotFoundException {
        Member member = memberRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + studentNumber));

        return new MemberPrincipal(member); // ✅ MemberPrincipal은 UserDetails를 구현해야 합니다.
    }
}