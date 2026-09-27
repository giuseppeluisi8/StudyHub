package it.studyhub.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.studyhub.backend.entity.Subject;

// Classe che interagisce con il database
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    // Trova tutti i subject il cui Course ha quell'id
    List<Subject> findByCourseId(Long courseId);

}
