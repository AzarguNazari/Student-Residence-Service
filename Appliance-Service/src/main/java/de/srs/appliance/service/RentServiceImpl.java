package de.srs.appliance.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.srs.appliance.model.Appliance;
import de.srs.appliance.model.Rent;
import de.srs.appliance.model.Rent.StatusEnum;
import de.srs.appliance.model.Student;
import de.srs.appliance.repository.ApplianceRepository;
import de.srs.appliance.repository.RentRepository;
import de.srs.appliance.repository.StudentRepository;

@Service
public class RentServiceImpl implements RentService{
	
	@Autowired
	 RentRepository rentRepository;
	
	@Autowired
	 ApplianceRepository applianceRepository;
	
	@Autowired
	StudentRepository studentRepository;

	public Rent getRentById(Integer serialNumber){
		return rentRepository.findRentBySerialNumber(serialNumber);
	}
	
	public List<Rent> getAllRentsByIdAndStudentId(Integer applianceId, Integer rentId, Integer studentId){
		return rentRepository.getAllRentsForAppliance(applianceId, rentId, studentId);
	}
	
	@Transactional
	public void addRent(Rent rent, Appliance appliance){
		 
		rent.setCreationDate(new Date());
		rent.setAppliance(appliance);
		
		Long rentDurationInMillis = Math.abs(rent.getSelectedEndDate().getTime() - rent.getCreationDate().getTime());
		Long rentDuration = TimeUnit.DAYS.convert(rentDurationInMillis, TimeUnit.MILLISECONDS);
		Double rentAmount = (double) (rentDuration * appliance.getPricePerDay());
		rent.setRentAmount(rentAmount);
		if(rent.getStudent() != null){
			Student student = studentRepository.findById(rent.getStudent().getId()).get();
			rent.setStudent(student);
		}
		
		if(appliance.getMaxTime()>=rentDuration){
			rentRepository.save(rent);
			
			Integer updatedAvailableAppliances = appliance.getAvailableAppliances() - rent.getNumberOfAppliances();
			appliance.setAvailableAppliances(updatedAvailableAppliances);
			applianceRepository.save(appliance);
		}
		
	}
	
	public void updateRent(Rent rent, Appliance appliance){
		
		Long rentDurationInMillis = null;
		
		if(rent.getStudent() != null){
			Student student = studentRepository.findById(rent.getStudent().getId()).get();
			rent.setStudent(student);
		}
		
		rent.setAppliance(appliance);
		
		if(rent.getStatus().equals(StatusEnum.TERMINATED)){
			rent.setActualEndDate(new Date());
			rentDurationInMillis = Math.abs(rent.getActualEndDate().getTime() - rent.getCreationDate().getTime());
			 
		} else{
			rentDurationInMillis = Math.abs(rent.getSelectedEndDate().getTime() - rent.getCreationDate().getTime());
		}
		
		Long rentDuration = TimeUnit.DAYS.convert(rentDurationInMillis, TimeUnit.MILLISECONDS);
		Double rentAmount = (double) (rentDuration * appliance.getPricePerDay());
		rent.setRentAmount(rentAmount);
		
		rentRepository.save(rent);
		
		Integer updatedAvailableAppliances = appliance.getAvailableAppliances() + rent.getNumberOfAppliances();
		appliance.setAvailableAppliances(updatedAvailableAppliances);
		applianceRepository.save(appliance);
	}

	@Override
	public List<Rent> getAllRentsByStudentIdAndStatus(Integer studentId, StatusEnum status) {
		return rentRepository.getAllRentsByStatus(studentId, status);
	}
	
	
}
