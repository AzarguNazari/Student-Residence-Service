package de.srs.bulletinboard.resource;

import org.springframework.hateoas.EntityModel;

import de.srs.bulletinboard.model.Announcement;

public class AnnouncementResource extends EntityModel {

	private Announcement announcement;
	
	public AnnouncementResource(Announcement announcement) {
		this.announcement = announcement;
	
	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}
}
