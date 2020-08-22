package srs.appliance.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import srs.appliance.model.Appliance;
import srs.appliance.model.ApplianceType;
import srs.appliance.repository.ApplianceRepository;
import srs.appliance.repository.ApplianceTypeRepository;

@Service
public class ApplianceServiceImpl implements ApplianceService {

	@Autowired
	private ApplianceRepository applianceRepository;
	
	@Autowired
	ApplianceTypeRepository applianceTypeRepository;
	
	@Override
	public Page<Appliance> getAllAppliances(String modelName, Integer applianceTypeId, Integer state, Integer page) { 
		return applianceRepository.findAllActiveAppliances(modelName, applianceTypeId, state, page);
	}
	
	@Override
	public Appliance getApplianceById(int serialNumber) {
		return applianceRepository.findBySerialNumber(serialNumber).get();
		
	}	
	
	@Override
	@Transactional
	@Modifying
	public void deleteAppliance(int serialNumber) {
		Date deletedDate = new Date();
		applianceRepository.deleteAppliance(deletedDate, serialNumber);
	}
	
	@Override
	@Transactional
	@Modifying
	public Appliance updateAppliance(Appliance appliance){
		Appliance result = applianceRepository.save(appliance);
		return result;
	}
	
	@Override
	@Transactional
	@Modifying
	public void addAppliance(Appliance appliance){
		appliance.setCreatedOn(new Date());
		applianceRepository.save(appliance);
	}
	
	@Transactional
	
	public List<ApplianceType> getAllApplianceTypes() {
		return applianceTypeRepository.findAll();
	}

}
