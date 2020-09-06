package srs.appliance.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import srs.appliance.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@Api(value = "User", description = "The User API")
public interface UserApi {

	@ApiOperation(value = "", nickname = "v1UserStudentIdGet", notes = "Gets an user by its student id", response = User.class, authorizations = {
	        @Authorization(value = "oAuth2Password", scopes = { 
	            })    }, tags={ "Bulletinboard", })
	    @ApiResponses(value = { 
	        @ApiResponse(code = 200, message = "Successful pull of user information", response = User.class),
	        @ApiResponse(code = 400, message = "Bad Request. Please check the parameters"),
	        @ApiResponse(code = 401, message = "Unauthorized. Please check your credentials"),
	        @ApiResponse(code = 403, message = "Forbidden. Sorry, you can not access this resource.") })
	    @RequestMapping(value = "/v1/user/{student-id}",
	        produces = { "application/json" }, 
	        method = RequestMethod.GET)
	    ResponseEntity<?> v1UserStudentIdGet(@ApiParam(value = "Id of student",required=true) @PathVariable("student-id") Integer studentId);
}
