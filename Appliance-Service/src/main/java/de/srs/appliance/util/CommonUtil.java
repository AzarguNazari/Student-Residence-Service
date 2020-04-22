package de.srs.appliance.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import de.srs.appliance.model.User;

public class CommonUtil {

	@SuppressWarnings("rawtypes")
	public static User getUserDetailsFromToken(String jwtToken){
		DecodedJWT jwt = JWT.decode(jwtToken.split("Bearer")[1].trim());
		Map<String, Claim> claims = jwt.getClaims();
		User user = new User();
		PropertyDescriptor pd = null;
        if(claims != null){
        	try {
    			Iterator mapInterator = claims.entrySet().iterator();
    			while(mapInterator.hasNext()){
    				Map.Entry claim = (Map.Entry) mapInterator.next();
    				if(Constants.claimsToUserObjectMap.containsKey(claim.getKey())){
    					String userPropertyName = Constants.claimsToUserObjectMap.get(claim.getKey());
        				
        				pd = new PropertyDescriptor(userPropertyName, User.class);
        				
        				if(pd.getPropertyType().equals(String.class)){
        					String userPropertyValue = ((Claim)claim.getValue()).asString();
        					pd.getWriteMethod().invoke(user, userPropertyValue);
        				} else if(pd.getPropertyType().equals(Integer.class)){
        					Integer userPropertyValue = ((Claim)claim.getValue()).asInt();
        					pd.getWriteMethod().invoke(user, userPropertyValue);
        				}
        				
    				}
    				
    			}
    		} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
		 
        return  user;
	}
	
	public static String getRoleFromToken(String jwtToken){
		if(jwtToken!= null){
			DecodedJWT jwt = JWT.decode(jwtToken.split("Bearer")[1].trim());
	        Claim claim = jwt.getClaim("authorities");
	        String[] authorities = claim.asArray(String.class);
	        return authorities[0];
		}
		return null;
		
	}
}
