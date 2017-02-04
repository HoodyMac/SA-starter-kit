package springmvc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Size;

public final class UserParams {

    private final String username;
    @Size(min = 3, max = 100)
    private final String password;
    private final String name;

    public UserParams(@JsonProperty("username") String username,
                      @JsonProperty("password") String password,
                      @JsonProperty("name") String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public UserAccount toUser() {
        UserAccount user = new UserAccount();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setName(name);
        return user;
    }

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

}
