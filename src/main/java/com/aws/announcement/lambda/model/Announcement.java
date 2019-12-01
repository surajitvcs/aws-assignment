package com.aws.announcement.lambda.model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Announcement {

	private String title;
	private String description;
	private String announcementDate;
	private String id;

	public Announcement() {

	}

	public Announcement(String json) {
		Gson gson = new Gson();
		Announcement announcementRequest = gson.fromJson(json, Announcement.class);
		this.title = announcementRequest.getTitle();
		this.description = announcementRequest.getDescription();
		this.announcementDate = announcementRequest.getAnnouncementDate();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(String announcementDate) {
		this.announcementDate = announcementDate;
	}

	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
