package it.studyhub.backend.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.studyhub.backend.dto.UserRequest;
import it.studyhub.backend.dto.UserResponse;
import it.studyhub.backend.entity.User;
import it.studyhub.backend.exception.UserNotFoundException;
import it.studyhub.backend.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final CurrentUserService currentUserService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
            CurrentUserService currentUserService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUserService = currentUserService;
    }

    public List<UserResponse> getAllUsersResponse() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(), user.getUsername(), user.getEmail())).toList();

    }

    public UserResponse getUserByIdResponse(Long id) {
        // Recupera l'utente che ha effettuato il login
        User currenUser = currentUserService.getCurrentUser();

        // Cerca nel db l'utente richiesto tramite il suo ID
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

        // Controlla che l'utente richiesto sia lo stesso di quello che ha effettuato il
        // login
        if (!user.getId().equals(currenUser.getId())) {
            throw new UserNotFoundException("Utente non trovato");
        }

        // Restituisce i sati dell'utente tramite UserResponse (quindi senza passowrd)
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    public UserResponse createUser(UserRequest request) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        return new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());

    }

    public UserResponse updateUser(Long id, UserRequest request) {
        //  Recupera l'utente che effettuato il login
        User currentUser = currentUserService.getCurrentUser();

        // Cerca nel db l'utente che vogliamo modificare
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

        // Controlla che l'utente da modificare sia quello che ha effettuato il login
        if(!user.getId().equals(currentUser.getId())){
            throw new UserNotFoundException("Utente non trovato");
        }
        
        // Aggiorna lo username
        user.setUsername(request.getUsername());
        // Aggiorna l'email
        user.setEmail(request.getEmail());
        // Cripta la nuova password prima di salvarla
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Salva le modifiche nel db
        User updatedUser = userRepository.save(user);

        // Restituisce i dati aggiornati senza la password
        return new UserResponse(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail());
    }

    public void deleteUser(Long id) {
        // Recupera l'utente che ha effettuato il login
        User currentUser = currentUserService.getCurrentUser();
        // Cerca nel db l'utente che vogliamo eliminare
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

        // Controlla che l'utente da eliminare sia quello che ha effettuato il login
        if(!user.getId().equals(currentUser.getId())){
            throw new UserNotFoundException("Utente non trovato");
        }

        // Elimina l'utente dal db
        userRepository.delete(user);
    }

}
