package com.vn.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.vn.entity.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class CustomerUserDetails implements UserDetails {

    Users user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<String> listRole = user.getUserRoles().stream().map(e->{

            return "ROLE_"+e.getRole().getName();
        }).collect(Collectors.toList());

        List<GrantedAuthority> authors = new ArrayList<GrantedAuthority>();
        listRole.forEach(e -> {
            SimpleGrantedAuthority author = new SimpleGrantedAuthority(e);
            authors.add(author);
        });

        return Collections.synchronizedList(authors);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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