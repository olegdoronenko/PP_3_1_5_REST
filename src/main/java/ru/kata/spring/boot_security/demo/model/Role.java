package ru.kata.spring.boot_security.demo.model;


import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "role_name")
    private String roleName;

    public Role() {

    }

    public Role(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        if ("ROLE_USER".equals(getAuthority())) {
            return "USER";
        } else if ("ROLE_ADMIN".equals(getAuthority())) {
            return "ADMIN";
        } else {
            return getAuthority();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNoPrefix() {
        String pr = "ROLE_";
        return roleName.substring(pr.length());
    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }


}
