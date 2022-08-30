package com.seor0.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seor0.cache.model.OtpBo;
import com.seor0.cache.service.OtpService;
import com.seor0.cache.service.request.CheckOtpRequest;
import com.seor0.cache.service.request.OtpRequest;
import com.seor0.cache.service.response.CheckOtpResponse;

@RestController
@RequestMapping("cache/otp")
public class OtpController {

	@Autowired
	private OtpService otpService;
	
	// insert
	@RequestMapping("insert")
	public String insertCache(@RequestBody OtpRequest request) {
		
		return otpService.inserisciCache(request);
	}
	// inserisci e invia
	@RequestMapping("insert/2")
	public String inserteSend(@RequestBody OtpRequest request) {
		
		return otpService.inserisceInvia(request);
	}
	// get 
	@RequestMapping("get")
	public OtpBo getCache(@RequestBody OtpRequest request) {
		return otpService.getOtpCache(request.getTrxId());
	}
	// check
	@RequestMapping("check")
	public CheckOtpResponse checkOtp(@RequestBody CheckOtpRequest request) {
		return otpService.CheckOtpCache(request);
	}
	// update
	@RequestMapping("put")
	public String putCache(@RequestBody OtpRequest request) {
		return null;
		//TODO vedere se ci serve o meno
	}
	
	
	// cancello
	// pretty useless, si auto elimina dopo 120 se nn letta e 360 totali
	
}
