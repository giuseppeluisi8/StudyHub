package it.studyhub.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.studyhub.backend.dto.UserResponse;
import it.studyhub.backend.entity.User;
import it.studyhub.backend.service.CurrentUserService;

@RestController
@RequestMapping("/test")
public class TestController {

    private final CurrentUserService currentUserService;

    public TestController(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    @GetMapping("/protected")
    public String protectedEndpoint() {
        return "Sei autenticato";
    }

    @GetMapping("/me")
    public UserResponse currentUser() {
        User user = currentUserService.getCurrentUser();
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}
