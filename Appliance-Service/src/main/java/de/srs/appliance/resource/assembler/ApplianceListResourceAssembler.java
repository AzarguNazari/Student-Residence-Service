package de.srs.appliance.resource.assembler;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import de.srs.appliance.api.HomeApplianceApiController;
import de.srs.appliance.model.Appliance;
import de.srs.appliance.resource.ApplianceListResource;
import de.srs.appliance.resource.ApplianceResource;

@Component
public class ApplianceListResourceAssembler {

	
	@Autowired
	ApplianceResourceAssembler applianceResourceAssembler;
	
	public ApplianceListResource build(Page<Appliance> page){
		
		List<ApplianceResource> appliances = page.getContent()
                .stream()
                .map(appliance ->  applianceResourceAssembler.toResource(appliance))
                .collect(Collectors.toList());
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        ApplianceListResource applianceListResource =  new ApplianceListResource(appliances, page.getNumber(),
                 page.getTotalPages(), page.getTotalElements());
        
        if(page.hasNext()){
        	buildNextPageLink(applianceListResource, page, request);
        }
        if(page.hasPrevious()){
        	buildPrevPageLink(applianceListResource, page, request);
        }
        
        return applianceListResource;
	}
	
	private void buildNextPageLink(ApplianceListResource applianceListResource, Page<Appliance> page, HttpServletRequest request){
		
		String modelName = request.getParameter("model-name");
		String type = request.getParameter("type");
		String status = request.getParameter("status");
		UriComponentsBuilder builder = null;
		try {
			builder = ControllerLinkBuilder.linkTo(
					ControllerLinkBuilder.methodOn(HomeApplianceApiController.class)
					.v1AppliancesGet(page.nextPageable().getPageNumber(),
							modelName ,
							type != null ? Integer.parseInt(type) : null,
							status ))
					.toUriComponentsBuilder();
		} catch (NumberFormatException  e) {
			
		}
		
		setGatewayHostAddress(builder, request);
		
		applianceListResource.add(new Link(builder.toUriString()).withRel("Next"));
	}
	
	private void buildPrevPageLink(ApplianceListResource applianceListResource, Page<Appliance> page, HttpServletRequest request){

		String modelName = request.getParameter("model-name");
		String type = request.getParameter("type");
		String status = request.getParameter("status");
		
		UriComponentsBuilder builder = null;
		try {
			builder = ControllerLinkBuilder.linkTo(
					ControllerLinkBuilder.methodOn(HomeApplianceApiController.class)
					.v1AppliancesGet(page.previousPageable().getPageNumber(),
							modelName ,
							type != null ? Integer.parseInt(type) : null,
							status ))
					.toUriComponentsBuilder();
		} catch (NumberFormatException e) {
			
		}
		
		setGatewayHostAddress(builder, request);
		
		applianceListResource.add(new Link(builder.toUriString()).withRel("Prev"));
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
