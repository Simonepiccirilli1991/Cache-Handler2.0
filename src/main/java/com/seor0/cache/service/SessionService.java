package com.seor0.cache.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seor0.cache.config.CacheClientOtp;
import com.seor0.cache.config.CacheClientSession;
import com.seor0.cache.fragment.service.GeneraSessionService;
import com.seor0.cache.model.SessionUtente;
import com.seor0.cache.service.request.SessionRequest;
import com.seor0.cache.service.response.SessionResponse;

@Service
public class SessionService {

	@Autowired
	private CacheClientSession cacheClient;
	@Autowired
	private GeneraSessionService gs;
	
	public SessionResponse creaSession(SessionRequest request) {
		SessionResponse response = new SessionResponse();
		SessionUtente session = new SessionUtente();
		//inserisco dati in cache
		try {
			session.setAbi(request.getAbi());
			session.setBt(request.getBt());
			session.setCanale(request.getCanale());
			session.setScope(request.getScope());
			session.setGeneTime((new SimpleDateFormat("HH.mm.ss").format(new java.util.Date().getTime())));
			session.setUsername(request.getUsername());
			session.setSessionActive(true);
			session.setSessionId(gs.generaSessionId(request.getBt(),request.getAbi(), session.getGeneTime(),session.getCanale()));
			cacheClient.insert(request.getBt(),session);
			
		}catch(Exception e) {
			//TODO implementare gestione errore 
			System.out.println("SO Esploso");
		}
		
		response.setBt(session.getBt());
		response.setScope(session.getScope());
		response.setSessionId(session.getSessionId());
		return response;
	}
	
	
	public SessionResponse updateSessione(SessionRequest request) {
		SessionResponse response = new SessionResponse();
		
		return response;
	}
	
	public SessionResponse getSession() {
		SessionResponse response = new SessionResponse();
		
		return response;
	}
	
	public SessionResponse deleteSession() {
		SessionResponse response = new SessionResponse();
		
		return response;
	}
	
	
}
