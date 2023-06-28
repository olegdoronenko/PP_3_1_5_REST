package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService{

    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public void modifyUser(User user) {
        userDao.modifyUser(user);
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Override
    public User findUserByUsername(String nickName) {
        return userDao.findUserByUsername(nickName);
    }

    @Override
    public User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return findUserByUsername(auth.getName());
    }

    @Override
    public List<User> getUsersList() {
        return userDao.getUsersList();
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {

        User user = findUserByUsername(nickName);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", nickName));
        }

        return user;

    }
}
