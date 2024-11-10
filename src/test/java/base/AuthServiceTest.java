package base;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AuthServiceTest {
    private UserRepository userRepository;
    private AuthService authService;

    @Before
    public void setUp() {
        userRepository = mock(UserRepository.class); // Ensure UserRepository is mockable
        authService = new AuthService(userRepository);
    }

    @Test
    public void testLoginSuccess() {
        User user = new User("testUser", "password123");
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        boolean result = authService.login("testUser", "password123");

        assertTrue(result);
    }

    @Test
    public void testLoginFailure() {
        when(userRepository.findByUsername("unknownUser")).thenReturn(null);

        boolean result = authService.login("unknownUser", "password123");

        assertFalse(result);
    }
}
