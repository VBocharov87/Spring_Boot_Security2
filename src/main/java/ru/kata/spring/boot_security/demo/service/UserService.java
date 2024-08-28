package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void saveUpdateUser(User user);

    void deleteUser(Long id);
}
