package de.srs.appliance.api;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.srs.appliance.model.User;
import de.srs.appliance.service.UserService;
import io.swagger.annotations.ApiParam;

@Controller
public class UserApiController implements UserApi {
	
	 private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

	    private final ObjectMapper objectMapper;

	    private final HttpServletRequest request;
	    
	    @Autowired
	    UserService userService;

	    @Autowired
	    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
	        this.objectMapper = objectMapper;
	        this.request = request;
	    }
	
	@Override
	public ResponseEntity<?> v1UserStudentIdGet(@ApiParam(value = "Id of student",required=true) @PathVariable("student-id") Integer studentId){
		
		String accept = request.getHeader("Accept");
		if(accept.equals("application/json")){
			if(studentId != null && studentId != 0){
				User user = userService.getUserByStudentId(studentId);
				return new ResponseEntity<User>(user,HttpStatus.OK); 
			}
		}
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}
}
