package com.aws.announcement.lambda.util;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class DynamoDBUtil {

	private static DynamoDB dynamoDB;

	public static DynamoDB getDynamoDB(LambdaLogger lambdaLogger) {
		if (dynamoDB == null) {
			AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
			dynamoDB = new DynamoDB(client);
		}
		return dynamoDB;
	}
}
