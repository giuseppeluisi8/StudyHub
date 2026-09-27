package it.studyhub.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class SubjectRequest {

    @NotBlank(message =  "Il nome della materia è obbligatorio")
    private String name; // nome della materia

    private Long courseId; // indica a quale corso vogliamo associare la materia

    private SubjectRequest(){

    }

    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public Long getCourseId(){
        return courseId;
    }

    public void setCourseId(Long courseId){
        this.courseId = courseId;
    }
}
