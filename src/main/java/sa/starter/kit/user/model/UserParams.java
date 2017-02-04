package sa.starter.kit.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sa.starter.kit.user.domain.UserAccount;

import javax.validation.constraints.Size;

public final class UserParams {

    private final String username;
    @Size(min = 3, max = 100)
    private final String password;

    public UserParams(@JsonProperty("username") String username,
                      @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public UserAccount toUser() {
        UserAccount user = new UserAccount();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        return user;
    }

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

}
