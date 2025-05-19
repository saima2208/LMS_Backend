package org.saima.LMS.repository;

import java.util.List;
import java.util.Optional;

import org.saima.LMS.constants.Role;
import org.saima.LMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	List<User> findByRole(Role role);

	Optional<User> findById(Long teacherId);
	
//	Optional<User> findById(Long id);

	Optional<User> findByIdAndRole(Long teacherId, Role role);
	
	 	
}