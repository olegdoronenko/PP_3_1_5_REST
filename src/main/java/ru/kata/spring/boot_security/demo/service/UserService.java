package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(User user);
    void deleteUser(User user);
    void deleteUser(Long id);
    void modifyUser(User user);
    User findUserById(Long id);
    User findUserByUsername(String nickName);
    User getAuthUser();
    List<User> getUsersList();
}
