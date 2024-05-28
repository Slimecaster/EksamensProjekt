package com.example.eksamensprojekt.Service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override

    public String getPassword() {
        return this.getPassword();

    }



    @Override

    public String getUsername() {

        return this.getUsername();

    }



    @Override

    public boolean isAccountNonExpired() {

        return true;

    }



    @Override

    public boolean isAccountNonLocked() {

        return true;

    }



    @Override

    public boolean isCredentialsNonExpired() {

        return true;

    }



    @Override

    public boolean isEnabled() {

        return true;

    }
}

