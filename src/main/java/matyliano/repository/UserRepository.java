package matyliano.repository;

import java.util.Optional;
import javax.transaction.Transactional;
import matyliano.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select concat( u.firstName, ' ', u.lastName) as fullName " +
            "from User u " +
            "where u.username like :username")
    Optional<String> findFullNameByUsername(String username);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    @Transactional
    void deleteByUsername(String username);

}
