package in.co.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.web.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
 
    User findByUsername(String username);
    
    List<User> findAll();
    
    User findByEmail(String email);
}
