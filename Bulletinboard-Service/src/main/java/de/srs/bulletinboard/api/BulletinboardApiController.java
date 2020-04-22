package de.srs.bulletinboard.api;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.srs.bulletinboard.model.Announcement;
import de.srs.bulletinboard.model.AnnouncementType;
import de.srs.bulletinboard.model.Reply;
import de.srs.bulletinboard.model.User;
import de.srs.bulletinboard.resource.AnnouncementListResource;
import de.srs.bulletinboard.resource.assembler.AnnouncementListResourceAssembler;
import de.srs.bulletinboard.service.BulletinboardService;
import de.srs.bulletinboard.util.CommonUtil;
import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-13T15:21:57.525Z[GMT]")
@Controller
public class BulletinboardApiController implements BulletinboardApi {

    private static final Logger log = LoggerFactory.getLogger(BulletinboardApiController.class);

    private final HttpServletRequest request;
    
    @Autowired
    private BulletinboardService bulletinboardService;
    
    @Autowired
    private AnnouncementListResourceAssembler announcementListResourceAssembler;

    @Autowired
    public BulletinboardApiController(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity<?> v1BulletinboardGet( @ApiParam(value = "Page number", allowableValues = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "Issuer") @Valid @RequestParam(value = "issuer-id", required = false) Integer issuerId,@ApiParam(value = "Date of creation") @Valid @RequestParam(value = "creation-date", required = false)@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS") Date creationDate,@ApiParam(value = "external id of announcement") @Valid @RequestParam(value = "external-id", required = false) Integer externalId) {
        String accept = request.getHeader("Accept");
        log.info("[START]: Get announcements by filter");
        if (accept != null && accept.contains("application/json")) {
        	try{
        		log.debug("[DEBUG]: Filters :[IssuerId : "+issuerId+" creationDate : "+creationDate+"  externalId : "+externalId+"] ");

            	Page<Announcement> announcements = bulletinboardService.getAllAnnouncements(issuerId, creationDate, externalId, pageNumber);
            	AnnouncementListResource announcementListResource = announcementListResourceAssembler.build(announcements);
            	return new ResponseEntity<>(announcementListResource,HttpStatus.OK);
        	}catch(Exception e){
        		log.error("[ERROR]: Error in processing resource", e);
                return new ResponseEntity<>(new ApiException(500, "Unsupported format"),HttpStatus.INTERNAL_SERVER_ERROR);
        	}
        	
        } else{
        	log.warn("[WARN]: Unsupported format");
        	return new ResponseEntity<>(new ApiException(400, "Unsupported format"),HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> v1BulletinboardIdGet(@ApiParam(value = "External id of announcement",required=true) @PathVariable("announcement-id") Integer id) {
        log.info("[START]: Get announcement by id");
    	String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	if(id != null && id != 0){
            		Announcement announcement = bulletinboardService.getAnnouncement(id);
            		return new ResponseEntity<Announcement>(announcement,HttpStatus.OK);
            	} else{
            		log.warn("[WARN]: Empty Parameters.");
            		return new ResponseEntity<ApiException>(new ApiException(400, "Empty Parameters."),HttpStatus.BAD_REQUEST);
            	}
            } catch (Exception e) {
                log.error("[ERROR]: Error in processing resource", e);
                return new ResponseEntity<Announcement>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
        	log.warn("[WARN]: Unsupported format");
        	return new ResponseEntity<>(new ApiException(400, "Unsupported format"),HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> v1BulletinboardIdReplyGet(@ApiParam(value = "External id of announcement",required=true) @PathVariable("announcement-id") Integer announcementId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	if(announcementId != null){
            		Announcement announcement = bulletinboardService.getAnnouncement(announcementId);
            		List<Reply> replies = bulletinboardService.getAllReplies(announcement.getId());
            		return new ResponseEntity<List<Reply>>(replies,HttpStatus.OK);
            	}else{
            		log.warn("[WARN]: Empty Parameters.");
            		return new ResponseEntity<ApiException>(new ApiException(400, "Empty Parameters."),HttpStatus.BAD_REQUEST);
            	}
            	
            } catch (Exception e) {
                log.error("[ERROR]: Error in processing resource", e);
                return new ResponseEntity<>(new ApiException(500, "Unsupported format"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
        	log.warn("[WARN]: Unsupported format");
        	return new ResponseEntity<>(new ApiException(400, "Unsupported format"),HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> v1BulletinboardIdReplyPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Reply body,@ApiParam(value = "External id of announcement",required=true) @PathVariable("announcement-id") Integer announcementId) {
        String accept = request.getHeader("Accept");
        
        	User loggedInUser = CommonUtil.getUserDetailsFromToken(request.getHeader("Authorization"));
            body.setUser(loggedInUser);
            if(validateReplyBody(body) && body.getAnnouncement().getExternalId().equals(announcementId)){
            	Announcement announcement = bulletinboardService.getAnnouncement(announcementId);
            	if(announcement != null){
            		body.setAnnouncement(announcement);
            		bulletinboardService.addReply(body);
            		return new ResponseEntity<Void>(HttpStatus.OK);
            	}else{
            		log.warn("[WARN]: Announcement not available");
            		return new ResponseEntity<ApiException>(new ApiException(400, " Announcement not available"),HttpStatus.BAD_REQUEST);
            	}
            } else{
            	log.warn("[WARN]: Invalid reply body");
        		return new ResponseEntity<ApiException>(new ApiException(400, "Invalid reply post"),HttpStatus.BAD_REQUEST);
            }
        
        
    }

    public ResponseEntity<?> v1BulletinboardPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Announcement body) {
        log.info("[START]: Add announcement");
    	try{
        	User loggedInUser = CommonUtil.getUserDetailsFromToken(request.getHeader("Authorization"));
            body.setUser(loggedInUser);
            if(validateAnnouncementBody(body)){
            	
            	bulletinboardService.addAnnouncement(body);
            	return new ResponseEntity<Void>(HttpStatus.OK);
            }else{
            	log.warn("[WARN]: Invalid announcement body");
        		return new ResponseEntity<ApiException>(new ApiException(400, "Invalid announcement body"),HttpStatus.BAD_REQUEST);
            }
            
        }catch(Exception e){
        	log.error("[ERROR]: Error in processing resource", e);
            return new ResponseEntity<>(new ApiException(500, "Unsupported format"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    public ResponseEntity<?> v1BulletinboardTypesGet() {
    	
    	log.info("[START]: Get announcement types");
    	String accept = request.getHeader("Accept");
    	if(accept != null && accept.contains("application/json")){
    		try{
    			List<AnnouncementType> announcementTypes = bulletinboardService.getAllAnnouncementTypes();
    	    	
    	    	return new ResponseEntity<List<AnnouncementType>>(announcementTypes, HttpStatus.OK);
    		}catch(Exception e){
    			log.error("[ERROR]: Error in processing resource", e);
                return new ResponseEntity<>(new ApiException(500, "Error in processing resource"),HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    		
    	} else{
    		log.error("[ERROR]: Unsupported format");
        	return new ResponseEntity<>(new ApiException(400, "Unsupported format"),HttpStatus.BAD_REQUEST);
    	}
    }

    
    private boolean validateAnnouncementBody(Announcement announcement){
    	if(announcement != null && 
    			announcement.getAnnouncementType() != null &&
    			announcement.getDescription() != null &&
    			announcement.getPriority() != null &&
    			announcement.getUser() != null){
    		return true;
    	}
    	return false;
    }
    
    private boolean validateReplyBody(Reply reply){
    	if(reply != null &&
    			reply.getAnnouncement() != null &&
    			reply.getMessageText() != null &&
    			reply.getUser() != null){
    		return true;
    	}
    	
    	return false;
    }

}
