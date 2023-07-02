package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
        entityManager.flush();
    }

    @Override
    public void deleteUser(Long id) {
        User user = findUserById(id);
        deleteUser(user);
    }

    @Override
    public void modifyUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.firstName= :username", User.class);
        query.setParameter("username", username);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<User> getUsersList() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }
}
