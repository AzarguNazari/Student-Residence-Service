package de.srs.bulletinboard.resource.assembler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import de.srs.bulletinboard.api.BulletinboardApiController;
import de.srs.bulletinboard.model.Announcement;
import de.srs.bulletinboard.resource.AnnouncementListResource;
import de.srs.bulletinboard.resource.AnnouncementResource;

@Component
public class AnnouncementListResourceAssembler {
	
	@Autowired
	AnnouncementResourceAssembler announcementResourceAssembler;
	
	public AnnouncementListResource build(Page<Announcement> page){
		
		List<AnnouncementResource> announcements = page.getContent()
                .stream()
                .map(announcement ->  announcementResourceAssembler.toResource(announcement))
                .collect(Collectors.toList());
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        AnnouncementListResource announcementListResource =  new AnnouncementListResource(announcements, page.getNumber(),
                 page.getTotalPages(), page.getTotalElements());
        
        if(page.hasNext()){
        	buildNextPageLink(announcementListResource, page, request);
        }
        if(page.hasPrevious()){
        	buildPrevPageLink(announcementListResource, page, request);
        }
        
        return announcementListResource;
	}
	
	private void buildNextPageLink(AnnouncementListResource announcementListResource, Page<Announcement> page, HttpServletRequest request){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		String issuerId = request.getParameter("issuer-id");
		String date = request.getParameter("creation-date");
		String externalId = request.getParameter("external-id");
		UriComponentsBuilder builder = null;
		try {
			builder = ControllerLinkBuilder.linkTo(
					ControllerLinkBuilder.methodOn(BulletinboardApiController.class)
					.v1BulletinboardGet(page.nextPageable().getPageNumber(),
							issuerId != null ? Integer.parseInt(issuerId) : null,
							date != null ? format.parse(date) : null,
							externalId != null ? Integer.parseInt(externalId) : null))
					.toUriComponentsBuilder();
		} catch (NumberFormatException | ParseException e) {
			
		}
		
		setGatewayHostAddress(builder, request);
		
		announcementListResource.add(new Link(builder.toUriString()).withRel("Next"));
	}
	
	private void buildPrevPageLink(AnnouncementListResource announcementListResource, Page<Announcement> page, HttpServletRequest request){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String issuerId = request.getParameter("issuer-id");
		String date = request.getParameter("creation-date");
		String externalId = request.getParameter("external-id");
		UriComponentsBuilder builder = null;
		try {
			builder = ControllerLinkBuilder.linkTo(
					ControllerLinkBuilder.methodOn(BulletinboardApiController.class)
					.v1BulletinboardGet(page.previousPageable().getPageNumber(),
							issuerId != null ? Integer.parseInt(issuerId) : null,
							date != null ? format.parse(date) : null,
							externalId != null ? Integer.parseInt(externalId) : null))
					.toUriComponentsBuilder();
		} catch (NumberFormatException | ParseException e) {
			
		}
		
		setGatewayHostAddress(builder, request);
		
		announcementListResource.add(new Link(builder.toUriString()).withRel("Prev"));
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
