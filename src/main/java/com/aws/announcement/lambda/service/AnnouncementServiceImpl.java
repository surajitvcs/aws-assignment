package com.aws.announcement.lambda.service;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.aws.announcement.lambda.model.APIError;
import com.aws.announcement.lambda.model.APISuccess;
import com.aws.announcement.lambda.model.Announcement;
import com.aws.announcement.lambda.util.DateTimeStampUtil;
import com.aws.announcement.lambda.util.DynamoDBConstant;
import com.aws.announcement.lambda.util.DynamoDBUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AnnouncementServiceImpl implements AnnouncementService{

	public  boolean persistAnnouncementData(Announcement announcement, LambdaLogger lambdaLogger){
		boolean outcome = false;
		lambdaLogger.log("Method Start : Inside Persist AnnounceData method()");
		DynamoDB dynamoDb = DynamoDBUtil.getDynamoDB(lambdaLogger);
		
		
		Table table = dynamoDb.getTable(DynamoDBConstant.TABLE_ANNOUNCEMENT);
		Item item = new Item().withPrimaryKey(DynamoDBConstant.ID, "An-" + Math.random())
				.withString(DynamoDBConstant.TITLE, announcement.getTitle())
				.withString(DynamoDBConstant.DESCRIPTION, announcement.getDescription())
				.withString(DynamoDBConstant.DATE, DateTimeStampUtil.getFormattedTimestamp(new Date()));

		PutItemOutcome itemOutcome = table.putItem(item);
		outcome = itemOutcome.getPutItemResult()!=null?true:false;
		lambdaLogger.log("Method End :Inside Persist AnnounceData method()");
		return outcome;

	}

	public  List<Announcement> getAllAnnouncementData(LambdaLogger lambdaLogger) {
		lambdaLogger.log("Method Start : Inside getAllAnnouncementData method()");
		List<Announcement> announcementList = new ArrayList<Announcement>();

		DynamoDB dynamoDB = DynamoDBUtil.getDynamoDB( lambdaLogger);
		Table table = dynamoDB.getTable(DynamoDBConstant.TABLE_ANNOUNCEMENT);

		for (Item item : table.scan()) {
			Announcement announcement = new Announcement();
			announcement.setTitle(item.getString(DynamoDBConstant.TITLE));
			announcement.setDescription(item.getString(DynamoDBConstant.DESCRIPTION));
			announcement.setAnnouncementDate(item.getString(DynamoDBConstant.DATE));
			announcementList.add(announcement);
		}
		lambdaLogger.log("Method end : Inside getAllAnnouncementData method()");
		return announcementList;
	}

	public  Announcement getAnnouncementObjectFromRequest(InputStream inputStream) {
		Announcement announcement = null;
		JsonParser parser = new JsonParser();
		Reader reader = new BufferedReader(new InputStreamReader(inputStream));
		JsonObject event = (JsonObject) parser.parse(reader);

		if (event.get(DynamoDBConstant.BODY) != null) {
			announcement = new Announcement(event.get(DynamoDBConstant.BODY).getAsString());
		}

		return announcement;
	}

	public  APIError getErrorResponse(String errorCode, Integer httpStatusCode, String errorMessage) {
		APIError apiError = new APIError();
		apiError.setErrorCode(errorCode);
		apiError.setHttpStatusCode(httpStatusCode);
		apiError.setErrorMessage(errorMessage);
		return apiError;
	}

	public  APISuccess getSuccessResponse(Integer httpStatusCode, String statusMessage) {
		APISuccess apiSuccess = new APISuccess();
		apiSuccess.setHttpStatusCode(httpStatusCode);
		apiSuccess.setStatusMessage(statusMessage);
		return apiSuccess;
	}

}
