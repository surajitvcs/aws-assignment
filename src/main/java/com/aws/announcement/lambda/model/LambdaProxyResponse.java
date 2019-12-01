package com.aws.announcement.lambda.model;


import java.util.Map;

public class LambdaProxyResponse extends BaseResponse{

	private Boolean isBase64Encoded = false;
	private Integer statusCode;
	private Map<String, String> headers;
	private String body;
	private Map<String, String> multiValueHeaders;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, String> getMultiValueHeaders() {
		return multiValueHeaders;
	}

	public void setMultiValueHeaders(Map<String, String> multiValueHeaders) {
		this.multiValueHeaders = multiValueHeaders;
	}

	public Boolean getIsBase64Encoded() {
		return isBase64Encoded;
	}

	public void setIsBase64Encoded(Boolean isBase64Encoded) {
		this.isBase64Encoded = isBase64Encoded;
	}

}
