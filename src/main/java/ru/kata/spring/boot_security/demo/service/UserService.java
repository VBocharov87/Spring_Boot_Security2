package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(User user, List<Long> roles);

    List<User> getAllUsers();

    void updateUser(User user, List<Long> roles);

    void deleteUser(Long id);
}
