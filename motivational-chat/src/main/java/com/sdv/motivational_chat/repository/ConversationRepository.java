package com.sdv.motivational_chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sdv.motivational_chat.model.Conversation;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
	List<Conversation> findByUsername(String username);
	
	@Query("SELECT DISTINCT username FROM Conversation")
	List<String> findDistinctUsernames();
}
