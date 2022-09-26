package com.seor0.cache.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seor0.cache.config.CacheClientOtp;
import com.seor0.cache.fragment.service.GeneraInviaService;
import com.seor0.cache.service.OtpService;
import com.seor0.cache.service.request.GeneraInviaRequest;
import com.seor0.cache.service.request.OtpRequest;
import com.seor0.cache.service.response.GeneraInviaResponse;

@SpringBootTest
@AutoConfigureMockMvc
public class CacheControllerTest {

	@Autowired
	ObjectMapper objectMapper;
	@Mock
	GeneraInviaService gis;
	@Mock 
	RestTemplate rt;
	@Mock
	CacheClientOtp ch;
	@Autowired 
	protected MockMvc mvc;
	@InjectMocks
	OtpService otpService;
	
	@Test
	public void generaInviaContrOK() throws  Exception {
		
		OtpRequest iRequest = new OtpRequest("username", "mail", "profilo", "a1");
		
		
		GeneraInviaResponse response = new GeneraInviaResponse();
		response.setEsito("00");
		response.setMsgResp("Otp generato correttamente");
		response.setOoTp("1234");
		
		//Mockito.when(rt.postForObject(anyString(), any(Object.class),Mockito.eq(GeneraInviaResponse.class))).thenReturn(response);
		when(gis.generaeInvia(Mockito.eq("username"), Mockito.eq("mail"))).thenReturn(response);
		
//		MvcResult result = (MvcResult) mvc.perform(post("http://localhost:8083/cache/otp/insert/2")
//				.contentType("application/json")
//				.content(objectMapper.writeValueAsString(iRequest)))
//		.andExpect(status().isOk());
//		
//		String resp = result.getResponse().toString();
		String resp = otpService.inserisceInvia(iRequest);
		assertThat(resp).isSameAs("Otp generato correttamente");
	}
}
