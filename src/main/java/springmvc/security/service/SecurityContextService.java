package springmvc.security.service;

import springmvc.model.UserAccount;

public interface SecurityContextService {
    UserAccount currentUserAccount();
}
