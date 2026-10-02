package it.studyhub.backend.dto;

public class MaterialResponse {

    private Long id;
    private String title;
    private String description;

    public MaterialResponse(){

    }

    public MaterialResponse(Long id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }
    
    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
