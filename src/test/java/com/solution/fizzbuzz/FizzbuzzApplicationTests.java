package com.solution.fizzbuzz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.solution.fizzbuzz.controller.FizzBuzzController;
import com.solution.fizzbuzz.utility.Utility;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(FizzBuzzController.class)
class FizzbuzzApplicationTests {
	@Autowired
	MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	void getFizzBuzzValueTestWhenNumberIsDivisibleBy15() throws JSONException {
		int testNumber = 15;
		JSONObject response = Utility.getFizzBuzzValue(testNumber);
		assertEquals(response.get("result"), "fizzbuzz");
	}

	@Test
	void getFizzBuzzValueTestWhenNumberIsDivisibleBy3() throws JSONException {
		int testNumber = 3;
		JSONObject response = Utility.getFizzBuzzValue(testNumber);
		assertEquals(response.get("result"), "fizz");
	}

	@Test
	void getFizzBuzzValueTestWhenNumberIsDivisibleBy5() throws JSONException {
		int testNumber = 5;
		JSONObject response = Utility.getFizzBuzzValue(testNumber);
		assertEquals(response.get("result"), "buzz");
	}

	@Test
	void validateInputBodyTestWhenBodyIsEmpty() throws JSONException {
		String response = Utility.validateInputBody(new JSONObject().toString());
		assertEquals(response, "Missing start or stop value from request body");
	}

	@Test
	void validateInputBodyTestWhenBodyIsMissingStartKey() throws JSONException {
		JSONObject request = new JSONObject();
		request.put("stop", 3);
		String response = Utility.validateInputBody(request.toString());
		assertEquals(response, "Missing start or stop value from request body");
	}

	@Test
	void validateInputBodyTestWhenBodyIsMissingStopKey() throws JSONException {
		JSONObject request = new JSONObject();
		request.put("start", 5);
		String response = Utility.validateInputBody(request.toString());
		assertEquals(response, "Missing start or stop value from request body");
	}

	@Test
	void validateInputBodyTestWhenStopKeyIsNotInteger() throws JSONException {
		JSONObject request = new JSONObject();
		request.put("start", 5);
		request.put("stop", "hhj5");
		String response = Utility.validateInputBody(request.toString());
		assertEquals(response, "Start and Stop value should be an integer");
	}

	@Test
	void validateInputBodyTestWhenStartKeyIsNotInteger() throws JSONException {
		JSONObject request = new JSONObject();
		request.put("start", "jjk");
		request.put("stop", "5");
		String response = Utility.validateInputBody(request.toString());
		assertEquals(response, "Start and Stop value should be an integer");
	}

	@Test
	void testPostCall() throws Exception {
		JSONObject request = new JSONObject();
		request.put("start", 4);
		request.put("stop", 5);

		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post("/fizzbuzz").contentType(MediaType.APPLICATION_JSON_VALUE).content(request.toString()))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

}
