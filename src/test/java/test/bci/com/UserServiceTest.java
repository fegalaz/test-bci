package test.bci.com;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import test.bci.com.exceptions.UserAlreadyExistsException;
import test.bci.com.repositories.UserRepository;
import test.bci.com.repositories.entities.Phones;
import test.bci.com.repositories.entities.Users;
import test.bci.com.services.UserService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void testCreateUser_Success() {
        Users user = createUserObject();
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.save(Mockito.any(Users.class))).thenReturn(user);

        Users createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(user.getName(), createdUser.getName());
    }

    @Test
     void testCreateUser_UserAlreadyExistsException() {
    	Users user = createUserObject();
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(user));
    }

    private Users createUserObject() {
    	Users user = new Users();
        user.setName("Juan Rodriguez");
        user.setEmail("juan@rodriguez.org");
        user.setPassword("hunter2");
        user.setPhones(Collections.singletonList(new Phones()));

        return user;
    }
}
