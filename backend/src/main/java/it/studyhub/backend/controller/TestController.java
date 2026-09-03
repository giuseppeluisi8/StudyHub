package it.studyhub.backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/protected")
    public String protectedEndpoint(){
        return "Sei autenticato";
    }
    
    @GetMapping("/me")
    public String currentUser(Authentication authentication){
        return authentication.getName();
    }
}
