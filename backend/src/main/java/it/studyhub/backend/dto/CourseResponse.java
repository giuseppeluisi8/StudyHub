package it.studyhub.backend.dto;
// dati che il client riceve
public class CourseResponse {
    private Long id;
    private String name;

    public CourseResponse(){

    }

    public CourseResponse(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
}
