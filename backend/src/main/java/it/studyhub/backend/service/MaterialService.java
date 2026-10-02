package it.studyhub.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.studyhub.backend.dto.MaterialRequest;
import it.studyhub.backend.dto.MaterialResponse;
import it.studyhub.backend.entity.Material;
import it.studyhub.backend.entity.Subject;
import it.studyhub.backend.entity.User;
import it.studyhub.backend.exception.CourseNotFoundException;
import it.studyhub.backend.exception.MaterialNotFoundException;
import it.studyhub.backend.exception.SubjectNotFoundException;
import it.studyhub.backend.repository.MaterialRepository;
import it.studyhub.backend.repository.SubjectRepository;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final SubjectRepository subjectRepository;
    private final CurrentUserService currentUserService;

    public MaterialService(MaterialRepository materialRepository, SubjectRepository subjectRepository,
            CurrentUserService currentUserService) {
        this.materialRepository = materialRepository;
        this.subjectRepository = subjectRepository;
        this.currentUserService = currentUserService;
    }

    public List<MaterialResponse> getAllMaterials() {
        User currentUser = currentUserService.getCurrentUser();

        return materialRepository.findAll()
                .stream()
                .filter(material -> material.getSubject()
                        .getCourse()
                        .getUser()
                        .getId()
                        .equals(currentUser.getId()))
                .map(material -> new MaterialResponse(
                        material.getId(),
                        material.getTitle(),
                        material.getDescription()))
                .toList();
    }

    public MaterialResponse getMaterialById(Long id) {
        User currentUser = currentUserService.getCurrentUser();

        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new MaterialNotFoundException("Materiale non trovato"));

        if (!material.getSubject().getCourse().getId().equals(currentUser.getId())) {
            throw new MaterialNotFoundException("Materiale non torvato");
        }

        return new MaterialResponse(material.getId(), material.getTitle(), material.getDescription());
    }

    public MaterialResponse createMaterial(MaterialRequest request) {
        User currentUser = currentUserService.getCurrentUser();

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new CourseNotFoundException("Corso non trovato"));

        if (!subject.getCourse().getUser().getId().equals(currentUser.getId())) {
            throw new CourseNotFoundException("Corso non trovato");
        }

        Material material = new Material();

        material.setTitle(request.getTitle());
        material.setDescription(request.getDescription());
        material.setSubject(subject);

        Material savedMaterial = materialRepository.save(material);

        return new MaterialResponse(savedMaterial.getId(), savedMaterial.getTitle(), savedMaterial.getDescription());
    }

    public MaterialResponse updateMaterial(Long id, MaterialRequest request) {
        User currentUser = currentUserService.getCurrentUser();

        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new MaterialNotFoundException("Materiale non trovato"));

        if (!material.getSubject().getCourse().getUser().getId().equals(currentUser.getId())) {
            throw new MaterialNotFoundException("Materiale non trovato");
        }

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new SubjectNotFoundException("Materia non trovata"));

        if (!subject.getCourse().getUser().getId().equals(currentUser.getId())) {
            throw new SubjectNotFoundException("Materia non trovata");
        }

        material.setTitle(request.getTitle());
        material.setDescription(request.getDescription());
        material.setSubject(subject);

        Material updatedMaterial = materialRepository.save(material);

        return new MaterialResponse(updatedMaterial.getId(), updatedMaterial.getTitle(),
                updatedMaterial.getDescription());

    }

    public void deleteMaterial(Long id) {

        User currentUser = currentUserService.getCurrentUser();

        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new MaterialNotFoundException("Materiale non trovato"));

        if (!material.getSubject().getCourse().getId().equals(currentUser.getId())) {
            throw new MaterialNotFoundException("Materiale non trovato");
        }
        materialRepository.delete(material);
    }

}
