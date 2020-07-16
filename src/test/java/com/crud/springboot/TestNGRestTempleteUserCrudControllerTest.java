package com.crud.springboot;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crud.springboot.model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestNGRestTempleteUserCrudControllerTest extends AbstractTestNGSpringContextTests{

	private RestTemplate restTemplate;
	private String responseBody;
	public String responseBodyPOST;
	
	private ResponseEntity<String> response;
	
	@BeforeClass
	public void init() {
		restTemplate = new RestTemplate();
	}
	
	@Test
	public void testSaveUser() {
		String addURI = "http://localhost:8080/SpringBootCRUDApp/customer/save";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");

		logger.info("Add URL :" + addURI);
		User user = new User("Test name1", 29, 35000.00);
		String jsonBody = asJsonString(user);
		System.out.println("\n\n" + jsonBody);
		HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);

		response = restTemplate.postForEntity(addURI, entity, String.class);
		responseBodyPOST = response.getBody();

		// Write response to file
		responseBody = response.getBody().toString();
		System.out.println("responseBody --->" + responseBody);
		
		Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}
