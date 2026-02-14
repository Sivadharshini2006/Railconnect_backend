package com.railconnect.authservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.railconnect.authservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}