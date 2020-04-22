package de.srs.appliance.resource.assembler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import de.srs.appliance.api.HomeApplianceApiController;
import de.srs.appliance.model.Rent;
import de.srs.appliance.resource.RentResource;

@Component
public class RentResourceAssembler {

	public RentResource toResource(Rent rent){
		
		RentResource rentResource = new RentResource(rent);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		buildUpdateLink(rentResource, request);
		return rentResource;
	}
	
	private void buildUpdateLink(RentResource rentResource, HttpServletRequest request){
		UriComponentsBuilder builder = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(HomeApplianceApiController.class).v1AppliancesIdRentIdPut(null, rentResource.getRent().getAppliance().getSerialNumber(), rentResource.getRent().getSerialNumber()))
				.toUriComponentsBuilder();
		setGatewayHostAddress(builder, request);
		rentResource.add(new Link(builder.toUriString()).withRel("Update"));
	}
	
	private void setGatewayHostAddress(UriComponentsBuilder builder, HttpServletRequest request){
    	
		if(request.getHeader("X-Forwarded-Host") != null){
			String[] xForwardedHostHeader = request.getHeader("X-Forwarded-Host").split(":");
			String gatewayHost = xForwardedHostHeader[0];
			Integer gatewayPort = Integer.parseInt(xForwardedHostHeader[1]);
        
			builder.host(gatewayHost);
			builder.port(gatewayPort);
		}
    }
}
