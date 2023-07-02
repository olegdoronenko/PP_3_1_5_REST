package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService{

    private final UserDao userDao;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserDao userDao, UserRepository userRepository) {
        this.userDao = userDao;
        this.userRepository = userRepository;
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
        User currentUser = userRepository.findById(user.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            currentUser.setEmail(user.getEmail());
        }
        if (user.getAge() != null) {
            currentUser.setAge(user.getAge());
        }
        if (user.getFirstName() != null && !user.getFirstName().isEmpty()) {
            currentUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null && !user.getLastName().isEmpty()) {
            currentUser.setLastName(user.getLastName());
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            currentUser.setPassword(user.getPassword());
        }
        currentUser.setRoles(user.getRoles());
        userRepository.save(currentUser);
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

        User user = findByEmail(nickName);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", nickName));
        }

        return user;
    }


    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }
}
