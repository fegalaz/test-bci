package test.bci.com.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ToString
@Getter
@Setter
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    @NotBlank
    private String email;

    @NotBlank
    @NotEmpty
    private String password;

    private LocalDateTime created;

    private LocalDateTime modified;

    private LocalDateTime lastLogin;

    private String token;

    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Phones> phones;
}
