package com.sdv.motivational_api.controller;

import com.sdv.motivational_api.model.Quote;
import com.sdv.motivational_api.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

	@Autowired
	private QuoteService quoteService;
	
	@GetMapping("/getQuote")
	public Quote getQuote() {
		return quoteService.getRandomQuote();
	}
}
