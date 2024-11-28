package primitive.community.app.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import primitive.community.app.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByStudentNumber(String studentNumber); // 학번으로 회원 검색
}