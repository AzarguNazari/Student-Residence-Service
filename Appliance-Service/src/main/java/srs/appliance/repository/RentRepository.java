package srs.appliance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import srs.appliance.model.Rent;
import srs.appliance.model.Rent.StatusEnum;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer>,CustomRentRepository {

	public List<Rent> findRentByApplianceId(Integer applianceId);
	
	public Rent findRentBySerialNumber(Integer serialNumber);
	
	public List<Rent> findRentByStudentIdAndStatus(Integer studentId, StatusEnum status);
	
//	public List<Rent> findRentByApplianceIdAndIdAndStudentId(Integer applianceId, Integer rentId, Integer studentId);

}
