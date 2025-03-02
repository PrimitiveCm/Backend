package primitive.community.app.domain.blacklistedToken.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import primitive.community.app.domain.blacklistedToken.entity.BlacklistedToken;

import java.util.Optional;


public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long> {
    Optional<BlacklistedToken> findByToken(String token);
}
