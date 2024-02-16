package org.example.apppdpiqbackend.repository;

import org.example.apppdpiqbackend.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {
    boolean existsByNameAndSpecialty(String name, UUID id);

    boolean existsByNameAndSpecialtyIdAndIdNot(String name, UUID specialty_id, UUID id);
}

