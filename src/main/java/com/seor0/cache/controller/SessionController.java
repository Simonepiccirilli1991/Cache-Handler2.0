package com.seor0.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seor0.cache.service.SessionService;
import com.seor0.cache.service.request.SessionRequest;
import com.seor0.cache.service.response.SessionResponse;

@RestController
@RequestMapping("session")
public class SessionController {

	
	@Autowired
	private SessionService ss;
	
	
	@RequestMapping("create")
	public ResponseEntity<SessionResponse> creaSessione(@RequestBody SessionRequest request,
			@RequestHeader(name = "ABI", required = false) String abi,
			@RequestHeader(name = "CHANNEL", required = false) String canale){
		
		request.setCanale(canale);
		request.setAbi(abi);
		
		SessionResponse response = ss.creaSession(request);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
