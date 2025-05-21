package org.saima.LMS.service;



import jakarta.transaction.Transactional;

import org.saima.LMS.constants.Role;
import org.saima.LMS.model.CustomUserDetails;
import org.saima.LMS.model.User;
import org.saima.LMS.model.UserInfoDetails;
import org.saima.LMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Transactional
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update fields
        user.setName(userDetails.getName());
        user.setFatherName(userDetails.getFatherName());
        user.setMotherName(userDetails.getMotherName());
        
       
        user.setPhone(userDetails.getPhone());
        user.setAddress(userDetails.getAddress());
        user.setAvatarUrl(userDetails.getAvatarUrl());

        // Only update email if it has changed and is not already in use
        if (!user.getEmail().equals(userDetails.getEmail())) {
            if (userRepository.existsByEmail(userDetails.getEmail())) {
                throw new RuntimeException("Email is already in use");
            }
            user.setEmail(userDetails.getEmail());
        }

        // Update password if provided
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        // Update role if provided
        if (userDetails.getRole() != null) {
            user.setRole(userDetails.getRole());
        }

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
    }

    public User getCurrentUser(Authentication authentication) {
        if (authentication == null) {
            return null;
        }

        if (authentication.getPrincipal() instanceof UserInfoDetails) {
            return ((UserInfoDetails) authentication.getPrincipal()).user();
        }

        return null;
    }

    @Transactional
    public void changePassword(Long userId, String currentPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
    }

    public UserDetails loadUserByUsername(String username) {
        Optional<User> byEmail = userRepository.findByEmail(username);
        return byEmail.map(CustomUserDetails::new).orElse(null);
    }
    
    
    public User updateUserByEmail(String email, User userDTO) {
        // Find the user by email
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return null; // User not found
        }

        User user = optionalUser.get();

        // Update user fields from DTO (excluding email)
        if (userDTO.getName() != null) user.setName(userDTO.getName());
        if (userDTO.getPhone() != null) user.setPhone(userDTO.getPhone());
        // Add more fields as necessary

        // Save updated user
        return userRepository.save(user);
    }
    
    
//    public User updateUserProfile(Long id, String name, String phone, String address, MultipartFile avatarUrl) throws IOException {
//        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
//
//        user.setName(name);
//        user.setPhone(phone);
//        user.setAddress(address);
//
//        // Save the avatar file (if provided)
//        if (avatarUrl != null && !avatarUrl.isEmpty()) {
//            byte[] avatarBytes = avatarUrl.getBytes();
//            user.setAvatarUrl(avatarBytes); // Save the avatar bytes to the database
//        }
//
//        return userRepository.save(user);
//    }
}