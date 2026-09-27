package it.studyhub.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.studyhub.backend.dto.SubjectRequest;
import it.studyhub.backend.dto.SubjectResponse;
import it.studyhub.backend.entity.Course;
import it.studyhub.backend.entity.Subject;
import it.studyhub.backend.entity.User;
import it.studyhub.backend.exception.CourseNotFoundException;
import it.studyhub.backend.exception.SubjectNotFoundException;
import it.studyhub.backend.repository.CourseRepository;
import it.studyhub.backend.repository.SubjectRepository;

@Service 
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final CourseRepository courseRepository;
    private final CurrentUserService currentUserService;

    public SubjectService(SubjectRepository subjectRepository, CourseRepository courseRepository,
            CurrentUserService currentUserService) {
        this.subjectRepository = subjectRepository;
        this.courseRepository = courseRepository;
        this.currentUserService = currentUserService;
    }

    public List<SubjectResponse> getAllSubjects() {
        User currentUser = currentUserService.getCurrentUser();

        return subjectRepository.findAll()
                .stream()
                .filter(subject -> subject.getCourse().getUser().getId().equals(currentUser.getId()))
                .map(subject -> new SubjectResponse(subject.getId(), subject.getName()))
                .toList();
    }

    public SubjectResponse getSubjectById(Long id) {
        User currentUser = currentUserService.getCurrentUser();

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException("Materia non trovata"));

        if (!subject.getCourse().getUser().getId().equals(currentUser.getId())) {
            throw new SubjectNotFoundException("Materia non trovata");
        }

        return new SubjectResponse(subject.getId(), subject.getName());
    }

    public SubjectResponse createSubject(SubjectRequest request) {
        User currentUser = currentUserService.getCurrentUser();

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Corso non trovato"));

        // Controlla se il corso esiste e appartiene all'utente autenticato
        if (!course.getUser().getId().equals(currentUser.getId())) {
            throw new CourseNotFoundException("Corso non trovato");
        }

        Subject subject = new Subject();

        subject.setName(request.getName());
        subject.setCourse(course);

        Subject savedSubject = subjectRepository.save(subject);

        return new SubjectResponse(savedSubject.getId(), savedSubject.getName());
    }

    public SubjectResponse updateSubject(Long id, SubjectRequest request) {
        User currentUser = currentUserService.getCurrentUser();

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException("Materia non trovata"));

        if (!subject.getCourse().getUser().getId().equals(currentUser.getId())) {
            throw new SubjectNotFoundException("Materia non trovata");
        }

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Corso non trovato"));

        if (!course.getUser().getId().equals(currentUser.getId())) {
            throw new CourseNotFoundException("Corso non trovato");
        }

        subject.setName(request.getName());
        subject.setCourse(course);

        Subject updatedSubject = subjectRepository.save(subject);

        return new SubjectResponse(updatedSubject.getId(), updatedSubject.getName());
    }

    public void deleteSubject(Long id){
        User currentUser = currentUserService.getCurrentUser();

        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new SubjectNotFoundException("Materia non trovata"));

        if(!subject.getCourse().getUser().getId().equals(currentUser.getId())){
            throw new SubjectNotFoundException("Materia non trovata");
        }

        subjectRepository.delete(subject);
    }
}
