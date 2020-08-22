package srs.appliance.resource;

import java.util.List;

import org.springframework.hateoas.EntityModel;

public class RentListResource extends EntityModel {

	private List<RentResource> rents;
	
	public RentListResource(List<RentResource> rents) {
		this.rents = rents;
	}

	public List<RentResource> getRents() {
		return rents;
	}

	public void setRents(List<RentResource> rents) {
		this.rents = rents;
	}
	
}
