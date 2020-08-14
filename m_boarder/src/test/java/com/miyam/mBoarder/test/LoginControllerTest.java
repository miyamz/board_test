package com.miyam.mBoarder.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginControllerTest extends MBoarderApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testLoginView() throws Exception {
		mockMvc.perform(get("/login.do"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("text/html;charset=UTF-8"));
	}

	@Test
	public void testLoginRequest() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userID", "root");
		map.put("passwd", "6b1944947aa2ae57c54288b1381ea0c94cffb55c50cc0174d302af2a6ee8bcf4");

		HttpEntity<Map<String,String>> entity = new HttpEntity<>(map);
		
        String requestBody = objectMapper.writeValueAsString(entity);
		mockMvc.perform(post("/login.do")
					.content(requestBody)
			        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk());
	}
}
