package srs.appliance.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srs.appliance.model.Appliance;
import srs.appliance.model.Rent;
import srs.appliance.model.Rent.StatusEnum;
import srs.appliance.model.Student;
import srs.appliance.repository.ApplianceRepository;
import srs.appliance.repository.RentRepository;
import srs.appliance.repository.StudentRepository;

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
		rent.setStatus(StatusEnum.RENTED);
		
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
	
	public void updateRent(Rent newRent, Rent previousRent, Appliance appliance){
		
		Long rentDurationInMillis = null;
		Integer updatedAvailableAppliances = null;
		
		if(newRent.getStudent() != null){
			Student student = studentRepository.findById(newRent.getStudent().getId()).get();
			newRent.setStudent(student);
		}
		
		newRent.setAppliance(appliance);
		
		if(newRent.getStatus().equals(StatusEnum.TERMINATED)){
			newRent.setActualEndDate(new Date());
			rentDurationInMillis = Math.abs(newRent.getActualEndDate().getTime() - newRent.getCreationDate().getTime());
			updatedAvailableAppliances = appliance.getAvailableAppliances() + newRent.getNumberOfAppliances();
		} else{
			rentDurationInMillis = Math.abs(newRent.getSelectedEndDate().getTime() - newRent.getCreationDate().getTime());
			updatedAvailableAppliances = appliance.getAvailableAppliances() + previousRent.getNumberOfAppliances() - newRent.getNumberOfAppliances();
		}
		
		Long rentDuration = TimeUnit.DAYS.convert(rentDurationInMillis, TimeUnit.MILLISECONDS);
		Double rentAmount = (double) (rentDuration * appliance.getPricePerDay());
		newRent.setRentAmount(rentAmount);
		
		rentRepository.save(newRent);
		
		appliance.setAvailableAppliances(updatedAvailableAppliances);
		applianceRepository.save(appliance);
	}

	@Override
	public List<Rent> getAllRentsByStudentIdAndStatus(Integer studentId, StatusEnum status) {
		return rentRepository.getAllRentsByStatus(studentId, status);
	}
	
	
}
