package de.srs.bulletinboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.srs.bulletinboard.model.AnnouncementType;

@Repository
public interface AnnouncementTypeRepository extends JpaRepository<AnnouncementType, Integer>,CustomAnnouncementRepository {

}

