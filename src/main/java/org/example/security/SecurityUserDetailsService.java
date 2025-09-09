package org.example.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    UserRepository userRepository;

    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecUser secUser = userRepository.findByUsername(username);
        if (secUser == null) { throw new UsernameNotFoundException(username); }
        return new SecurityUserPrincipal(userRepository.findByUsername(username));
    }

}
