package it.studyhub.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.studyhub.backend.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByUserId(Long userId); //findByUserId cerca i Course il cui User ha questo id
}
