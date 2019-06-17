package com.aws.announcement.lambda.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.http.HttpStatus;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.aws.announcement.lambda.model.APISuccess;
import com.aws.announcement.lambda.model.Announcement;
import com.aws.announcement.lambda.model.AnnouncementResponse;
import com.aws.announcement.lambda.model.LambdaProxyResponse;
import com.aws.announcement.lambda.service.AnnouncementService;
import com.aws.announcement.lambda.service.AnnouncementServiceImpl;

public class AnnouncementServicePostDataHandler implements RequestStreamHandler {

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
	 LambdaLogger lambdaLogger = context.getLogger();
		
		LambdaProxyResponse lambdaProxyResponse = new LambdaProxyResponse();
		AnnouncementResponse announcementResponse = new AnnouncementResponse();
		
		AnnouncementService announcementService = new AnnouncementServiceImpl();
		
		Announcement announcement = announcementService.getAnnouncementObjectFromRequest(inputStream);

		if (announcement != null) {
			boolean outcome = announcementService.persistAnnouncementData(announcement,lambdaLogger);
			if (outcome) {
				APISuccess apiSuccess = announcementService.getSuccessResponse(HttpStatus.SC_CREATED,
						"Data Saved Successfully");
				announcementResponse.getData().add(announcement);
				lambdaProxyResponse.setStatusCode(HttpStatus.SC_CREATED);
				
				lambdaProxyResponse.setBody(announcementResponse.toString());

			} else {
				// handle error message
			}

		} else {
			// handle error message
		}

		lambdaLogger.log("Wrinting data to Stream");
		Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
		writer.write(lambdaProxyResponse.toString());
		writer.close();

	}

}
