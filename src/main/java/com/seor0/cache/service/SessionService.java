package com.seor0.cache.service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
	
	public ResponseEntity<SessionResponse> creaSession(SessionRequest request) {
		SessionResponse response = new SessionResponse();
		SessionUtente session = new SessionUtente();
		//inserisco dati in cache
		try {
			session.setAbi(request.getAbi());
			session.setBt(request.getBt());
			session.setCanale(request.getCanale());
			session.setScope(request.getScope());
			session.setGeneTime((new SimpleDateFormat("HH:mm:ss").format(new java.util.Date().getTime())));
			session.setUsername(request.getUsername());
			session.setSessionActive(true);
			session.setSessionId(gs.generaSessionId(request.getBt(),request.getAbi(), session.getGeneTime(),session.getCanale()));
			cacheClient.insert(request.getBt(),session);
			
		}catch(Exception e) {
			//TODO implementare gestione errore 
			System.out.println("SO Esploso");
		}
		ResponseEntity<SessionResponse> oResponse = new ResponseEntity(new SessionResponse(),HttpStatus.OK);
		oResponse.getBody().setSessionActive(session.isSessionActive());
		oResponse.getBody().setSessionActive(session.isSessionActive());
		oResponse.getBody().setBt(session.getBt());
		oResponse.getBody().setSessionId(session.getSessionId());
		response.setBt(session.getBt());
		response.setScope(session.getScope());
		response.setSessionId(session.getSessionId());
		return oResponse;
	}
	
	public SessionResponse checkSessionAlreadyACtive(SessionRequest request, String sessionId) {
		SessionResponse response = new SessionResponse();
		SessionUtente session = new SessionUtente();
		// controllo se sessione esiste ed è attiva
		boolean sessionExist;
		boolean sessionIsActive = false;
		
		sessionExist = cacheClient.exist(request.getBt());
		if(sessionExist) {
			session = cacheClient.get(request.getBt()); 
			sessionIsActive = session.isSessionActive();
		}
		
		if(sessionIsActive) {
			// controllo che lo scope e il sessionId siano uguali altrimenti e un altra sessione e torno true
			if(session.getScope().equals(request.getScope()) && sessionId.equals(session.getSessionId()))
				response.setSessionAlreadyExist(false);
			else 
				response.setSessionAlreadyExist(true);
			
		}
		response.setSessionActive(session.isSessionActive());	
		return response;
	}
	
	public SessionResponse updateSessione(SessionRequest request, String sessionId) {
		SessionResponse response = new SessionResponse();
		SessionUtente session = new SessionUtente();
		boolean sessionExist;
		sessionExist = cacheClient.exist(request.getBt());
		if(!sessionExist) {
			response.setSessionNoExist(true);
			return response;
		}
		session = cacheClient.get(request.getBt());
		// se sessionId non combaciano e una nuova non un update
		if(!sessionId.equals(session.getSessionId())) {
			// TODO implementare poi gestione errore
			response.setSessionNoExist(true);
			return response;
		}
		session.setScope(request.getScope());
		session.setUpdateTime((new SimpleDateFormat("HH:mm:ss").format(new java.util.Date().getTime())));
		cacheClient.put(request.getBt(), session);
		
		response.setScope(session.getScope());
		response.setSessionId(sessionId);
		response.setBt(session.getBt());
		
		return response;
	}
	
	public SessionResponse getSession(SessionRequest request) {
		SessionResponse response = new SessionResponse();
		SessionUtente session = new SessionUtente();
		//controllo se sessione esiste 
		boolean sessionExist;
		sessionExist = cacheClient.exist(request.getBt());
		if(!sessionExist) {
			response.setSessionNoExist(true);
			return response;
		}
		session = cacheClient.get(request.getBt()); 
		//LocalTime timeStart = LocalTime.parse(resp.getGeneTime());
		LocalTime timeStart = (session.getUpdateTime() != null) ? LocalTime.parse(session.getUpdateTime()) : LocalTime.parse(session.getGeneTime());
		LocalTime timeEnd = LocalTime.parse(new SimpleDateFormat("HH:mm:ss").format(new java.util.Date().getTime()));
		// addo 5 minuti al tempo di start tempo massimo
		System.out.println("Time start piu 5 min ="+ timeStart.plusMinutes(1) );
		System.out.println("Time end ="+ timeEnd );
		// check se tempo limite e edentro start +1 minuto
		if(timeEnd.isAfter(timeStart.plusMinutes(5))) {
			response.setSessionActive(false); return response;
		}
		
		response.setScope(session.getScope());
		response.setBt(session.getBt());
		response.setSessionActive(session.isSessionActive());
		response.setUsername(session.getUsername());
		
		return response;
	}
	
	public SessionResponse deleteSession(SessionRequest request) {
		SessionResponse response = new SessionResponse();
		SessionUtente session = new SessionUtente();
		// controllo se sessione esiste 
		boolean sessionExist;
		sessionExist = cacheClient.exist(request.getBt());
		if(!sessionExist) {
			response.setSessionNoExist(true);
			return response;
		}
		// TODO finire , al momento non esiste delete
		return response;
	}
	
	
}
