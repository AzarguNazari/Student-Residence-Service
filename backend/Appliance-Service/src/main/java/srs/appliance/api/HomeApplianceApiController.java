package srs.appliance.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import srs.appliance.model.Appliance;
import srs.appliance.model.Appliance.StateEnum;
import srs.appliance.model.ApplianceType;
import srs.appliance.model.Rent;
import srs.appliance.model.Rent.StatusEnum;
import srs.appliance.resource.ApplianceListResource;
import srs.appliance.resource.ApplianceResource;
import srs.appliance.resource.RentListResource;
import srs.appliance.resource.assembler.ApplianceListResourceAssembler;
import srs.appliance.resource.assembler.ApplianceResourceAssembler;
import srs.appliance.resource.assembler.RentListResourceAssembler;
import srs.appliance.service.ApplianceService;
import srs.appliance.service.RentService;
import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-13T15:21:57.525Z[GMT]")
@Controller
public class HomeApplianceApiController implements HomeApplianceApi {

    private static final Logger log = LoggerFactory.getLogger(HomeApplianceApiController.class);

    private final HttpServletRequest request;
    
    @Autowired
    private ApplianceService applianceService;
    
    @Autowired
    private RentService rentService;
    
    @Autowired
    private ApplianceResourceAssembler applianceResourceAssembler;
    
    @Autowired
    private ApplianceListResourceAssembler applianceListResourceAssembler;
    
    @Autowired
    private RentListResourceAssembler rentListResourceAssembler;


    @Autowired
    public HomeApplianceApiController(HttpServletRequest request) {
        this.request = request;
    }

    @Override
	@SuppressWarnings("rawtypes")
	public ResponseEntity<?> v1AppliancesGet(@Min(0) @Max(10) @ApiParam(value = "Number of appliances returned", allowableValues = "") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber,@ApiParam(value = "Model Name of the HA") @Valid @RequestParam(value = "model-name", required = false) String modelName,@ApiParam(value = "Type of the HA") @Valid @RequestParam(value = "type", required = false) Integer type,@ApiParam(value = "State of the HA") @Valid @RequestParam(value = "state", required = false) String state) {

        String accept = request.getHeader("Accept");
        log.info("[START] : Get all appliances by filter ");
        if (accept != null && accept.contains("application/json")) {
            try {
            	log.debug("[DEBUG]: Filters :[modelName : "+modelName+" applianceTypeId : "+type+"  status : "+state+"] ");
            	Page<Appliance> appliances = applianceService.getAllAppliances(modelName, type, state != null ? StateEnum.fromValue(state).ordinal() : null, pageNumber);

            	ApplianceListResource applianceListResource = applianceListResourceAssembler.build(appliances);
                return new ResponseEntity<>(applianceListResource,HttpStatus.OK);
            } catch (Exception e) {
                log.error("[ERROR]: Error in processing resource", e);
                return new ResponseEntity<>(new ApiException(500, "Unsupported format"),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else{
        	log.error("[ERROR]: Unsupported format");
        	return new ResponseEntity<>(new ApiException(400, "Unsupported format"),HttpStatus.BAD_REQUEST);
        }
    }

	@Override
	
    public ResponseEntity<?> v1AppliancesIdDelete(@ApiParam(value = "id of the house appliance",required=true) @PathVariable("appliance-id") Integer applianceId) {
        
		log.info("[START] : Delete an appliance ");
        if(applianceId != 0 && applianceId != null){
        	try{
        		log.debug("[DEBUG]: Appliance serial number :"+applianceId);
        		Appliance appliance = applianceService.getApplianceById(applianceId);
            	if(appliance != null){
            		applianceService.deleteAppliance(applianceId);
					return new ResponseEntity<Void>(HttpStatus.OK);
            	} else{
            		log.warn("[WARN]: The appliance to be deleted does not exist");
            		return new ResponseEntity<ApiException>(new ApiException(400, "The appliance to be deleted does not exist"),HttpStatus.BAD_REQUEST);
            	}
        	}catch(Exception e){
        		log.error("[ERROR]: Error in processing resource", e);
                return new ResponseEntity<>(new ApiException(500, "Unsupported format"),HttpStatus.INTERNAL_SERVER_ERROR);
        	}
        	
        }
        else{
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
    }

    @Override
    public ResponseEntity<?> v1AppliancesIdGet(@ApiParam(value = "id of the house appliance",required=true) @PathVariable("appliance-id") Integer applianceId) {
        String accept = request.getHeader("Accept");
        log.info("[START]: Get appliance by id");
        if (accept != null && accept.contains("application/json")) {
            try{
            	if(applianceId==0){ 
            		log.warn("[WARN]: Missing Parameters");
    				return new ResponseEntity<ApiException>(new ApiException(400, "Missing paramters"),HttpStatus.BAD_REQUEST);
    			} else{
    				log.debug("[DEBUG]: Appliance serial number :"+applianceId);
    				Appliance appliance = applianceService.getApplianceById(applianceId);
    				ApplianceResource applianceResource = applianceResourceAssembler.toResource(appliance);
    				
    				if(appliance == null){
    					log.warn("Requested appliance not available");
    					return new ResponseEntity<ApiException>(new ApiException(404, "Requested appliance not available"),HttpStatus.NOT_FOUND);
    				} else {
    					return new ResponseEntity<ApplianceResource>(applianceResource,HttpStatus.OK);
    				}
    			}
            }catch(Exception e){
            	log.error("[ERROR]: Error in processing resource", e);
                return new ResponseEntity<>(new ApiException(500, "Error in processing resource"),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        	
        } else{
        	log.warn("Unsupported format");
        	return new ResponseEntity<ApiException>(new ApiException(400, "Unsupported format"),HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<?> v1AppliancesIdPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Appliance body,@ApiParam(value = "id of the house appliance",required=true) @PathVariable("appliance-id") Integer applianceId) {
        
    	log.info("[START]: Update appliance");
        if(applianceId != 0 && applianceId != null && validateApplianceBody(body)){
        	try{
        		log.debug("[DEBUG]: Appliance serial number :"+applianceId);
            	Appliance appliance = applianceService.getApplianceById(applianceId);
            	
            	if(appliance != null){
            		body.setId(appliance.getId());            	
            		applianceService.updateAppliance(body);
					return new ResponseEntity<Void>(HttpStatus.OK);
            	} else{
            		log.warn("The appliance to be updated does not exist");
            		return new ResponseEntity<ApiException>(new ApiException(400, "The appliance to be updated does not exist"),HttpStatus.BAD_REQUEST);
            	} 
        	} catch(Exception e){
        		log.error("[ERROR]: Error in processing resource", e);
                return new ResponseEntity<>(new ApiException(500, "Error in processing resource"),HttpStatus.INTERNAL_SERVER_ERROR);
        	}
        } else{
        	log.warn("Unsupported request body.");
        	return new ResponseEntity<ApiException>(new ApiException(400, "Unsupported request body. Please check"),HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<?> v1AppliancesIdRentGet(@ApiParam(value = "external id of appliance",required=true) @PathVariable("appliance-id") Integer applianceId, @ApiParam(value = "External Id of Rent") @Valid @RequestParam(value = "rent-id", required = false) Integer rentId, @ApiParam(value = "Student Id") @Valid @RequestParam(value = "student-id", required = false) Integer studentId){
    	
    	String accept = request.getHeader("Accept");
    	log.info("[START]: Get rents by appliance id");
    	if(accept != null && accept.contains("application/json")){
    		if(applianceId != 0 && applianceId != null){
        		try{
        			log.debug("[DEBUG]: Appliance serial number :"+applianceId);
        			Appliance appliance = applianceService.getApplianceById(applianceId);
            		if(appliance != null){
            			
            			List<Rent> rents = rentService.getAllRentsByIdAndStudentId(appliance.getId(), rentId, studentId);
            			RentListResource rentListResource = rentListResourceAssembler.build(rents);
            			return new ResponseEntity<>(rentListResource,HttpStatus.OK);
            		} else{
            			log.error("Unsupported parameters.");
            			return new ResponseEntity<ApiException>(new ApiException(1, "Unsupported Parameter. Please check"),HttpStatus.BAD_REQUEST);
            		}
        		}catch(Exception e){
        			log.error("[ERROR]: Error in processing resource", e);
                    return new ResponseEntity<>(new ApiException(500, "Error in processing resource"),HttpStatus.INTERNAL_SERVER_ERROR);
        		}
        		
        	} else {
        		log.error("Null or Empty parameters");
    			return new ResponseEntity<ApiException>(new ApiException(400, "Null or Empty parameters. Please check"),HttpStatus.BAD_REQUEST);
        	}
    	}else{
    		log.error("[ERROR]: Unsupported format");
        	return new ResponseEntity<>(new ApiException(400, "Unsupported format"),HttpStatus.BAD_REQUEST);
    	}
    	
    }
    	
    @Override
    public ResponseEntity<?> v1AppliancesTypesGet() {
    	String accept = request.getHeader("Accept");
    	if(accept != null && accept.contains("application/json")){
    		try{
    			List<ApplianceType> applianceType = applianceService.getAllApplianceTypes();
    	    	
            	return new ResponseEntity<List<ApplianceType>>(applianceType, HttpStatus.OK);
    		}catch(Exception e){
    			log.error("[ERROR]: Error in processing resource", e);
                return new ResponseEntity<>(new ApiException(500, "Error in processing resource"),HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    		
    	} else{
    		log.error("[ERROR]: Unsupported format");
        	return new ResponseEntity<>(new ApiException(400, "Unsupported format"),HttpStatus.BAD_REQUEST);
    	}
    	
    }
    
    @Override
    public ResponseEntity<?> v1AppliancesIdRentIdPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Rent body,@ApiParam(value = "external id of the house appliance",required=true) @PathVariable("appliance-id") Integer applianceId,@ApiParam(value = "external id of rent",required=true) @PathVariable("rent-id") Integer rentId) {
        
    	log.info("[START]: Update rent");
    	if(body != null && applianceId != null && rentId != null){
    		try{
    			Appliance appliance = applianceService.getApplianceById(applianceId);
        		Rent rent = rentService.getRentById(rentId);
        		
        		if(appliance != null && rent != null && appliance.getSerialNumber().equals(body.getAppliance().getSerialNumber()) && validateRentBody(body)){
        			body.setId(rent.getId());

        			rentService.updateRent(body,rent, appliance);
					return new ResponseEntity<Void>(HttpStatus.OK);
        		} else{
        			log.warn("[WARN]: Invalid Parameters");
                    return new ResponseEntity<>(new ApiException(400, "Invalid Parameters"),HttpStatus.BAD_REQUEST);
        		}
    		} catch(Exception e){
    			log.error("[ERROR]: Error in processing resource", e);
                return new ResponseEntity<>(new ApiException(500, "Error in processing resource"),HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    					
    	}else{
    		log.warn("[WARN]: Invalid Parameters");
            return new ResponseEntity<>(new ApiException(400, "Invalid Parameters"),HttpStatus.BAD_REQUEST);
    	}
    }

    @Override
    public ResponseEntity<?> v1AppliancesIdRentPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Rent body,@ApiParam(value = "id of the house appliance",required=true) @PathVariable("appliance-id") Integer applianceId) {
        
    	log.info("[START]: Rent an apppliance");
    	if(body != null && applianceId != null){
    		try{
    			Appliance appliance = applianceService.getApplianceById(applianceId);
        		if(appliance != null  && appliance.getSerialNumber().equals(body.getAppliance().getSerialNumber())){
        			rentService.addRent(body, appliance);
					return new ResponseEntity<Void>(HttpStatus.OK);
        		} else{
        			log.warn("[WARN]: Invalid Parameters");
                    return new ResponseEntity<>(new ApiException(400, "Invalid Parameters"),HttpStatus.BAD_REQUEST);
        		}
    		}catch(Exception e){
    			log.error("[ERROR]: Error in processing resource", e);
                return new ResponseEntity<>(new ApiException(500, "Error in processing resource"),HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    					
    	} else{
    		log.warn("[WARN]: Invalid Parameters");
            return new ResponseEntity<>(new ApiException(400, "Invalid Parameters"),HttpStatus.BAD_REQUEST);
    	}
    }

    @Override
    public ResponseEntity<?> v1AppliancesPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Appliance body) {
    	
    	log.info("[START]: Add appliance");

		try{
			if(validateApplianceBody(body)){
				applianceService.addAppliance(body);
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else{
				log.warn("[WARN]: Unsupported request body");
				return new ResponseEntity<ApiException>(new ApiException(400, "Unsupported request body. Please check"),HttpStatus.BAD_REQUEST);
			}
		}
		catch(Exception ex){
			log.warn("[WARN]: Invalid Parameters");
			return new ResponseEntity<>(new ApiException(500, "Internal Error"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @Override
	public ResponseEntity<?> v1AppliancesRentGet(@ApiParam(value = "Id of the Student",required=false) @PathVariable("student-id") Integer studentId, @ApiParam(value = "status of the rent",required=false) @PathVariable("status") String status) {
    	String accept = request.getHeader("Accept");

    	try{
    		if(accept != null && accept.contains("application/json")){
        		List<Rent> rents =  rentService.getAllRentsByStudentIdAndStatus(studentId, StatusEnum.fromValue(status));
                RentListResource rentListResource = rentListResourceAssembler.build(rents);
        		return new ResponseEntity<>(rentListResource,HttpStatus.OK);
        	} else{
        		log.warn("Unsupported format");
            	return new ResponseEntity<ApiException>(new ApiException(400, "Unsupported format"),HttpStatus.BAD_REQUEST);
        	}
		}
    	catch(Exception ex){
			log.warn("[WARN]: Invalid Parameters");
			return new ResponseEntity<>(new ApiException(500, "Internal Error"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    private boolean validateApplianceBody(Appliance appliance){
    	if(appliance != null){
    		if(appliance.getApplianceType() != null && 
    				appliance.getAvailableAppliances() != null &&
    				appliance.getMaxTime() != null &&
    				appliance.getModelName() != null &&
    				appliance.getPricePerDay() != null &&
    				appliance.getState() != null) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private boolean validateRentBody(Rent rent){
    	if(rent != null){
    		if(rent.getAppliance() != null && 
    				rent.getNumberOfAppliances() != null &&
    				rent.getSelectedEndDate() != null &&
    				rent.getStatus() != null && 
    				rent.getStudent() != null){
    			return true;
    		}
    	}
    	return false;
    }

	
}
