package srs.appliance.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import srs.appliance.model.Appliance;
import srs.appliance.model.ApplianceType;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Integer>, CustomApplianceRepository {
	
	@Query(value = "SELECT * FROM appliance WHERE serial_number = ?1 AND deleted_on IS NULL"
			,	nativeQuery = true)
	public Optional<Appliance> findBySerialNumber(Integer serialNumber);
	
	@Modifying
	@Query(value = "UPDATE appliance SET deleted_on = ? WHERE serial_number = ?"
			, nativeQuery = true)
	public int deleteAppliance(Date deletedOn, Integer serialNumber);
	
	@Query(value = "SELECT * FROM appliance_type", nativeQuery = true)
	public List<ApplianceType> findAllApplianceTypes();
	
}
