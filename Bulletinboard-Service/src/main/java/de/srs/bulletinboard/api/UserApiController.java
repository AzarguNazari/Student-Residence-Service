package de.srs.bulletinboard.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.srs.bulletinboard.model.User;
import de.srs.bulletinboard.service.UserService;
import io.swagger.annotations.ApiParam;

@Controller
public class UserApiController implements UserApi {
	
	 private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

	    private final HttpServletRequest request;
	    
	    @Autowired
	    UserService userService;

	    @Autowired
	    public UserApiController(HttpServletRequest request) {
	  
	        this.request = request;
	    }
	
	@Override
	public ResponseEntity<?> v1BulletinboardAdminsGet(){
		
		String accept = request.getHeader("Accept");
		if(accept.equals("application/json")){
			
			List<User> admins = userService.getAdmins();
			return new ResponseEntity<>(admins,HttpStatus.OK); 
			
		}
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}
}
