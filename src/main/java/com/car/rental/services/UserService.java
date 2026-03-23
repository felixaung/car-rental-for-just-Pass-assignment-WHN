package com.car.rental.services;

import com.car.rental.models.Role;
import com.car.rental.models.User;
import com.car.rental.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void save(String email, String username, String password) {

        if(userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.CUSTOMER);
        user.setEnabled(false);
        userRepository.save(user);


    }

    public List<User> findAll(){
        return userRepository.findAll();
    }


}
