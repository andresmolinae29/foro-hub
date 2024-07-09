package forohub.com.api.domain.users;

public record UserResponse(Long id, String username) {

    public static UserResponse fromUser(User user) {
        return new UserResponse(user.getId(), user.getUsername());
    }
}
