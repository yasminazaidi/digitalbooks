package org.example.boekenwinkel;

import org.example.boekenwinkel.model.User;
import org.example.boekenwinkel.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BoekenwinkelApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoekenwinkelApplication.class, args);
    }

    // Dit zorgt ervoor dat gebruikers automatisch in de code worden aangemaakt bij het opstarten
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            // 1. Maak een Admin-account aan als deze nog niet bestaat
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@ehb.be");
                admin.setPassword("admin123");
                admin.setRole("ADMIN");
                userRepository.save(admin);
                System.out.println(">>> Standaard Admin-account aangemaakt: admin / admin123");
            }

            // 2. Maak een normale testgebruiker aan als deze nog niet bestaat
            if (userRepository.findByUsername("yasmina").isEmpty()) {
                User user = new User();
                user.setUsername("yasmina");
                user.setEmail("yasmina@ehb.be");
                user.setPassword("welkom123");
                user.setRole("USER");
                userRepository.save(user);
                System.out.println(">>> Standaard gebruiker aangemaakt: yasmina / welkom123");
            }
            // 3. Tweede extra gebruiker (bijv. student)
            if (userRepository.findByUsername("student").isEmpty()) {
                User user2 = new User();
                user2.setUsername("student");
                user2.setEmail("student@ehb.be");
                user2.setPassword("student123");
                user2.setRole("USER");
                userRepository.save(user2);
                System.out.println(">>> Extra gebruiker aangemaakt: student / student123");
            }
        };
    }
}