package springmvc.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import springmvc.model.UserDTO;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<UserDTO> findOne(Long id);

    Optional<UserDTO> findMe();
}
