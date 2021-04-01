package matyliano.service;

import java.util.Optional;
import matyliano.entity.UserToken;
import matyliano.repository.UserConfirmationTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class UserTokenService {

    private final UserConfirmationTokenRepository userTokenRepository;

    public UserTokenService(UserConfirmationTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }


    public void saveToken(UserToken userToken) {
        userTokenRepository.save(userToken);
    }


    public void deleteTokenById(Long id) {
        userTokenRepository.deleteById(id);
    }


    public Optional<UserToken> findTokenByToken(String userToken) {
        return userTokenRepository.findUserTokenByToken(userToken);
    }
}
