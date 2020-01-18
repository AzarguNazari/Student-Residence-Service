package de.srs.bulletinboard.resource.assembler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import de.srs.bulletinboard.api.BulletinboardApiController;
import de.srs.bulletinboard.model.Announcement;
import de.srs.bulletinboard.resource.AnnouncementResource;

@Component
public class AnnouncementResourceAssembler {
	
	
	public AnnouncementResource toResource(Announcement announcement){
		
		
		AnnouncementResource announcementResource = new  AnnouncementResource(announcement);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		buildSelfLink(announcementResource, announcement, request);
		buildRepliesLink(announcementResource, announcement, request);
		
		return announcementResource;
	}
	
	private void buildSelfLink(AnnouncementResource announcementResource, Announcement announcement, HttpServletRequest request){
		UriComponentsBuilder builder = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(BulletinboardApiController.class).v1BulletinboardIdGet(announcement.getExternalId())).toUriComponentsBuilder();
		setGatewayHostAddress(builder, request);
		announcementResource.add(new Link(builder.toUriString()).withSelfRel());
	}
	
	private void buildRepliesLink(AnnouncementResource announcementResource, Announcement announcement, HttpServletRequest request){
		UriComponentsBuilder builder = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(BulletinboardApiController.class).v1BulletinboardIdReplyGet(announcement.getExternalId())).toUriComponentsBuilder();
		setGatewayHostAddress(builder, request);
		announcementResource.add(new Link(builder.toUriString()).withRel("Replies"));
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
