package test.bci.com.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import test.bci.com.dto.UserDTO;
import test.bci.com.exceptions.UserAlreadyExistsException;
import test.bci.com.repositories.entities.Users;
import test.bci.com.services.UserService;
import test.bci.com.utils.EmailValidator;
import test.bci.com.utils.PasswordValidator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final EmailValidator emailValidator;

    /**
     * Metodo que retorna la
     * @return Lista de Usuarios
     */
    @GetMapping("/list")
    public List<UserDTO> findAll(){

        return  userService.findAll();
    }

    /**
     * Metodo que crea un usuario dado un body
     * de tipo JSON
     * @param user
     * @return
     */
    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody Users user,BindingResult result) {
        if(result.hasErrors()){
            return validate(result);
        }
        try {
            log.info("Validating email");
            if (!EmailValidator.isValidEmail(user.getEmail())) {
                return ResponseEntity.badRequest()
                        .body(Collections
                                .singletonMap("mensaje", "Formato de correo electrónico no válido"));
            }
            log.info("Validating password");
            if (!PasswordValidator.isValidPassword(user.getPassword())){
                return ResponseEntity.badRequest()
                        .body(Collections
                                .singletonMap("mensaje", "Password no cumple con el formato especificado"));
            }
            log.info("Call user.createUser()");
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Metodo que valida la entrada del objeto
     * y ademas retorna un mensaje de error
     * @param result
     * @return
     */
    private static ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String,String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(),"El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
