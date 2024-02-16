package org.example.apppdpiqbackend.repository;

import org.example.apppdpiqbackend.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    Answer findByQuestionId(UUID question_id);
    List<Answer> findByQuestionLessonId(UUID question_lesson_id);
}
