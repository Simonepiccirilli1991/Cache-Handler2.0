package com.seor0.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seor0.cache.service.AppSessionService;
import com.seor0.cache.service.request.AppSessionRequest;
import com.seor0.cache.service.response.AppSessionResponse;

@RestController
@RequestMapping("app")
public class AppSessionController {

	@Autowired
	private AppSessionService apService;
	
	@PostMapping("create")
	public ResponseEntity<AppSessionResponse> creaSessioneApp(@RequestBody AppSessionRequest request, 
			@RequestHeader HttpHeaders header){
		if(ObjectUtils.isEmpty(header.getFirst("APP_NAME")) || ObjectUtils.isEmpty(header.getFirst("SEC_SESSION")) ) {
			// TODO implementare eccezzione invalid request
		}	
		request.setAppName(header.getFirst("APP_NAME"));
		request.setSecSession(header.getFirst("SEC_SESSION"));
		//System.out.println("qui ci sono");	
			
		return apService.creaSession(request);
	}
}
