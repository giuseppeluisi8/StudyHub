package it.studyhub.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.studyhub.backend.dto.CourseRequest;
import it.studyhub.backend.dto.CourseResponse;
import it.studyhub.backend.entity.Course;
import it.studyhub.backend.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream().map(course -> new CourseResponse(course.getId(), course.getName()))
                .toList();

    }

    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Corso non trovato"));
        return new CourseResponse(course.getId(), course.getName());

    }

    public CourseResponse createCourse(CourseRequest request) {
        Course course = new Course();

        course.setName(request.getName());

        Course savedCourse = courseRepository.save(course);

        return new CourseResponse(savedCourse.getId(), savedCourse.getName());
    }

    public CourseResponse updateCourse(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("corso non trovato"));
        course.setName(request.getName());
        Course updatedCourse = courseRepository.save(course);

        return new CourseResponse(updatedCourse.getId(), updatedCourse.getName());
    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Corse non trovato"));
        courseRepository.delete(course);
    }

}
