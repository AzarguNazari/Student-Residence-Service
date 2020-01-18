package de.srs.bulletinboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.srs.bulletinboard.model.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer>{

	public List<Reply> findByAnnouncementId(Integer announcementId);
}
