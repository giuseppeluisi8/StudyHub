package it.studyhub.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.studyhub.backend.dto.UserRequest;
import it.studyhub.backend.entity.User;
import it.studyhub.backend.exception.UserNotFoundException;
import it.studyhub.backend.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();

    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Utente non trovato"));
    }

    public User createUser(UserRequest request){
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return userRepository.save(user);

    }

    public User updateUser(Long id, UserRequest request){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

        userRepository.delete(user);
    }
    
}
