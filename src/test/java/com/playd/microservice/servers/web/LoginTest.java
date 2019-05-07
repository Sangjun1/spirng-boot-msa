package com.playd.microservice.servers.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playd.microservices.servers.web.WebServer;
import com.playd.microservices.servers.web.login.model.LoginInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebServer.class)
@WebAppConfiguration
public class LoginTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void readLoginTest() throws Exception {

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUsername("jun");
		loginInfo.setPassword("1111");


		ObjectMapper objectMapper = new ObjectMapper();

		String username = "jun";

		mvc.perform(
				post("/api/auth/login")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(objectMapper.writeValueAsString(loginInfo))
		).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.username", is(loginInfo.getUsername().toUpperCase())))
		;
	}
}
