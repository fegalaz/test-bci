package test.bci.com.services;

import test.bci.com.repositories.entities.Users;

import java.util.List;


public interface UserService {
    Users createUser(Users user);

    List<Users> findAll();
}
