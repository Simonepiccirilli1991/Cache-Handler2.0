package com.seor0.cache.fragment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.seor0.cache.service.SessionService;
import com.seor0.cache.service.response.SessionResponse;

@Service
public class SecSessionIsValid {

	@Autowired
	private SessionService ssServ;
	
	// ritorna booleano
	public boolean SecuretyIsValid(String bt, String secSessionId) {
		
		SessionResponse sessionDto = ssServ.getSession(bt);
		// controllo che la sessione esista, e sia attiva.
		if(!ObjectUtils.isEmpty(sessionDto.getSessionNoExist()) && Boolean.FALSE.equals(sessionDto.isSessionActive()))
			return false;
		
		// controllo che id sicurezza combacino
		if(!sessionDto.getSessionId().equals(secSessionId))
			return false;
		// tutto ok
		return true;
	}
	
	// controlla che sia attiva ed in l2
	public boolean SecSessionIsValidL2(String bt) {
		
		SessionResponse sessionDto = ssServ.getSession(bt);
		// controllo che la sessione esista, e sia attiva.
		if(!ObjectUtils.isEmpty(sessionDto.getSessionNoExist()) && Boolean.FALSE.equals(sessionDto.isSessionActive()))
			return false;
		
		// controllo che id sicurezza combacino
		if(!sessionDto.getScope().equals("L2"))
			return false;
		// tutto ok
		return true;
	}
}
