package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;


public interface RoleDao {

    void addRole(Role role);

    void deleteRole(Role role);

    void deleteRole(Long id);

    void modifyRole(Role role);

    Role findRoleById(Long id);

    List<Role> getRoleList();


}
