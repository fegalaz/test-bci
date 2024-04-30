package test.bci.com.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.bci.com.exceptions.UserAlreadyExistsException;
import test.bci.com.repositories.UserRepository;
import test.bci.com.repositories.entities.Users;
import test.bci.com.services.UserService;
import test.bci.com.utils.GenerateToken;
import test.bci.com.utils.PasswordValidator;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenerateToken generateToken;

    @Autowired
    private PasswordValidator passwordValidator;


    /**
     * Metodo que persiste un usuario
     * en la BD , y ademas valida si el email
     * ya existe anteriormente.
     * @param user
     * @return
     */
    @Override
    @Transactional
    public Users createUser(Users user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("El correo existe anteriormente");
        }
        user.setPassword(passwordValidator.encriptarPassword(user.getPassword()));
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(GenerateToken.getToken());
        user.setActive(true);

        return userRepository.save(user);
    }

    /**
     * Metodo que retorna la lista de usuario
     * @return List<Users>
     */
    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }
}
