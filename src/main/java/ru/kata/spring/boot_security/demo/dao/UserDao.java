package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserDao {

    void addUser(User user);
    void deleteUser(User user);
    void deleteUser(Long id);
    void modifyUser(User user);
    User findUserById(Long id);
    User findUserByUsername(String nickName);
    List<User> getUsersList();
}
