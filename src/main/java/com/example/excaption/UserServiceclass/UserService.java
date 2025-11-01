package com.example.excaption.UserServiceclass;

import org.springframework.stereotype.Service;
import com.example.excaption.Repository.UserRepository;
import com.example.excaption.dto.UserRequestDTO;
import com.example.excaption.entity.User;
import com.example.excaption.exception.DuplicateResourceException;
import com.example.excaption.exception.ResourceNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User createUser(UserRequestDTO userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new DuplicateResourceException("Email already exists: " + userRequest.getEmail());
        }
        
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword()); 
        
        return userRepository.save(user);
    }
}