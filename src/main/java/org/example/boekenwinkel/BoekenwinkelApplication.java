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

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            // Controleer of de tabel volledig leeg is voordat we de standaardaccounts aanmaken
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@ehb.be");
                admin.setPassword("admin123");
                admin.setRole("ADMIN");
                userRepository.save(admin);

                User user1 = new User();
                user1.setUsername("yasmina");
                user1.setEmail("yasmina@ehb.be");
                user1.setPassword("welkom123");
                user1.setRole("USER");
                userRepository.save(user1);

                User user2 = new User();
                user2.setUsername("student");
                user2.setEmail("student@ehb.be");
                user2.setPassword("student123");
                user2.setRole("USER");
                userRepository.save(user2);

                System.out.println(">>> Standaardaccounts succesvol aangemaakt!");
            }
        };
    }
}