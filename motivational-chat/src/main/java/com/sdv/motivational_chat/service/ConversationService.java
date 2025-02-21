package com.sdv.motivational_chat.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sdv.motivational_chat.model.Conversation;
import com.sdv.motivational_chat.model.Quote;
import com.sdv.motivational_chat.repository.ConversationRepository;

@Service
public class ConversationService {

	
	private final ConversationRepository conversationRepository;
	private final RestTemplate restTemplate;
	
	@Autowired
	public ConversationService(ConversationRepository conversationRepository, RestTemplate restTemplate) {
		this.conversationRepository = conversationRepository;
		this.restTemplate = restTemplate;
	}
	
	public String getMotivationalQuote() {
		String url = "http://localhost:8081/getQuote"; 
		Quote quote = restTemplate.getForObject(url, Quote.class);
		return quote.getQuoteText();
	}
	
	public void saveConversation(String username, String message, String response) {
		Conversation conversation = new Conversation();
		conversation.setUsername(username);
		conversation.setMessage(message);
		conversation.setResponse(response);
		conversation.setDate(LocalDateTime.now());
		conversationRepository.save(conversation);
	}
	
	public List<Conversation> getConversationByUser(String username) {
		return conversationRepository.findByUsername(username);
	}
	
	public List<String> getAllUsers() {
		return conversationRepository.findDistinctUsernames();
	}
}
