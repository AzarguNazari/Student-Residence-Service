package de.srs.appliance.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import de.srs.appliance.model.Appliance;
import de.srs.appliance.model.Rent;
import de.srs.appliance.model.Rent.StatusEnum;
import de.srs.appliance.model.Student;

@Repository
public class CustomRentRepositoryImpl implements CustomRentRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Rent> getAllRentsForAppliance(Integer applianceId, Integer rentId, Integer studentId) {
		
		Query query = entityManager.createNativeQuery(getAllRentsForApplianceQuery(applianceId, rentId, studentId),Rent.class);
		
		List<Rent> rents = query.getResultList();
		rents.forEach(rent->{
			rent.setAppliance(new Appliance().serialNumber(rent.getAppliance().getSerialNumber()));
		});
		return rents;
	}
	
	private String getAllRentsForApplianceQuery(Integer applianceId, Integer rentId, Integer studentId){
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM rent ")
				.append("WHERE appliance_id = ").append(applianceId);
		
		if(rentId != null && rentId != 0){
			builder.append(" AND serial_number = ").append(rentId);
		}
		
		if(studentId != null && studentId != 0){
			builder.append(" AND student_id = ").append(studentId);
		}
		
		return builder.toString();
		
	}
	
	public List<Rent> getAllRentsByStatus(Integer studentId, StatusEnum status) {
		Query query = entityManager.createNativeQuery(getAllRentsQuery(studentId, status),Rent.class);
		
		List<Rent> rents = query.getResultList();
		return rents;
	}
	
	private String getAllRentsQuery(Integer studentId, StatusEnum status) {
		StringBuilder builder = new StringBuilder();
		boolean conditionApplied = false;
		builder.append("SELECT * FROM rent ");
		
		if(status!= null){
			builder.append("WHERE status = ").append(status.ordinal());
			conditionApplied = true;
		}
		if(studentId != null){
			if(!conditionApplied) {
				builder.append(" WHERE ");
			} else {
				builder.append(" AND ");
			}
			builder.append(" student_id = ").append(studentId);
		}	
		String queryString = builder.toString();
				
		return queryString;
	}

	 
}
