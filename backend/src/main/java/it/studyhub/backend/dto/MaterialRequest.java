package it.studyhub.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class MaterialRequest {

    @NotBlank(message = "Il titolo del materiale è obbligatorio")
    private String title;

    private String description;

    private Long subjectId;

    public MaterialRequest() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

}
