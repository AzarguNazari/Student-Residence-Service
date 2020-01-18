package de.srs.appliance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.srs.appliance.model.ApplianceType;

public interface ApplianceTypeRepository extends JpaRepository<ApplianceType, Integer> {
	
}
