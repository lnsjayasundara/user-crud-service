package com.crud.springboot;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testng.annotations.Test;

@SpringBootTest(classes = UserCrudServiceApplication.class)
@AutoConfigureMockMvc
public class TestNGUserCrudControllerTest extends AbstractTestNGSpringContextTests{

	@Autowired
	private MockMvc mock;
	
	@Test
	public void testGetUser() throws Exception {
		mock.perform(MockMvcRequestBuilders.get("/cutomer/filter/{id}", 0).accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.*").isNotEmpty())
		.andExpect(MockMvcResultMatchers.jsonPath("$.*.id").isNotEmpty());
	}
}
