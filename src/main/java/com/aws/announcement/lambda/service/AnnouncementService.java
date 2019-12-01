package com.aws.announcement.lambda.service;


import java.io.InputStream;
import java.util.List;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.aws.announcement.lambda.model.APISuccess;
import com.aws.announcement.lambda.model.Announcement;

public interface AnnouncementService {
	public  boolean persistAnnouncementData(Announcement announcement, LambdaLogger lambdaLogger) ;
	public  List<Announcement> getAllAnnouncementData(LambdaLogger lambdaLogger) ;
	public  Announcement getAnnouncementObjectFromRequest(InputStream inputStream);
	public  APISuccess getSuccessResponse(Integer httpStatusCode, String statusMessage);
	
}
