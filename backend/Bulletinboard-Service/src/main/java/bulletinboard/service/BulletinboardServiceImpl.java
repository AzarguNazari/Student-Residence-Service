package bulletinboard.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import bulletinboard.model.Announcement;
import bulletinboard.model.AnnouncementType;
import bulletinboard.model.Reply;
import bulletinboard.repository.AnnouncementRepository;
import bulletinboard.repository.AnnouncementTypeRepository;
import bulletinboard.repository.ReplyRepository;

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
