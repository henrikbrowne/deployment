package org.example.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class SecurityUserPrincipal implements UserDetails {
    SecUser secUser;

    public SecurityUserPrincipal(SecUser secUser) {
        this.secUser = secUser;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return secUser.getPassword();
    }

    @Override
    public String getUsername() {
        return secUser.getUsername();
    }
}
