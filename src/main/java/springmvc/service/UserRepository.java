package springmvc.service;

import org.springframework.data.jpa.repository.JpaRepository;
import springmvc.model.UserAccount;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findOneByUsername(String username);

}
