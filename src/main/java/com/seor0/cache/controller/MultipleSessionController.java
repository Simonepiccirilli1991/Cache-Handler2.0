package com.seor0.cache.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seor0.cache.service.request.SessionRequest;


@RestController
@RequestMapping("multiple/cache")
public class MultipleSessionController {

	
	@RequestMapping("insert/{keycache}/session/sy")
	public String insertCache(@PathVariable(value = "keycache") String keycache ,
			@PathVariable(value = "cachename") String cachename ,@RequestBody SessionRequest request) {
		
		
		return "";
	}
}
