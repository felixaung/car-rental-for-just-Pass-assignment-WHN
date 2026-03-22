package com.car.rental.util;

import com.car.rental.models.Role;
import com.car.rental.models.User;
import com.car.rental.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeed implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSeed(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        String adminEmail = "admin@gmail.com";
        boolean adminExists = userRepository.findByEmail(adminEmail).isPresent();

        if (adminExists) {
            return;
        }
        User admin = new User();
        admin.setEmail(adminEmail);
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("P@ssw0rd"));
        admin.setEnabled(true);
        admin.setAddress("Yangon");
        admin.setNrc(null);
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);
    }
}
