package srs.appliance.repository;

import java.util.List;

import srs.appliance.model.Rent;
import srs.appliance.model.Rent.StatusEnum;

public interface CustomRentRepository {

	public List<Rent> getAllRentsForAppliance(Integer applianceId, Integer rentId, Integer studentId);
	
	public List<Rent> getAllRentsByStatus(Integer studentId, StatusEnum status);	
}
