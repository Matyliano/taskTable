package matyliano.repository;

import java.util.Optional;
import matyliano.entity.UserToken;
import org.springframework.stereotype.Service;

@Service
public interface UserTokenService  {
    Optional<UserToken> findUserTokenByUserToken(String userToken);

    void saveToken(UserToken userToken);

    void deleteToken(Long id);
}
