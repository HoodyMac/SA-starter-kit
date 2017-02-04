package sa.starter.kit.security.service;

import sa.starter.kit.user.domain.UserAccount;

public interface SecurityContextService {
    UserAccount currentUserAccount();
}
