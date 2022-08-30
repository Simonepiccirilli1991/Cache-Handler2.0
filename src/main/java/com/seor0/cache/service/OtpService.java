package com.seor0.cache.service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seor0.cache.config.CacheClient;
import com.seor0.cache.fragment.service.GeneraInviaService;
import com.seor0.cache.model.OtpBo;
import com.seor0.cache.service.request.CheckOtpRequest;
import com.seor0.cache.service.request.OtpRequest;
import com.seor0.cache.service.response.CheckOtpResponse;
import com.seor0.cache.service.response.GeneraInviaResponse;

@Service
public class OtpService {

	

	@Autowired
	private CacheClient cacheClient;
	@Autowired
	private GeneraInviaService giService;
	
	public String inserisciCache(OtpRequest request) {
		OtpBo otpDto = new OtpBo();
		
		otpDto.setGenerateTime(new SimpleDateFormat("HH.mm.ss").format(new java.util.Date().getTime()));
		//otpDto.setOtp(request.getOtp());
		otpDto.setUsername(request.getUsername());
		otpDto.setProfilo(request.getProfilo());
		otpDto.setTrxId(request.getTrxId());
		
		return cacheClient.insert(request.getTrxId(), otpDto);
	}
	
	
	public String inserisceInvia(OtpRequest request) {
		OtpBo otpEnt = new OtpBo();
		GeneraInviaResponse otpDTO= new GeneraInviaResponse();
		
		otpDTO = giService.generaeInvia(request.getUsername(), request.getEmail());
		// se esito diverso da 00 torno msg errore
		//TODO implementare genstine errori ora non riesco
		if(!otpDTO.getEsito().equals("00"))
			return otpDTO.getMsgResp();
		
		otpEnt.setGenerateTime(new SimpleDateFormat("HH:mm:ss").format(new java.util.Date().getTime()));
		otpEnt.setOtp(otpDTO.getOoTp());
		otpEnt.setUsername(request.getUsername());
		otpEnt.setProfilo(request.getProfilo());
		otpEnt.setTrxId(request.getTrxId());
		
		cacheClient.insert(request.getTrxId(), otpEnt);
		
		return otpDTO.getMsgResp();
	}
	
	
	public OtpBo getOtpCache(String key) {
		
		// cambiare poi
//		OtpBo response = new OtpBo();
//		try {
//			response = cacheClient.get(key);
//		}catch(Exception e) {
//			if(response == null) {
//				//TODO implementare lancio eccezzione
//			}
//		}
		
		return cacheClient.get(key);
	}
	
	public CheckOtpResponse CheckOtpCache(CheckOtpRequest request) {
		
		// cambiare poi
		OtpBo otpDto = new OtpBo();
		CheckOtpResponse response = new CheckOtpResponse();
		try {
			otpDto = cacheClient.get(request.getTrxId());
		}catch(Exception e) {
			
				response.setCodiceEsito("01"); response.setCheckOk(false); return response;
				
		}
		LocalTime timeStart = LocalTime.parse(otpDto.getGenerateTime());
		LocalTime timeEnd = LocalTime.parse(new SimpleDateFormat("HH:mm:ss").format(new java.util.Date().getTime()));
		// addo 1 minuti al tempo di start tempo massimo
		System.out.println("Time start piu 1 min ="+ timeStart.plusMinutes(1) );
		System.out.println("Time end ="+ timeEnd );
		// check se tempo limite e edentro start +1 minuto
		if(timeEnd.isAfter(timeStart.plusMinutes(1))) {
			//TODO implementare gestione caso errore
			response.setCodiceEsito("02"); response.setCheckOk(false); return response;
		}
		
		// checko effettivo otp
		if(!request.getOtp().equals(otpDto.getOtp()) || !request.getProfilo().equals(otpDto.getProfilo())) {
			//TODO implementare gestione caso errore
			response.setCodiceEsito("03"); response.setCheckOk(false); return response;
		}
		
		response.setCheckOk(true);
		response.setCodiceEsito("00");
		
		return response;
		}
	
	
	// da qui in poi i servici si connettono a db e cachano poi
	// si connette a db per fare ste operazioni, facciamo direttamente su client e via
	
//	public OtpBo insert(OtpRequest request) {
//		
//		OtpBo otpDto = new OtpBo();
//		
//		otpDto.setGenerateTime(new SimpleDateFormat("HH.mm.ss").format(new java.util.Date().getTime()));
//		otpDto.setOtp(request.getOtp());
//		otpDto.setUsername(request.getUsername());
//		otpDto.setProfilo(request.getProfilo());
//		OtpBo response = new OtpBo();
//		try {
//			response = otpRepo.save(otpDto);
//		}catch(Exception e) {
//			// implementare gestione errore
//		}
//		
//		return response;
//	}
//	
//	public Optional<OtpBo> get(Long trxId) {
//		
//		Optional<OtpBo> response = Optional.of(new OtpBo());
//		
//		try {
//			response = otpRepo.findById(trxId);
//		}catch( Exception e) {
//			// TODO DECIDERE SE IMPLEMENTARE QUI O MENO CONTROLLO SU OGGETTO
//		}
//		
//		return response;
//	}
//	
//	public CheckOtpResponse checkOtp(CheckOtpRequest request) {
//		
//		CheckOtpResponse response = new CheckOtpResponse();
//		
//		// implementare try catch
//		OtpBo otpDto = otpRepo.getReferenceById(request.getTrxId());
//		
//		if(otpDto == null) {
//			//TODO implementare gestione caso errore
//			response.setCodiceEsito("01"); response.setCheckOk(false); return response;
//		}
//		LocalTime timeStart = LocalTime.parse(otpDto.getGenerateTime());
//		LocalTime timeEnd = LocalTime.now();
//		// addo 1 minuti al tempo di start tempo massimo
//		timeStart.plusMinutes(1);
//		// check se tempo limite e edentro start +1 minuto
//		if(timeStart.isAfter(timeEnd)) {
//			//TODO implementare gestione caso errore
//			response.setCodiceEsito("02"); response.setCheckOk(false); return response;
//		}
//		
//		// checko effettivo otp
//		if(request.getOtp() != otpDto.getOtp() || request.getProfilo() != otpDto.getProfilo()) {
//			//TODO implementare gestione caso errore
//			response.setCodiceEsito("03"); response.setCheckOk(false); return response;
//		}
//		
//		response.setCheckOk(true);
//		response.setCodiceEsito("00");
//		
//		return response;
//	}
	
}
