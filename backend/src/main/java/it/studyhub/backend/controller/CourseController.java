package it.studyhub.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseResponse> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    @PostMapping
    public CourseResponse createCourse(@Valid @RequestBody CourseRequest request){
        return courseService.createCourse(request);
    }

    @PostMapping("/{id}")
    public CourseResponse updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequest request){
        return courseService.updateCourse(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
    }
}
