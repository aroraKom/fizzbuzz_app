package com.solution.fizzbuzz.utility;

import org.json.JSONObject;

public class Utility {
	public static JSONObject getFizzBuzzValue(int inputNumber) {
		JSONObject resultantJSON = new JSONObject();
		resultantJSON.put("in",inputNumber);
		String result= String.valueOf(inputNumber);
		if(inputNumber%3==0) {
			result="fizz";
		}
		if(inputNumber%5==0) {
			result="buzz";
		}
		if((inputNumber%3==0)&& (inputNumber%5==0)) {
			result="fizzbuzz";
		}
		resultantJSON.put("result",result);
		return resultantJSON;
	}

	public static String validateInputBody(String body) {
		String result="";
	
		JSONObject request =new JSONObject(body);
		if(request.has("start") && request.has("stop")) {
			if(request.get("start") instanceof Integer && request.get("stop") instanceof Integer) {
				result="";
			}
			else {
				result="Start and Stop value should be an integer";
			}
		}
		else {
			result="Missing start or stop value from request body";
		}
		
		return result;
		
	}
	
}
