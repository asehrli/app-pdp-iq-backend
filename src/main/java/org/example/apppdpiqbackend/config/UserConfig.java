package org.example.apppdpiqbackend.config;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.apppdpiqbackend.entity.Role;
import org.example.apppdpiqbackend.entity.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "default")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class UserConfig {
    Role role;
    User user;
}
