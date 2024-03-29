package org.example.apppdpiqbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableAspectJAutoProxy
@SpringBootApplication
@EnableJpaAuditing
public class AppPdpIqBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppPdpIqBackendApplication.class, args);
    }

}
