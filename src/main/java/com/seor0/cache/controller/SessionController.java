package com.seor0.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
			@RequestHeader HttpHeaders headers,
			@RequestHeader(name = "ABI", required = false) String abi,
			@RequestHeader(name = "CHANNEL", required = false) String canale){
		
		request.setCanale(canale);
		request.setAbi(abi);
		
		//SessionResponse response = ss.creaSession(request);
		
		//return new ResponseEntity<>(ss.creaSession(request), HttpStatus.OK);
		return ss.creaSession(request);
	}
	
	
	@RequestMapping("get")
	public SessionResponse getSessione(@RequestBody SessionRequest request,
			@RequestHeader HttpHeaders headers,
			@RequestHeader(name = "ABI", required = false) String abi,
			@RequestHeader(name = "CHANNEL", required = false) String canale,
			@RequestHeader(name = "X-SESSIONID", required = false) String sessionId){
		
		return ss.getSession(request);
			
		
	}
	
	@RequestMapping("checkse")
	public ResponseEntity<SessionResponse> checkSessionAlredyActive(@RequestBody SessionRequest request,
			@RequestHeader HttpHeaders headers,
			@RequestHeader(name = "ABI", required = false) String abi,
			@RequestHeader(name = "CHANNEL", required = false) String canale,
			@RequestHeader(name = "X-SESSIONID", required = true) String sessionId){
			
		
		return new ResponseEntity<>(ss.checkSessionAlreadyACtive(request, sessionId), HttpStatus.OK);
			
		
	}
	
	@RequestMapping("update")
	public SessionResponse updateSession(@RequestBody SessionRequest request,
			@RequestHeader HttpHeaders headers,
			@RequestHeader(name = "ABI", required = false) String abi,
			@RequestHeader(name = "CHANNEL", required = false) String canale,
			@RequestHeader(name = "X-SESSIONID", required = true) String sessionId){
		
			return ss.updateSessione(request, sessionId);
	}
}
