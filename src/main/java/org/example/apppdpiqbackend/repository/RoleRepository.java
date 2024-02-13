package org.example.apppdpiqbackend.repository;

import org.example.apppdpiqbackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, UUID id);

}