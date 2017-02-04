package springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springmvc.model.UserAccount;
import springmvc.model.UserDTO;
import springmvc.security.service.SecurityContextService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SecurityContextService securityContextService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SecurityContextService securityContextService) {
        this.userRepository = userRepository;
        this.securityContextService = securityContextService;
    }

    @Override
    public Optional<UserDTO> findOne(Long id) {
        final UserAccount currentUser = securityContextService.currentUserAccount();
        final UserDTO user = new UserDTO(userRepository.findOne(id));
        if (currentUser == null) return Optional.empty();
        user.setMyself(user.getId() == currentUser.getId());
        return Optional.of(user);
    }

    @Override
    public Optional<UserDTO> findMe() {
        final UserAccount currentUser = securityContextService.currentUserAccount();
        return findOne(currentUser.getId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<UserAccount> user = userRepository.findOneByUsername(username);
        final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
        user.ifPresent(detailsChecker::check);
        return user.orElseThrow(() -> new UsernameNotFoundException("user not found."));
    }
}
