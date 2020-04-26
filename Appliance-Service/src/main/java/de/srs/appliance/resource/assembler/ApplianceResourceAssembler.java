package de.srs.appliance.resource.assembler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import de.srs.appliance.api.HomeApplianceApiController;
import de.srs.appliance.model.Appliance;
import de.srs.appliance.resource.ApplianceResource;
import de.srs.appliance.util.CommonUtil;
import de.srs.appliance.util.Constants;

@Component
public class ApplianceResourceAssembler {

	public ApplianceResource toResource(Appliance appliance){
		
		
		ApplianceResource applianceResource = new  ApplianceResource(appliance);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		String role = CommonUtil.getRoleFromToken(request.getHeader("Authorization"));
		
		if(role.equals(Constants.ADMIN)){
			buildAddLink(applianceResource, appliance, request); //ADMIN
			buildDeleteLink(applianceResource, appliance, request); //ADMIN
			buildUpdateLink(applianceResource, appliance, request); // ADMIN
			buildGetRentsByApplianceIdLink(applianceResource, appliance, request); // ADMIN
		}
		
		buildSelfLink(applianceResource, appliance, request); //ALL
		buildGetRentsLink(applianceResource, appliance, request); // ALL
		
		
		return applianceResource;
	}
	
	private void buildSelfLink(ApplianceResource applianceResource, Appliance appliance, HttpServletRequest request){
		UriComponentsBuilder builder = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(HomeApplianceApiController.class).v1AppliancesIdGet(appliance.getSerialNumber())).toUriComponentsBuilder();
		setGatewayHostAddress(builder, request);
		applianceResource.add(new Link(builder.toUriString()).withSelfRel());
	}
	
	private void buildAddLink(ApplianceResource applianceResource, Appliance appliance, HttpServletRequest request){
		UriComponentsBuilder builder = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(HomeApplianceApiController.class).v1AppliancesPost(null)).toUriComponentsBuilder();
		setGatewayHostAddress(builder, request);
		applianceResource.add(new Link(builder.toUriString()).withRel("Add"));
	}
	
	private void buildDeleteLink(ApplianceResource applianceResource, Appliance appliance, HttpServletRequest request){
		UriComponentsBuilder builder = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(HomeApplianceApiController.class).v1AppliancesIdDelete(appliance.getSerialNumber())).toUriComponentsBuilder();
		setGatewayHostAddress(builder, request);
		applianceResource.add(new Link(builder.toUriString()).withRel("Delete"));
	}
	
	private void buildUpdateLink(ApplianceResource applianceResource, Appliance appliance, HttpServletRequest request){
		UriComponentsBuilder builder = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(HomeApplianceApiController.class).v1AppliancesIdPut(null, appliance.getSerialNumber())).toUriComponentsBuilder();
		setGatewayHostAddress(builder, request);
		applianceResource.add(new Link(builder.toUriString()).withRel("Update"));
	}
	
	private void buildGetRentsByApplianceIdLink(ApplianceResource applianceResource, Appliance appliance, HttpServletRequest request){
		UriComponentsBuilder builder = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(HomeApplianceApiController.class).v1AppliancesIdRentGet(appliance.getSerialNumber(), null, null)).toUriComponentsBuilder();
		setGatewayHostAddress(builder, request);
		applianceResource.add(new Link(builder.toUriString()).withRel("RentByApplianceId"));
	}
	
	private void buildGetRentsLink(ApplianceResource applianceResource, Appliance appliance, HttpServletRequest request){
		UriComponentsBuilder builder = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(HomeApplianceApiController.class).v1AppliancesRentGet(null, null)).toUriComponentsBuilder();
		setGatewayHostAddress(builder, request);
		applianceResource.add(new Link(builder.toUriString()).withRel("Rents"));
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
