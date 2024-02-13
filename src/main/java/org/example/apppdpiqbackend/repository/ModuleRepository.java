package org.example.apppdpiqbackend.repository;

import org.example.apppdpiqbackend.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {
    boolean existsByName(String name);
     boolean existsByNameAndIdNot(String name , UUID id);
}

