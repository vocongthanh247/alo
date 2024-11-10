package base;

public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}

