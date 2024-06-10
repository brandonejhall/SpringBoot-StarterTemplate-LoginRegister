package com.swiftrecruit.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.swiftrecruit.usermanagement.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
