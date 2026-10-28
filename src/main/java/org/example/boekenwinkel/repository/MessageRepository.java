package org.example.boekenwinkel.repository;

import org.example.boekenwinkel.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByRecipientUsername(String recipientUsername);
}