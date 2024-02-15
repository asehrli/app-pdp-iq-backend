package org.example.apppdpiqbackend.components;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.example.apppdpiqbackend.config.UserConfig;
import org.example.apppdpiqbackend.entity.Role;
import org.example.apppdpiqbackend.entity.User;
import org.example.apppdpiqbackend.enums.PermissionEnum;
import org.example.apppdpiqbackend.repository.RoleRepository;
import org.example.apppdpiqbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class DataInitializer implements CommandLineRunner {
    final RoleRepository roleRepository;
    final UserRepository userRepository;
    final UserConfig userConfig;
    final PasswordEncoder passwordEncoder;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    String ddl;

    @Override
    public void run(String... args) throws Exception {
        if (ddl.startsWith("create")) {
            Role role = userConfig.getRole();
            role.setPermissions(Arrays.asList(PermissionEnum.values()));
            roleRepository.save(role);

            Role userRole = new Role();
            userRole.setName("USER");
            userRole.setPermissions(List.of());
            roleRepository.save(userRole);

            log.info("Role saved: " + role);

            User user = userConfig.getUser();
            user.setRoles(List.of(role));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            log.info("User saved: " + user);
        }
    }
}
