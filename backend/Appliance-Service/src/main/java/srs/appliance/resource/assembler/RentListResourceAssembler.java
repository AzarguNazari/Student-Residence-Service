package srs.appliance.resource.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import srs.appliance.model.Rent;
import srs.appliance.resource.RentListResource;
import srs.appliance.resource.RentResource;

@Component
public class RentListResourceAssembler {

	
	@Autowired
	RentResourceAssembler rentResourceAssembler;
	
	public RentListResource build(List<Rent> response){
		
		List<RentResource> rents = response
                .stream()
                .map(rent ->  rentResourceAssembler.toResource(rent))
                .collect(Collectors.toList());
		
        RentListResource rentListResource = new RentListResource(rents);
        
        return rentListResource;
	}
}
