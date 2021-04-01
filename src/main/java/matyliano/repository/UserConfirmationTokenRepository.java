package matyliano.repository;


import java.util.Optional;
import matyliano.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConfirmationTokenRepository extends JpaRepository<UserToken, Long> {
    @Query("select t from UserToken t where t.userToken=:token")
    Optional<UserToken> findUserTokenByToken(String token);
}
