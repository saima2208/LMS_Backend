package org.saima.LMS.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.saima.LMS.annotation.CurrentUser;
import org.saima.LMS.constants.Role;
import org.saima.LMS.dto.PasswordChangeRequest;

import org.saima.LMS.dto.UserCreateRequest;
import org.saima.LMS.dto.UserResponse;
import org.saima.LMS.dto.UserUpdateRequest;
import org.saima.LMS.model.User;

import org.saima.LMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	private final UserService userService;
	
//	 private final UserInfoDetailsService userInfoDetailsService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/user")
	public UserDetails user(@CurrentUser UserDetails currentUser) {
		return currentUser;
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<UserResponse> getAllUsers() {
		return userService.getAllUsers().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN') or @userSecurity.hasUserId(authentication, #id)")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
		return userService.getUserById(id).map(user -> ResponseEntity.ok(convertToDTO(user)))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/role")
	public List<UserResponse> getUsersByRole(@RequestParam Role role) {
		return userService.getUsersByRole(role).stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
		User user = new User(userCreateRequest.email(),
				userCreateRequest.password(),
				userCreateRequest.role(),
				userCreateRequest.name(),
				userCreateRequest.fatherName(),
				userCreateRequest.motherName(),
				userCreateRequest.phone(), 
				userCreateRequest.address(),
				userCreateRequest.avatarUrl()

		);

		User createdUser = userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(createdUser));
	}

	@PutMapping("/{id}")
//	@PreAuthorize("hasRole('ADMIN') or @userSecurity.hasUserId(authentication, #id)")
	public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
			@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
		try {
			User userDetails = new User();
			userDetails.setName(userUpdateRequest.name());

			userDetails.setEmail(userUpdateRequest.email());
			userDetails.setFatherName(userUpdateRequest.fatherName());
			userDetails.setMotherName(userUpdateRequest.motherName());
			userDetails.setPhone(userUpdateRequest.phone());
			userDetails.setAddress(userUpdateRequest.address());
			userDetails.setAvatarUrl(userUpdateRequest.avatarUrl());

			// Only admin can update roles
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
				userDetails.setRole(userUpdateRequest.role());
			}

			User updatedUser = userService.updateUser(id, userDetails);
			return ResponseEntity.ok(convertToDTO(updatedUser));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
//	  @PutMapping("/{id}")
//	    public ResponseEntity<User> updateUserProfile(
//	            @PathVariable Long id,
//	            @RequestParam("name") String name,
//	            @RequestParam("phone") String phone,
//	            @RequestParam("address") String address,
//	            @RequestParam(value = "avatarUrl", required = false) MultipartFile avatarUrl) {
//
//	        try {
//	            User updatedUser = userService.updateUserProfile(id, name, phone, address, avatarUrl);
//	            return ResponseEntity.ok(updatedUser);
//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//	        }
//	    }
	
	 @PutMapping
	    public ResponseEntity<?> updateUserByEmail(@RequestBody User userDTO) {
	        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
	            return ResponseEntity.badRequest().body("Email is required to update user.");
	        }

	        try {
	            User updatedUser = userService.updateUserByEmail(userDTO.getEmail(), userDTO);
	            if (updatedUser == null) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with this email.");
	            }
	            return ResponseEntity.ok(updatedUser);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Failed to update user: " + e.getMessage());
	        }
	    }

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);
			return ResponseEntity.ok().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/me")
	public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
		User currentUser = userService.getCurrentUser(authentication);
		if (currentUser == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.ok(convertToDTO(currentUser));
	}
	
	

	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(Authentication authentication,
			@Valid @RequestBody PasswordChangeRequest request) {
		try {
			User currentUser = userService.getCurrentUser(authentication);
			if (currentUser == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}

			userService.changePassword(currentUser.getId(), request.currentPassword(), request.newPassword());

			return ResponseEntity.ok().build();
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
//    @PutMapping("/change-password")
//    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequestDto request) {
//        if (request.getCurrentPassword() == null || request.getNewPassword() == null) {
//            return ResponseEntity.badRequest().body("Password fields cannot be null");
//        }
//
//        userInfoDetailsService.changePassword(request.getUserId(), request.getCurrentPassword(), request.getNewPassword());
//        return ResponseEntity.ok("Password changed successfully");
//    }
	
	

	// Helper method to convert User entity to UserDTO
	private UserResponse convertToDTO(User user) {
		UserResponse dto = new UserResponse();
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setRole(user.getRole());
		dto.setName(user.getName());
		dto.setFatherName(user.getFatherName());
		dto.setMotherName(user.getMotherName());
		dto.setPhone(user.getPhone());
		dto.setAddress(user.getAddress());
		dto.setAvatarUrl(user.getAvatarUrl());

		return dto;
	}
}