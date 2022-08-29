package com.seor0.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seor0.cache.model.OtpBo;
import com.seor0.cache.service.OtpService;
import com.seor0.cache.service.request.OtpRequest;

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
	
	// get 
	public OtpBo getCache(OtpRequest request) {
		return otpService.getOtpCache(request.getTrxId());
	}
	// check
	
	// update
	
	// cancello
	
	
}
