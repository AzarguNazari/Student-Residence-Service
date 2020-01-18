package de.srs.appliance.resource;

import org.springframework.hateoas.ResourceSupport;

import de.srs.appliance.model.Appliance;


public class ApplianceResource extends ResourceSupport{
	
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
