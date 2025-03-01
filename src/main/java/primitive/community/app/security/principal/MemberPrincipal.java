package primitive.community.app.security.principal;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import primitive.community.app.domain.member.entity.Member;

import java.util.Collection;
import java.util.Collections;

@Getter
public class MemberPrincipal implements UserDetails {
    private final Member member;

    public MemberPrincipal(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getStudentNumber(); // 학번을 username으로 사용
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return member.isStatus();
    }
}