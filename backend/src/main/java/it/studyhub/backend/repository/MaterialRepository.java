package it.studyhub.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.studyhub.backend.entity.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findBySubjectId(Long subjectId);
    
}
