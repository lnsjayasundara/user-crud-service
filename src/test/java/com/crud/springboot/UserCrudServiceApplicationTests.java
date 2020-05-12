package com.crud.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import com.crud.springboot.model.ResponseMessage;
import com.crud.springboot.model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserCrudServiceApplicationTests {
	/*
	 * @Autowired UserRepository userRepo;
	 */

	@Autowired
	private MockMvc mvc;

	@Test
	public void testGetAllUserApi() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/cutomer/filter/{id}", 0).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*.id").isNotEmpty());

	}

	@Test
	public void testGetUserByIdApi() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/cutomer/filter/{id}", 3).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());

	}

	@Test
	public void testSaveUserAPISuccess() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post("/customer/save").content(asJsonString(new User("Test name", 29, 35000.00)))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"));
	}

	@Test
	public void tesSaveUserAPIFailed() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/customer/save")
				.content(asJsonString(new User("Test name", null, 35000.00))).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isExpectationFailed())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Failed"));
	}

	@Test
	public void testUpdateUserAPI() throws Exception {
		MvcResult mockResult = mvc
				.perform(MockMvcRequestBuilders.put("/customer/update").contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(new User(Long.valueOf(13), "Test Name", 33, 38000.00, "Y"))))
				.andReturn();
		assertEquals(201, mockResult.getResponse().getStatus());
		ResponseMessage res = mapFromJson(mockResult.getResponse().getContentAsString(), ResponseMessage.class);
		assertEquals(res.getStatus(), "Success");

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
