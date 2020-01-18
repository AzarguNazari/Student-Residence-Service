package de.srs.bulletinboard.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import de.srs.bulletinboard.model.Announcement;
import de.srs.bulletinboard.model.AnnouncementType;
import de.srs.bulletinboard.model.Reply;

public interface BulletinboardService {
	
	public Page<Announcement> getAllAnnouncements(Integer issuerId, Date creationDate, Integer announcementId, Integer page);
	
	public Announcement getAnnouncement(Integer announcementId);
	
	public void addAnnouncement(Announcement announcement);
	
	public List<Reply> getAllReplies(Integer announcementId);
	
	public void addReply(Reply reply);
	
	public List<AnnouncementType> getAllAnnouncementTypes(); 


}
