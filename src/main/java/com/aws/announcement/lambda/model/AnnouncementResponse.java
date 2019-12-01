package com.aws.announcement.lambda.model;


import java.util.ArrayList;
import java.util.List;

public class AnnouncementResponse extends BaseResponse{
	
	public List<Announcement> getData() {
		return data;
	}

	public void setData(List<Announcement> data) {
		this.data = data;
	}

	private List<Announcement> data = new ArrayList<Announcement>();
	

}
