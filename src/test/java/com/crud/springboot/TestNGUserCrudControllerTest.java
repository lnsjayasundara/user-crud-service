package com.crud.springboot;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crud.springboot.model.ResponseMessage;
import com.crud.springboot.model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = UserCrudServiceApplication.class)
@AutoConfigureMockMvc
public class TestNGUserCrudControllerTest extends AbstractTestNGSpringContextTests {

	private User user;
	
	@Autowired
	private MockMvc mock;

	@BeforeClass
	public void intData() {
		user = new User("Test name", 29, 35000.00);
	}
	
	@Test
	public void testGetUserList() throws Exception {
		mock.perform(MockMvcRequestBuilders.get("/cutomer/filter/{id}", 0).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*.id").isNotEmpty());
	}

	@Test
	public void testSaveUser() throws Exception {
		mock.perform(
			MockMvcRequestBuilders.post("/customer/save").content(asJsonString(user))
			.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"));
	}
	
	@Test(dependsOnMethods = "testSaveUser")
	public void testGetUserByIdApi() throws Exception {
		MvcResult result = mock.perform(MockMvcRequestBuilders.get("/cutomer/filter/{id}", 9).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
				.andReturn();
		user = mapFromJson(result.getResponse().getContentAsString(),User.class);		
	}
	
	@Test(dependsOnMethods = "testGetUserByIdApi")
	public void testUpdateUserAPI() throws Exception {
		user.setName("Test name Update");
		MvcResult mockResult = mock
				.perform(MockMvcRequestBuilders.put("/customer/update").contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(user)))
				.andReturn();
		Assert.assertEquals(mockResult.getResponse().getStatus(),201);
		ResponseMessage res = mapFromJson(mockResult.getResponse().getContentAsString(), ResponseMessage.class);
		Assert.assertEquals(res.getStatus(), "Success");

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
