package srs.bulletinboard.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import srs.bulletinboard.model.Announcement;
import srs.bulletinboard.model.AnnouncementType;
import srs.bulletinboard.model.Reply;

public interface BulletinboardService {
	
	public Page<Announcement> getAllAnnouncements(Integer issuerId, Date creationDate, Integer announcementId, Integer page);
	
	public Announcement getAnnouncement(Integer announcementId);
	
	public void addAnnouncement(Announcement announcement);
	
	public List<Reply> getAllReplies(Integer announcementId);
	
	public void addReply(Reply reply);
	
	public List<AnnouncementType> getAllAnnouncementTypes(); 


}
