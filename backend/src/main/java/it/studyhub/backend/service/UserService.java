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

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getAllUsersResponse() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(), user.getUsername(), user.getEmail())).toList();

    }

    public UserResponse getUserByIdResponse(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Utente non trovato"));
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
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User updatedUser = userRepository.save(user);

        return new UserResponse(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail());
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

        userRepository.delete(user);
    }

}
