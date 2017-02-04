package springmvc.model;

public class UserDTO {

    private final UserAccount user;

    public UserDTO(UserAccount user) {
        this.user = user;
    }

    private Boolean isMyself = null;

    public long getId() {
        return user.getId();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getName() {
        return user.getName();
    }

    public Boolean getMyself() {
        return isMyself;
    }

    public void setMyself(Boolean myself) {
        isMyself = myself;
    }
}
