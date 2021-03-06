package matyliano.repository;

import java.util.Optional;
import matyliano.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c where c.clientName=:clientName")
    Optional<Client> findClientByClientName(String clientName);
}
