package org.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
                 .authorizeHttpRequests(auth -> auth
                         .requestMatchers("/", "/home", "/login", "/init", "/h2-console", "/h2-console/**", "/register").permitAll()
                         .requestMatchers("/admin").hasRole("ADMIN")
                         .anyRequest().authenticated()
                 )
                 .formLogin(form -> form
                         .loginPage("/login")
                         .defaultSuccessUrl("/secret", true)
                         .permitAll()
                 )
                 .logout(logout -> logout
                         .logoutUrl("/logout")
                         .logoutSuccessUrl("/")
                         .permitAll()
                 )
                 .csrf(AbstractHttpConfigurer::disable)
                 .headers(c -> c
                         .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
/*
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user").password("123").roles("USER").build());
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin").password("123").roles("USER", "ADMIN").build());
        return manager;
    }

 */
}
