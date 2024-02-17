package org.example.apppdpiqbackend.repository;

import org.example.apppdpiqbackend.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpecialityRepository extends JpaRepository<Specialty, UUID> {
}
