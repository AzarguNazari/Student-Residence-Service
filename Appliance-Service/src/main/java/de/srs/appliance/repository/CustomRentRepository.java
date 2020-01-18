package de.srs.appliance.repository;

import java.util.List;

import de.srs.appliance.model.Rent;
import de.srs.appliance.model.Rent.StatusEnum;

public interface CustomRentRepository {

	public List<Rent> getAllRentsForAppliance(Integer applianceId, Integer rentId, Integer studentId);
	
	public List<Rent> getAllRentsByStatus(Integer studentId, StatusEnum status);	
}
