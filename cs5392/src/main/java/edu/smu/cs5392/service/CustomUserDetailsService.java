package edu.smu.cs5392.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.smu.cs5392.model.Role;
import edu.smu.cs5392.model.User;
import edu.smu.cs5392.model.UserRole;
import edu.smu.cs5392.repository.RoleRepository;
import edu.smu.cs5392.repository.UserRepository;
import edu.smu.cs5392.repository.UserRoleRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private BCryptPasswordEncoder passwordEncoder;

 /* 
    private final UserRepository userRepository;
*/
    public CustomUserDetailsService() {
        this.passwordEncoder = new BCryptPasswordEncoder(12);
       
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        System.out.println("User is" + user);

        return new org.springframework.security.core.userdetails.User(
            user.getUserName(),
            user.getPassword(),
            user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList()
        );
    }

    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
        
    }

   public User addOperatorUser(String userName, String password, String firstName, String lastName, String email) {
        // Check if user already exists
        if (userRepository.findByUserName(userName) != null) {
            throw new IllegalArgumentException("User already exists with username: " + userName);
        }

        // Hash password
        String hashedPassword = passwordEncoder.encode(password);

        // Create user
        User user = new User();
        user.setUserName(userName);
        user.setPassword(hashedPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setType("Operator");

        // Save user
        user = userRepository.save(user);

        // Assign "Operator" role
        Role role = roleRepository.findByName("Operator");
        if (role == null) {
            role = new Role();
            role.setName("Operator");
            role = roleRepository.save(role);
        }

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);

        return user;
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Delete user's roles
        userRoleRepository.deleteByUser(user);

        // Delete user
        userRepository.delete(user);
    }

    Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
