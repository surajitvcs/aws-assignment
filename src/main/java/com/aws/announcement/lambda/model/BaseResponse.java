package com.aws.announcement.lambda.model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseResponse {

	public String getJson() {
		final Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
}
