package com.sdv.motivational_chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sdv.motivational_chat.model.Conversation;
import com.sdv.motivational_chat.service.ConversationService;

@Controller
public class ConversationController {

	
	private final ConversationService conversationService;
	
	@Autowired
	public ConversationController(ConversationService conversationService) {
		this.conversationService = conversationService;
	}
	
	@GetMapping("/conversation")
	public String showConversationPage() {
		return "conversation";
	}
	
	@PostMapping("/sendMessage")
	public String sendMessage(Model model, @RequestParam String username, @RequestParam String message) {
		String response = conversationService.getMotivationalQuote();
		
		conversationService.saveConversation(username, message, response);
		
		model.addAttribute("username", username);
		model.addAttribute("message", message);
		model.addAttribute("response", response);
		
		return "conversation";
	}
	
	@GetMapping("/users") 
	public String getAllUsers(Model model) {
		List<String> users = conversationService.getAllUsers();
		model.addAttribute("users", users);
		return "users";
	}
	
	@GetMapping("/userConversation")
	public String showUserConversation(Model model, @RequestParam String username) {
		List<Conversation> conversations = conversationService.getConversationByUser(username);
		model.addAttribute("username", username);
		model.addAttribute("conversations", conversations);
		return "userConversation";
	}
}
