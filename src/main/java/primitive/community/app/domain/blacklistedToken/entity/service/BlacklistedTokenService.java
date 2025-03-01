package primitive.community.app.domain.blacklistedToken.entity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import primitive.community.app.domain.blacklistedToken.entity.BlacklistedToken;
import primitive.community.app.domain.blacklistedToken.entity.repository.BlacklistedTokenRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BlacklistedTokenService {

    private final BlacklistedTokenRepository blacklistedTokenRepository;

    @Transactional
    public void blacklistToken(String token, Date expirationDate) {
        BlacklistedToken blacklistedToken = new BlacklistedToken();
        blacklistedToken.setToken(token);
        blacklistedToken.setExpirationDate(expirationDate);
        blacklistedTokenRepository.save(blacklistedToken);
    }

    @Transactional(readOnly = true)
    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokenRepository.findByToken(token).isPresent();
    }
}
