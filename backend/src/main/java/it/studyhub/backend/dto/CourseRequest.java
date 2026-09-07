package it.studyhub.backend.dto;

import jakarta.validation.constraints.NotBlank;
//dati che il client può inviare
public class CourseRequest {

    @NotBlank
    private String name;

    public CourseRequest(){

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    
}
