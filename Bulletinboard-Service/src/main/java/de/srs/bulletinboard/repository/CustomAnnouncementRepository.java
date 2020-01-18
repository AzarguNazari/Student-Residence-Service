package de.srs.bulletinboard.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import de.srs.bulletinboard.model.Announcement;

public interface CustomAnnouncementRepository {

	public Page<Announcement> getAllAnnouncements(Integer issuerId, Date creationDate, Integer externalId, Integer page);
	
}
