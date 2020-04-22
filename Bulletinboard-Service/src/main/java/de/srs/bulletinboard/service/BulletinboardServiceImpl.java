package de.srs.bulletinboard.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import de.srs.bulletinboard.model.Announcement;
import de.srs.bulletinboard.model.AnnouncementType;
import de.srs.bulletinboard.model.Reply;
import de.srs.bulletinboard.repository.AnnouncementRepository;
import de.srs.bulletinboard.repository.AnnouncementTypeRepository;
import de.srs.bulletinboard.repository.ReplyRepository;

@Service
public class BulletinboardServiceImpl implements BulletinboardService {

	@Autowired
	AnnouncementRepository announcementRepository;
	
	@Autowired
	ReplyRepository replyRepository;
	
	@Autowired
	AnnouncementTypeRepository announcementTypeRepository; 
	
	@Override
	public Page<Announcement> getAllAnnouncements(Integer issuerId, Date creationDate, Integer announcementId, Integer page) {

		return announcementRepository.getAllAnnouncements(issuerId, creationDate, announcementId, page);
	}

	@Override
	public Announcement getAnnouncement(Integer announcementId) {

		return announcementRepository.findByExternalId(announcementId).get();
		
	}

	@Override
	@Modifying
	@Transactional
	public void addAnnouncement(Announcement announcement) {
		announcement.setCreationDate(new Date());
		
		announcementRepository.save(announcement);
	}

	@Override
	public List<Reply> getAllReplies(Integer announcementId) {
		
		return replyRepository.findByAnnouncementId(announcementId);
	}

	@Override
	@Modifying
	@Transactional
	public void addReply(Reply reply) {
		
		reply.setCreationDate(new Date());
		replyRepository.save(reply);
	}
	
	@Override
	
	public List<AnnouncementType> getAllAnnouncementTypes() {
		return announcementTypeRepository.findAll();
	}

	

}
