package it.studyhub.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.studyhub.backend.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    
}
