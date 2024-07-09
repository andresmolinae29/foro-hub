package forohub.com.api.domain.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse createUser(String user, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(user, encodedPassword);
        userRepository.save(newUser);
        return UserResponse.fromUser(newUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
