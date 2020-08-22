package srs.bulletinboard.resource;

import java.util.List;

import srs.bulletinboard.util.resource.AbstractListResource;

public class AnnouncementListResource extends AbstractListResource{
	
	private List<AnnouncementResource> announcements;
	
	public AnnouncementListResource(List<AnnouncementResource> announcements, Integer pageNumber, Integer totalPages, Long totalElements) {
		super(pageNumber, totalPages, totalElements);
		this.setAnnouncements(announcements);
	}

	public List<AnnouncementResource> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(List<AnnouncementResource> announcements) {
		this.announcements = announcements;
	}

}
