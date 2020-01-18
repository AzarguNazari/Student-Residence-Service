package de.srs.bulletinboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.srs.bulletinboard.model.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer>,CustomAnnouncementRepository {

	public Optional<Announcement> findByExternalId(Integer externalId);
}
