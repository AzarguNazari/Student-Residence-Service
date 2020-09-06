package bulletinboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bulletinboard.model.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer>,CustomAnnouncementRepository {

	public Optional<Announcement> findByExternalId(Integer externalId);
}
