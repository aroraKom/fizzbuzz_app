package com.solution.fizzbuzz.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.solution.fizzbuzz.response.APIResponse;
import com.solution.fizzbuzz.utility.Utility;

@RestController
public class FizzBuzzController {

	@RequestMapping(path="/fizzbuzz",method=RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<Object>  retrieveDetailsForCourse(@RequestBody String input) {
		APIResponse response = null;
		try {
			
			if(input==null || input.isEmpty() || input.isBlank() || input =="{}") {
				response = new APIResponse(HttpStatus.BAD_REQUEST, "Invalid Input body");
				return new ResponseEntity<Object>(response,response.getStatus());
			}
			String validateResponse=Utility.validateInputBody(input);
			if(validateResponse!="") {
				response = new APIResponse(HttpStatus.BAD_REQUEST, validateResponse);
				return new ResponseEntity<Object>(response,response.getStatus());
			}
			else {
				JSONArray resultantArray = new JSONArray();
				JSONObject request =new JSONObject(input);
				// get value of start request
				resultantArray.put(Utility.getFizzBuzzValue(request.getInt("start")));
				// get value of stop request
				resultantArray.put(Utility.getFizzBuzzValue(request.getInt("stop")));
				
				return new ResponseEntity<Object>(resultantArray.toString(),HttpStatus.OK);
			}
		}
		catch(Exception e) {
			response = new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error while processing the request");
			return new ResponseEntity<Object>(response,response.getStatus());
		}
		
	}

}
