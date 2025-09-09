package org.example.security;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<SecUser,Long> {
    SecUser findByUsername(String username);
}
