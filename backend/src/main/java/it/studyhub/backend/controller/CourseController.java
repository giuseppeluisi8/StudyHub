package it.studyhub.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.studyhub.backend.dto.CourseRequest;
import it.studyhub.backend.dto.CourseResponse;
import it.studyhub.backend.service.CourseService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {
    // Service che contiene la logica relativa ai corsi
    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    // GET /courses
    // Restituisce i corsi dell'utente autenticato
    @GetMapping
    public List<CourseResponse> getAllCourses(){
        return courseService.getAllCourses();
    }

    // GET /courses/id
    // restituisce un singolo corso 
    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable Long id){
        // @PathVariable prende l'ID dall'URL
        return courseService.getCourseById(id);
    }

    // POST /courses
    //Crea un nuovo corso
    @PostMapping
    public CourseResponse createCourse(@Valid @RequestBody CourseRequest request){
        // @Valid attiva le validazioni presenti in CourseRequest
        // @RequestBody trasforma il JSON ricevuto in un oggetto CourseRequest
        return courseService.createCourse(request);
    }

    // Modifica un corso esistente
    @PutMapping("/{id}")
    public CourseResponse updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequest request){
        // Prende l'ID dall'URL e i nuovi dati dal JSON
        return courseService.updateCourse(id, request);
    }

    // Elimina un corso già esistente
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id){
        //Prende l'ID dall'URL e chiede al service di eliminare il corso
        courseService.deleteCourse(id);
    }
}
