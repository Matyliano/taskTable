package matyliano.repository;

import java.util.Optional;
import matyliano.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select concat( u.firstName, ' ', u.lastName) as fullName " +
            "from User u " +
            "where u.username like :username")
    Optional<String> findFullNameByUsername(String username);

    Optional<User> findByUsername(String username);

}
