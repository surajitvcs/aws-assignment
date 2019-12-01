package com.aws.announcement.lambda.model;


import java.util.List;

public class ErrorResponse extends BaseResponse {

	private List<APIError> data;

	public List<APIError> getData() {
		return data;
	}

	public void setData(List<APIError> data) {
		this.data = data;
	}

}
