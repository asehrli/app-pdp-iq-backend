package org.example.apppdpiqbackend.repository;

import org.example.apppdpiqbackend.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {
}
