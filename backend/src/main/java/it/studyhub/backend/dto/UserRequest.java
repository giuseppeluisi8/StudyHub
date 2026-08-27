package it.studyhub.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {
    
    @NotBlank(message = "Lo username è obbligatorio")
    private String username;

    @Email(message = "L'email non è valida")
    @NotBlank(message = "L'email è obbligatoria")
    private String email;

    @NotBlank(message = "La password è obbligatoria")
    private String password;

    public UserRequest(){

    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
