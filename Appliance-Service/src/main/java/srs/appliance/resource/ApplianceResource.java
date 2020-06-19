package srs.appliance.resource;

import org.springframework.hateoas.EntityModel;

import srs.appliance.model.Appliance;


public class ApplianceResource extends EntityModel{
	
	private Appliance appliance;
	
	public ApplianceResource(Appliance appliance) {
		this.appliance = appliance;
	}

	public Appliance getAppliance() {
		return appliance;
	}

	public void setAppliance(Appliance appliance) {
		this.appliance = appliance;
	}

}
