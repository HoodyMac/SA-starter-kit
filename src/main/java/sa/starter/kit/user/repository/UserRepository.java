package sa.starter.kit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.starter.kit.user.domain.UserAccount;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findOneByUsername(String username);
}
