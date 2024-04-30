package test.bci.com;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import test.bci.com.controllers.UserController;
import test.bci.com.repositories.entities.Phones;
import test.bci.com.repositories.entities.Users;
import test.bci.com.services.UserService;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testCreateUser() throws Exception {
        Users user = createUserObject();
        Mockito.when(userService.createUser(Mockito.any(Users.class))).thenReturn(user);

        mockMvc.perform(post("/api/users/create")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Juan Rodriguez"))
                .andExpect(jsonPath("$.email").value("juan@rodriguez.org"))
                .andExpect(jsonPath("$.phones").isArray());
        
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
