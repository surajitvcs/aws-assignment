package com.aws.announcement.lambda.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeStampUtil {
	public static final String TIMEZONE_BASELINE = "UTC";
	public static final String TIMESTAMP_WITH_MILLISECONDS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String TIMESTAMP_WITHOUT_MILLISECONDS = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String TIMESTAMP_WITHOUT_MILLISECONDS_WITHOUT_Z = "yyyy-MM-dd'T'HH:mm:ss";

	public static String getFormattedTimestamp(Date date) {
		return getDateFormat(TIMESTAMP_WITH_MILLISECONDS).format(date);
	}

	public static DateFormat getDateFormat(String pattern) {
		SimpleDateFormat result = new SimpleDateFormat(pattern);
		result.setTimeZone(TimeZone.getTimeZone(TIMEZONE_BASELINE));
		return result;
	}

}
