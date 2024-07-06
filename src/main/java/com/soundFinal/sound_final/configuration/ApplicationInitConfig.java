package com.soundFinal.sound_final.configuration;

import com.soundFinal.sound_final.entity.Role;
import com.soundFinal.sound_final.entity.User;
import com.soundFinal.sound_final.repository.RoleRepository;
import com.soundFinal.sound_final.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if (userRepository.findByEmailIgnoreCase("admin@gmail.com").isEmpty()) {
                Optional<Role> adminRoleOptional = roleRepository.findByName("ADMIN");
                //Linh update if not find to the admin role create role new admin
                if (adminRoleOptional.isEmpty()) {
                    // Nếu chưa, tạo mới vai trò "ADMIN"
                    Role adminRole = Role.builder()
                            .name("ADMIN")
                            .description("Administrator role")
                            .build();
                    roleRepository.save(adminRole);
                    adminRoleOptional = Optional.of(adminRole); // Update the optional
                }

                // Create roles set
                Set<Role> roles = new HashSet<>();
                roles.add(adminRoleOptional.get());

                // Build user with roles using builder pattern
                User user = User.builder()
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("admin"))
                        .username("admin")    // Set username
                        .createDate(LocalDateTime.now()) // Set creation date
                        .build();

                // Set roles using setter method
                user.setRoles(roles);

                // Save user with roles
                userRepository.save(user);

                log.warn("Admin user has been created with default password: admin. Please change it.");
            }
        };
    }
}
