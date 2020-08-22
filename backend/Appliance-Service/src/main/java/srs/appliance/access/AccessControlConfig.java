package srs.appliance.access;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriTemplate;

import srs.appliance.model.Pair;

@Configuration
public class AccessControlConfig extends Permissions {
	
	@SuppressWarnings("unchecked")
	public Boolean checkAccess(String userRole, String requestEndpoint, String requestMethod){
		
		if(userRole.equals(ADMIN))
			return true;
		else if (userRole.equals(STUDENT)){
			Pair[] studentPermissions = (Pair[])STUDENT_PERMISSIONS.getValue();
			
			for(Pair endpointPair : studentPermissions){
				UriTemplate template = (UriTemplate) endpointPair.getKey();
				
				if(template.matches(requestEndpoint)){	
					Pair[] methodPairs = (Pair[]) endpointPair.getValue();
					
					for(Pair methodPair : methodPairs){
						String method = (String) methodPair.getKey();
						if(method.equals(requestMethod)){
							return (Boolean) methodPair.getValue();
						}
					}
					
				}
			}
		} 
			
		return false;
	}
}
