package srs.bulletinboard.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import srs.bulletinboard.model.Announcement;
import srs.bulletinboard.util.Constants;

@Repository
public class CustomAnnouncementRepositoryImpl implements CustomAnnouncementRepository{

	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<Announcement> getAllAnnouncements(Integer issuerId, Date creationDate, Integer externalId, Integer page) {
		
		List<Announcement> announcements = new ArrayList<>();
		
		Query countQuery = entityManager.createNativeQuery(getAllAnnouncementsQuery(true,issuerId, creationDate, externalId));
		setParametesInQuery(countQuery, issuerId, creationDate, externalId);
		Integer count = ((BigInteger)countQuery.getSingleResult()).intValue();
		
		Query resultQuery = entityManager.createNativeQuery(getAllAnnouncementsQuery(false,issuerId, creationDate, externalId),Announcement.class);
		setParametesInQuery(resultQuery, issuerId, creationDate, externalId);
		
		if(page != null){
			announcements = resultQuery.setFirstResult(Constants.PAGE_SIZE * page).setMaxResults(Constants.PAGE_SIZE).getResultList();
			return new PageImpl<>(announcements, PageRequest.of(page, Constants.PAGE_SIZE), count);
		}		
		else{
			announcements = resultQuery.getResultList();
			return new PageImpl<>(announcements);
		}
			 
		
	}
	
	private String getAllAnnouncementsQuery(boolean countOnly, Integer issuerId, Date creationDate, Integer externalId){
		StringBuilder builder = new StringBuilder();
		boolean conditionApplied = false;
		if(countOnly){
			builder.append("SELECT COUNT(*) FROM announcement");
		}else{
			builder.append("SELECT * FROM announcement");
		}
		
		if(issuerId != null){
			builder.append(" WHERE admin_id = :issuerId");
			conditionApplied = true;
		}
		if(creationDate != null){
			
			if(!conditionApplied){
				builder.append(" WHERE");
			}else{
				builder.append(" AND ");
			}
			builder.append(" creation_date BETWEEN ")
				.append(":startDate")
				.append(" AND ")
				.append(":endDate");
		}
		if(externalId != null){
			if(!conditionApplied){
				builder.append(" WHERE ");
			}else{
				builder.append(" AND ");
			}
			builder.append(" external_id = :externalId");
			
		}
		
		return builder.toString();
		
	}
	
	private void setParametesInQuery(Query query, Integer issuerId, Date creationDate, Integer externalId){
		if(issuerId != null){
			query.setParameter("issuerId", issuerId);
		}
		if(creationDate != null){
			Date[] dateRange = createDateRange(creationDate);
			query.setParameter("startDate", dateRange[0]).setParameter("endDate", dateRange[1]);
		}
		if(externalId != null){
			query.setParameter("externalId", externalId);
		}
	}
	
	private Date[] createDateRange(Date creationDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(creationDate);
		calendar.set(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
		Date startDate = calendar.getTime();
		
		calendar.add(Calendar.DATE, 1);
		Date endDate = calendar.getTime();
		
		return new Date[]{startDate, endDate};
	}

	
}
