package srs.bulletinboard.repository;

import java.util.Date;

import org.springframework.data.domain.Page;

import srs.bulletinboard.model.Announcement;

public interface CustomAnnouncementRepository {

	public Page<Announcement> getAllAnnouncements(Integer issuerId, Date creationDate, Integer externalId, Integer page);
	
}
