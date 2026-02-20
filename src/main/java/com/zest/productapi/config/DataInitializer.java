package com.zest.productapi.config;

import com.zest.productapi.security.entity.Role;
import com.zest.productapi.security.entity.User;
import com.zest.productapi.security.repository.RoleRepository;
import com.zest.productapi.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initUsers(
            UserRepository userRepository,
            RoleRepository roleRepository) {

        return args -> {

            if (userRepository.findByUsername("admin").isPresent()) return;

            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> roleRepository.save(
                            new Role(null, "ROLE_ADMIN")
                    ));

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of(adminRole));

            userRepository.save(admin);
        };
    }
}