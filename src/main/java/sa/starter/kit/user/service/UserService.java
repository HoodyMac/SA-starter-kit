package sa.starter.kit.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import sa.starter.kit.user.model.UserDTO;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<UserDTO> findOne(Long id);

    Optional<UserDTO> findMe();
}
