package com.techelevator.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.techelevator.model.CatFact;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatFactService implements CatFactService {

	@Override
	public CatFact getFact() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity entity = restTemplate.getForEntity("https://cat-data.netlify.app/api/facts/random",CatFact.class);
		return (CatFact) entity.getBody();
	}

}
