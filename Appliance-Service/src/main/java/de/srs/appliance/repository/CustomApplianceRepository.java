package de.srs.appliance.repository;

import java.util.List;

import org.springframework.data.domain.Page;

import de.srs.appliance.model.Appliance;

public interface CustomApplianceRepository {

	public Page<Appliance> findAllActiveAppliances(String modelName, Integer applianceTypeId, String status, Integer page);
	
}
