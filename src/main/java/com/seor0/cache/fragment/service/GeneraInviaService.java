package com.seor0.cache.fragment.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.seor0.cache.service.request.GeneraInviaRequest;
import com.seor0.cache.service.response.GeneraInviaResponse;

@Service
public class GeneraInviaService {

	
	RestTemplate rt = new RestTemplate();
	
	String fooResourceUrl
	= "http://localhost:8085/nodb/otp/send";
	
	public GeneraInviaResponse generaeInvia(String username, String email) {
		
		GeneraInviaRequest request = new GeneraInviaRequest();
		request.setEmail(email);
		request.setUsername(username);
		GeneraInviaResponse response = new GeneraInviaResponse();
		try {
			response = rt.postForObject(fooResourceUrl, request, GeneraInviaResponse.class);
		}catch(Exception e) {
			//TODO implementare gestione errori su tutto ppoi
		}
		return response;
	}
}
