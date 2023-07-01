package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
        entityManager.flush();

    }

    @Override
    public void deleteRole(Role role) {
        entityManager.remove(role);
        entityManager.flush();
    }

    @Override
    public void deleteRole(Long id) {
        Role role = findRoleById(id);
        deleteRole(role);
    }

    @Override
    public void modifyRole(Role role) {
        entityManager.merge(role);
        entityManager.flush();
    }

    @Override
    public Role findRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> getRoleList() {
        return entityManager.createQuery("FROM Role", Role.class).getResultList();
    }


}
