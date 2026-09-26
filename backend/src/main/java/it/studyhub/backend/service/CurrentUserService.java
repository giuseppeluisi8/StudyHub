package it.studyhub.backend.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import it.studyhub.backend.entity.User;

@Service
public class CurrentUserService {
    public User getCurrentUser() {
        // Recupera le info sull'utente autenticato
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Restituisce l'utente che è stato inserito nel securityContext dal JWTAuthentication filter
        return (User) authentication.getPrincipal();
    }
}
