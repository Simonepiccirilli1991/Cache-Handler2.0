package com.seor0.cache.service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.stereotype.Service;

import com.seor0.cache.config.CacheClientAppSession;
import com.seor0.cache.fragment.service.Base64Crypter;
import com.seor0.cache.fragment.service.SecSessionIsValid;
import com.seor0.cache.model.AppSession;
import com.seor0.cache.service.request.AppSessionRequest;
import com.seor0.cache.service.response.AppSessionResponse;


@Service
public class AppSessionService {

	@Autowired
	private CacheClientAppSession cacheClient;
	@Autowired SecSessionIsValid sessionValid;
	@Autowired Base64Crypter crypter;
	
	@Value("${smgy.cache.key}")
	 String keyStr;
	
	String randomVector = "1234567891012131";
	
	public ResponseEntity<AppSessionResponse> creaSession(AppSessionRequest request){
		AppSessionResponse response = new AppSessionResponse();
		//ResponseEntity<AppSessionResponse> oResponse = new ResponseEntity<AppSessionResponse>(new AppSessionResponse(),HttpStatus.OK);
		AppSession appSession = new AppSession();
		
		// prima di creare controllo che gia non esista sessione, in caso ritorno response specifica con codice 
		boolean sessionAlreadyExist = sessionGiaEsiste(request.getBt());
		
		// sessione esiste ed e valida
		if(sessionAlreadyExist) {
//			oResponse.getBody().setAlreadyActive(true);
//			oResponse.getBody().setCodServizio("03-alreadyActive");
			response.setAlreadyActive(true);
			response.setCodServizio("03-alreadyActive");
			return new ResponseEntity<>(response,HttpStatus.UPGRADE_REQUIRED);
		}
		
		// utente non ha gia sessione attive , controllo che la sessione di sicurezza sia valida
		// logica in fragment
		
		boolean secSessionIsValid = sessionValid.SecuretyIsValid(request.getBt(), request.getSecSession());
		// se secSessionId non e valido lancio errore
		if(!secSessionIsValid) {
			//TODO implementare errore
		}
		// tutto ok creo	
		appSession.setSecSessionId(request.getSecSession());
		appSession.setBt(request.getBt());
		appSession.setSessionActive(true);
		appSession.setAppName(request.getAppName());
		appSession.setGeneTime((new SimpleDateFormat("HH:mm:ss").format(new java.util.Date().getTime())));
		appSession.setAppSessionId(crypto(request.getSecSession()));
		cacheClient.insert(request.getBt(), appSession);
		
	
		
//		oResponse.getBody().setActive(true);
//		oResponse.getBody().setAppSessionId(appSession.getAppSessionId());
//		oResponse.getBody().setCodServizio("00 - OK");
		
		response.setActive(true);
		response.setAppSessionId(appSession.getAppSessionId());
		response.setCodServizio("00 - OK");
		
		// passo header con data inizio session Applicativa
		 HttpHeaders responseHeaders = new HttpHeaders();
		 responseHeaders.add("appTime", "priva");
		
		
		return new ResponseEntity<>(response,responseHeaders,HttpStatus.OK);
	}
	
	
	private Boolean sessionGiaEsiste(String bt) {
		
		// controllo se esiste gia una sessione
		boolean sessionExist = cacheClient.exist(bt);
		// se esiste controllo che sia ancora valida
		if(sessionExist) {
			AppSession session = cacheClient.get(bt);
			
			LocalTime timeStart = (session.getUpdateTime() != null) ? LocalTime.parse(session.getUpdateTime()) : LocalTime.parse(session.getGeneTime());
			LocalTime timeEnd = LocalTime.parse(new SimpleDateFormat("HH:mm:ss").format(new java.util.Date().getTime()));
			// addo 5 minuti al tempo di start tempo massimo
			System.out.println("Time start piu 5 min ="+ timeStart.plusMinutes(1) );
			System.out.println("Time end ="+ timeEnd );
			// check se tempo limite e edentro start +1 minuto
			if(timeEnd.isAfter(timeStart.plusMinutes(5))) {
				return false;
			}
			else
				return true;
		}
			
		
		return false;
	}
	
	
	// metodi usati per criptare
	private String crypto(String secSession) {
		
		String key = keyStr;
		String vector = randomVector;
		// crypto 
		String crypt = Base64Crypter.encrypt(key, vector, secSession);
		 
		
		return crypt;
	}
	
	private String deCrypto(String bt, String secSession) {
		String key = keyStr;
		String vector = randomVector;
		// crypto 
		String crypt = Base64Crypter.decrypt(key, vector, secSession);
		 
		
		return crypt;
	}
}
