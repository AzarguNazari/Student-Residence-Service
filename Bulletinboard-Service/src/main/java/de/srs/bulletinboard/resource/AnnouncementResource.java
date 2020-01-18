package de.srs.bulletinboard.resource;

import org.springframework.hateoas.ResourceSupport;

import de.srs.bulletinboard.model.Announcement;

public class AnnouncementResource extends ResourceSupport {

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
