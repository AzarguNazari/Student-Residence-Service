package de.srs.bulletinboard.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.srs.bulletinboard.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-13T15:21:57.525Z[GMT]")
@Api(value = "User", description = "The User API")
public interface UserApi {

	@ApiOperation(value = "", nickname = "v1BulletinboardAdminsGet", notes = "Gets admins information", response = User.class, responseContainer="List", authorizations = {
	        @Authorization(value = "oAuth2Password", scopes = { 
	            })    }, tags={ "Bulletinboard", })
	    @ApiResponses(value = { 
	        @ApiResponse(code = 200, message = "Successful pull of admin information", response = User.class, responseContainer="List"),
	        @ApiResponse(code = 400, message = "Bad Request. Please check the parameters"),
	        @ApiResponse(code = 401, message = "Unauthorized. Please check your credentials"),
	        @ApiResponse(code = 403, message = "Forbidden. Sorry, you can not access this resource.") })
	    @RequestMapping(value = "/v1/bulletinboard/admins",
	        produces = { "application/json" }, 
	        method = RequestMethod.GET)
	    ResponseEntity<?> v1BulletinboardAdminsGet();
}
