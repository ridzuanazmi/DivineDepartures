package nusiss.project.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nusiss.project.server.models.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    // Find user by email
    Optional<User> findByEmail(String email);
}
