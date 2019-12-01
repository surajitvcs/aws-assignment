package com.aws.announcement.lambda.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;


import org.apache.http.HttpStatus;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.aws.announcement.lambda.model.APISuccess;
import com.aws.announcement.lambda.model.Announcement;
import com.aws.announcement.lambda.model.LambdaProxyResponse;
import com.aws.announcement.lambda.model.AnnouncementResponse;
import com.aws.announcement.lambda.service.AnnouncementService;
import com.aws.announcement.lambda.service.AnnouncementServiceImpl;

public class AnnouncementServiceGetAllHandler implements RequestStreamHandler {

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
		LambdaLogger lambdaLogger = context.getLogger();
		lambdaLogger.log("Method Start :Inside AnnouncementServiceGetAllHandler  Lambda handleRequest() : ");
		
		AnnouncementService announcementService = new AnnouncementServiceImpl();

		LambdaProxyResponse lambdaProxyResponse = new LambdaProxyResponse();
		List<Announcement> announcementList = announcementService.getAllAnnouncementData(lambdaLogger);

		if (announcementList.isEmpty()) {
			APISuccess apiSuccess = announcementService.getSuccessResponse(HttpStatus.SC_NO_CONTENT,
					"The request has fulfilled the request but does not need to return an entity body");
			lambdaProxyResponse.setStatusCode(HttpStatus.SC_NO_CONTENT);
			

		} else {
			APISuccess apiSuccess = announcementService.getSuccessResponse(HttpStatus.SC_OK,
					"Data Retrived successfully");
			AnnouncementResponse announcementResponse = new AnnouncementResponse();
			announcementResponse.setData(announcementList);
			lambdaProxyResponse.setBody(announcementResponse.toString());
			lambdaProxyResponse.setStatusCode(HttpStatus.SC_OK);
			
		}
		Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
		writer.write(lambdaProxyResponse.toString());
		writer.close();
	}

}
