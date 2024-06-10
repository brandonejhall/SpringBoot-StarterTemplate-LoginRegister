package com.swiftrecruit.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.swiftrecruit.usermanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String Email);
}
