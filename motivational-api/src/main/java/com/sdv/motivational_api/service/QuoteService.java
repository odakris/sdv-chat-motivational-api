package com.sdv.motivational_api.service;

import com.sdv.motivational_api.model.Quote;
import com.sdv.motivational_api.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteService {
	
	@Autowired
	private QuoteRepository quoteRepository;
	
	public Quote getRandomQuote() {
		List<Quote> quotes = quoteRepository.findAll();
		
		if(quotes.isEmpty()) {
			return null;
		}
		
		Random rand = new Random();
		return quotes.get(rand.nextInt(quotes.size()));
	}
}
