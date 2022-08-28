package com.seor0.cache.service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seor0.cache.model.OtpBo;
import com.seor0.cache.repo.OtpRepo;
import com.seor0.cache.service.request.CheckOtpRequest;
import com.seor0.cache.service.request.OtpRequest;
import com.seor0.cache.service.response.CheckOtpResponse;

@Service
public class OtpService {

	
	@Autowired
	OtpRepo otpRepo;
	
	
	public OtpBo insert(OtpRequest request) {
		
		OtpBo otpDto = new OtpBo();
		
		otpDto.setGenerateTime(new SimpleDateFormat("HH.mm.ss").format(new java.util.Date().getTime()));
		otpDto.setOtp(request.getOtp());
		otpDto.setUsername(request.getUsername());
		otpDto.setProfilo(request.getProfilo());
		OtpBo response = new OtpBo();
		try {
			response = otpRepo.save(otpDto);
		}catch(Exception e) {
			// implementare gestione errore
		}
		
		return response;
	}
	
	public Optional<OtpBo> get(Long trxId) {
		
		Optional<OtpBo> response = Optional.of(new OtpBo());
		
		try {
			response = otpRepo.findById(trxId);
		}catch( Exception e) {
			// TODO DECIDERE SE IMPLEMENTARE QUI O MENO CONTROLLO SU OGGETTO
		}
		
		return response;
	}
	
	public CheckOtpResponse checkOtp(CheckOtpRequest request) {
		
		CheckOtpResponse response = new CheckOtpResponse();
		
		// implementare try catch
		OtpBo otpDto = otpRepo.getReferenceById(request.getTrxId());
		
		if(otpDto == null) {
			//TODO implementare gestione caso errore
			response.setCodiceEsito("01"); response.setCheckOk(false); return response;
		}
		LocalTime timeStart = LocalTime.parse(otpDto.getGenerateTime());
		LocalTime timeEnd = LocalTime.now();
		// addo 1 minuti al tempo di start tempo massimo
		timeStart.plusMinutes(1);
		// check se tempo limite e edentro start +1 minuto
		if(timeStart.isAfter(timeEnd)) {
			//TODO implementare gestione caso errore
			response.setCodiceEsito("02"); response.setCheckOk(false); return response;
		}
		
		// checko effettivo otp
		if(request.getOtp() != otpDto.getOtp() || request.getProfilo() != otpDto.getProfilo()) {
			//TODO implementare gestione caso errore
			response.setCodiceEsito("03"); response.setCheckOk(false); return response;
		}
		
		response.setCheckOk(true);
		response.setCodiceEsito("00");
		
		return response;
	}
	
}
