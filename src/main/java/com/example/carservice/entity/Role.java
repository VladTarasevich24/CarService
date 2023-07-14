package com.example.carservice.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
