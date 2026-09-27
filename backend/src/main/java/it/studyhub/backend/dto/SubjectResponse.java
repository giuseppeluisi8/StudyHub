package it.studyhub.backend.dto;

public class SubjectResponse {

    private Long id;
    private String name;

    public SubjectResponse() {

    }

    public SubjectResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
