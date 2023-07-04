package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return findByEmail(auth.getName());
    }

    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public List<User> getUsersList() {
        return userRepository.findAll();
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

    @Transactional
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> getUser = userRepository.findById(id);
        return getUser.orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

//    @Override
//    public void modifyUser(User user) {
//        userRepository.
//    }

    @Override
    public void modifyUser(User user) {
        //User currentUser = userRepository.findById(user.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        User currentUser = new User();
        currentUser.setId(user.getId());

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            currentUser.setEmail(user.getEmail());
        }
        if (user.getAge() != null ) {
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
//        @Override
//    public void modifyUser(User user) {
//        User currentUser = new User();
//
//        currentUser.setId(user.getId());
//
//        currentUser.setEmail(user.getEmail());
//
//        currentUser.setAge(user.getAge());
//
//        currentUser.setFirstName(user.getFirstName());
//
//        currentUser.setLastName(user.getLastName());
//
//        currentUser.setPassword(user.getPassword());
//
//        currentUser.setRoles(user.getRoles());
//        userRepository.save(currentUser);
//    }


}
