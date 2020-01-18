package de.srs.appliance.service;

import java.util.List;

import de.srs.appliance.model.Appliance;
import de.srs.appliance.model.Rent;
import de.srs.appliance.model.Rent.StatusEnum;

public interface RentService {

	public Rent getRentById(Integer serialNumber);
	
	public List<Rent> getAllRentsByIdAndStudentId(Integer applianceId, Integer rentId, Integer studentId);
	
	public void addRent(Rent rent, Appliance appliance);
	
	public void updateRent(Rent rent, Appliance appliance);
	
	public List<Rent> getAllRentsByStudentIdAndStatus(Integer studentId, StatusEnum status);
}
