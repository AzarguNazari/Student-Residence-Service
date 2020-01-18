package de.srs.appliance.resource;

import java.util.List;

import de.srs.appliance.util.resource.AbstractListResource;

public class ApplianceListResource extends AbstractListResource {

	List<ApplianceResource> appliances;
	
	public ApplianceListResource(List<ApplianceResource> appliances, Integer pageNumber, Integer totalPages, Long totalElements) {
		super(pageNumber, totalPages, totalElements);
		this.appliances = appliances;
	}
	
	public List<ApplianceResource> getAppliances() {
		return appliances;
	}

	public void setAppliances(List<ApplianceResource> appliances) {
		this.appliances = appliances;
	}


}
