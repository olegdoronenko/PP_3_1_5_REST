package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
//import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService{
    private final RoleDao roleDao;
    //private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
//        this.roleRepository = roleRepository;
    }

//    @Override
//    public List<Role> getRolesList() {
//        return roleRepository.findAll();
//    }

    @Override
    public List<Role> getRolesList2() {
        return roleDao.getRoleList();
    }
}
