package it.studyhub.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.studyhub.backend.dto.CourseRequest;
import it.studyhub.backend.dto.CourseResponse;
import it.studyhub.backend.entity.Course;
import it.studyhub.backend.entity.User;
import it.studyhub.backend.repository.CourseRepository;

@Service
public class CourseService {

    //Interagisce con la tabella corsi del database
    private final CourseRepository courseRepository;
    //service che permette di capire quale utente ha effettuato il login
    private final CurrentUserService currentUserService;

    public CourseService(CourseRepository courseRepository, CurrentUserService currentUserService) {
        this.courseRepository = courseRepository;
        this.currentUserService = currentUserService;
    }

    // GET /courses
    // Restituisce tutti i corsi dell'untete che si è autenticato
    public List<CourseResponse> getAllCourses() {
        // Recupera l'utente che eseguito il login
        User currentUser = currentUserService.getCurrentUser();
        // cerca nel Db solamente i corsi appartente all'utente corrente
        return courseRepository.findByUserId(currentUser.getId())
                .stream().map(course -> new CourseResponse(course.getId(), course.getName())) // Per ogni corso trasforma l'entity in un CourseResponse
                .toList(); // trasforma il risultato dello stream in una lista

    }
    
    // GET /courses/id
    // Restituisce un singolo corso
    public CourseResponse getCourseById(Long id) {
        // Recupera l'utente autenticato
        User currentUser = currentUserService.getCurrentUser();
        //cerca il corso nel db usanto il suo ID, se il corso non esiste lancia un'eccezione
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Corso non trovato"));

        //Controlla che il corso appartenga all'utente autenticato
        if (!course.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Corso non trovato"); //se appartiene ad un altro utente, non permette l'accesso
        }

        //Trasforma l'entity course in un CourseResponse e lo restitusce al controller
        return new CourseResponse(course.getId(), course.getName());

    }

    // POST /courses
    // Crea un nuovo corso
    public CourseResponse createCourse(CourseRequest request) {
        // Recupera l'utente autenticato
        User currentUser = currentUserService.getCurrentUser();

        // Crea l'oggetto corso
        Course course = new Course();

        // Prende il nome ricevuto dal client e lo inserisce nel corso
        course.setName(request.getName());
        // associa il corso all'utente autenticato
        course.setUser(currentUser);

        // Salva il corso nel database
        Course savedCourse = courseRepository.save(course);
        // Crea CourseResponse, conterrà i dati che vengono restituiti al client
        return new CourseResponse(savedCourse.getId(), savedCourse.getName());
    }

    // PUT /courses/id
    // Modifica un corso già esistente
    public CourseResponse updateCourse(Long id, CourseRequest request) {
        // recupera l'utente già autenticato
        User currentUser = currentUserService.getCurrentUser();

        // Cerca il corso da modificare, se non esiste lancia un'eccezione
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("corso non trovato"));

        //C<ontrolla che corso è associato all'utente autenticato. se non lo è, la modifica viene impedita
        if (!course.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Corso non trovato");
        }
        
        // modifica il nome del corso
        course.setName(request.getName());

        //salva le modifiche nel database
        Course updatedCourse = courseRepository.save(course);
        
        // Restituisce il corso aggiornato
        return new CourseResponse(updatedCourse.getId(), updatedCourse.getName());
    }

    // DELETE /course/id
    // Elimina il corso
    public void deleteCourse(Long id) {
        User currentUser = currentUserService.getCurrentUser();

        //cerca il corso da eliminare, se non lo trova lancia l'eccezione
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Corso non trovato"));

        // controlla che il corso da eliminare sia associato all'utente autenticato. in caso contrario, l'eliminazione viene impedita
        if (!course.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Corso non trovato");
        }

        //elimina il corso dal database
        courseRepository.delete(course);
    }

}
