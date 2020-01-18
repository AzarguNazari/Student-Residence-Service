package de.srs.appliance.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import de.srs.appliance.model.Appliance;
import de.srs.appliance.model.ApplianceType;


public interface ApplianceService {
	
	public Page<Appliance> getAllAppliances(String modelName, Integer applianceTypeId, String status, Integer page);

	public Appliance getApplianceById(int applianceId);
	
	public void deleteAppliance(int serialNumber);
	
	public Appliance updateAppliance(Appliance appliance);
	
	public void addAppliance(Appliance appliance);
	
	public List<ApplianceType> getAllApplianceTypes(); 
}
