package srs.appliance.repository;

import org.springframework.data.domain.Page;

import srs.appliance.model.Appliance;

public interface CustomApplianceRepository {

	public Page<Appliance> findAllActiveAppliances(String modelName, Integer applianceTypeId, Integer status, Integer page);
	
}
