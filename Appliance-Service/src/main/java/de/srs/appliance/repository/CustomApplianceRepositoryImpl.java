package de.srs.appliance.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import de.srs.appliance.model.Appliance;
import de.srs.appliance.util.Constants;

public class CustomApplianceRepositoryImpl implements CustomApplianceRepository{

	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<Appliance> findAllActiveAppliances(String modelName, Integer applianceTypeId, Integer status, Integer page) {
		List<Appliance> appliances = new ArrayList<>();
		
		Query countQuery = entityManager.createNativeQuery(buildFindAllAppliancesQuery(true, modelName, applianceTypeId, status));
		setParametersInQuery(countQuery, modelName, applianceTypeId, status);
		Integer count = ((BigInteger)countQuery.getSingleResult()).intValue();
		
		Query resultQuery = entityManager.createNativeQuery(buildFindAllAppliancesQuery(false, modelName, applianceTypeId, status),Appliance.class);
		setParametersInQuery(resultQuery, modelName, applianceTypeId, status);
		if(page != null){
			appliances = resultQuery.setFirstResult(Constants.PAGE_SIZE * page).setMaxResults(Constants.PAGE_SIZE).getResultList();
			return  new PageImpl<>(appliances, PageRequest.of(page, Constants.PAGE_SIZE), count);
		} else {
			appliances = resultQuery.getResultList();
			return new PageImpl<>(appliances);
		}
		
	}
	
	private String buildFindAllAppliancesQuery(boolean countOnly, String modelName, Integer applianceTypeId, Integer status){
		
		StringBuilder query = new StringBuilder();
		
		if(countOnly){
			query.append("SELECT COUNT(*) FROM appliance WHERE deleted_on isnull");
		} else {
			query.append("SELECT * FROM appliance WHERE deleted_on isnull ");
		}
		
		if(modelName != null){
			query.append(" AND model_name = :modelName");
		}
		if(applianceTypeId != null){
			query.append(" AND appliance_type = :applianceTypeId");
		}
		
		if(status != null){
			query.append(" AND state = :state");
		}
		
		return query.toString();
	}
	
	private void setParametersInQuery(Query query, String modelName, Integer applianceTypeId, Integer status){
		
		if(modelName != null){
			query.setParameter("modelName", modelName);
		}
		
		if(applianceTypeId != null){
			query.setParameter("applianceTypeId", applianceTypeId);
		}
		
		if(status != null){
			query.setParameter("state", status);
		}
	}

	
}
