package com.aws.announcement.lambda.model;

public class APISuccess extends APIResponseStatus {

	private String statusMessage;

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
