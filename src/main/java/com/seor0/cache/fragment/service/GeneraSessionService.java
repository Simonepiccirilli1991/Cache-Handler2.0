package com.seor0.cache.fragment.service;

import org.springframework.stereotype.Service;

@Service
public class GeneraSessionService {

	public String generaSessionId(String bt, String abi, String time,String canale) {
		
		String respose = bt+abi+time+canale;
		
		return respose;
	}
	
}
