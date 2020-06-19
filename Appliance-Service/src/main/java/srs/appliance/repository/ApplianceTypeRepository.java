package srs.appliance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import srs.appliance.model.ApplianceType;

public interface ApplianceTypeRepository extends JpaRepository<ApplianceType, Integer> {
	
}
