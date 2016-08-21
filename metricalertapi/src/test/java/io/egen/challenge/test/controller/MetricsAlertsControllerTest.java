package io.egen.challenge.test.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.egen.challenge.Application;
import io.egen.challenge.test.JVMArgumentsInitializer;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers = { JVMArgumentsInitializer.class }, classes = {Application.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MetricsAlertsControllerTest {
	
	
	private Long currentTimeStamp = System.currentTimeMillis();

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void createMetrics() throws Exception {
		String createMetricJsonRequest = "{\"timeStamp\": \"" + String.valueOf(currentTimeStamp) + "\", \"value\": \"150\"}";
		this.mockMvc.perform(post("/createMetrics").contentType("application/json").content(createMetricJsonRequest))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$.timeStamp").isNumber())
				.andExpect(jsonPath("$.value").isNumber());
	}
	
	@Test
	public void createMetricsForOverWeightAlert() throws Exception {
		String createMetricJsonRequest = "{\"timeStamp\": \"" + String.valueOf(currentTimeStamp+100) + "\", \"value\": \"200\"}";
		this.mockMvc.perform(post("/createMetrics").contentType("application/json").content(createMetricJsonRequest))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$.timeStamp").isNumber())
				.andExpect(jsonPath("$.value").isNumber());
	}
	
	@Test
	public void createMetricsForUnderWeightAlert() throws Exception {
		String createMetricJsonRequest = "{\"timeStamp\": \"" + String.valueOf(currentTimeStamp+200) + "\", \"value\": \"40\"}";
		this.mockMvc.perform(post("/createMetrics").contentType("application/json").content(createMetricJsonRequest))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$.timeStamp").isNumber())
				.andExpect(jsonPath("$.value").isNumber());
	}

	@Test
	public void getMetrics() throws Exception {

		this.mockMvc.perform(get("/getMetrics"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$[0].timeStamp").isNumber())
				.andExpect(jsonPath("$[0].value").isNumber());;
	}
	
	@Test
	public void getMetricsAlerts() throws Exception {
		this.mockMvc.perform(get("/getAlerts"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$[0].timeStamp").isNumber())
				.andExpect(jsonPath("$[0].alertMessge").isString());
	}

	@Test
	public void getMetricsAlertsForTimeRange() throws Exception {
		this.mockMvc.perform(get("/getAlertsForTimeRange")
							 .param("startTime", String.valueOf(currentTimeStamp - 10000))
							 .param("endTime", String.valueOf(currentTimeStamp + 10000)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$[0].timeStamp").isNumber())
				.andExpect(jsonPath("$[0].alertMessge").isString());
	}
	
	@Test
	public void getMetricsForTimeRange() throws Exception {
        Long currentTimeStamp = System.currentTimeMillis();
		this.mockMvc.perform(get("/getMetricsForTimeRange")
							 .param("startTime", String.valueOf(currentTimeStamp-10000))
							 .param("endTime", String.valueOf(currentTimeStamp+10000)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$[0].timeStamp").isNumber())
				.andExpect(jsonPath("$[0].value").isNumber());
	}

}