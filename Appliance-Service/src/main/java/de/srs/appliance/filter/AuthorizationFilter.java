package de.srs.appliance.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import de.srs.appliance.access.AccessControlConfig;
import de.srs.appliance.util.CommonUtil;


@Component
public class AuthorizationFilter implements Filter  {

	@Autowired
	AccessControlConfig accessControlConfig;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String method = request.getMethod();
		String endpoint = request.getServletPath();
		String jwtToken = request.getHeader("Authorization");
        
		String role = getRole(jwtToken);
        CommonUtil.getUserDetailsFromToken(jwtToken);
        Boolean hasAccess = accessControlConfig.checkAccess(role, endpoint, method);
        
        if(hasAccess)
        	chain.doFilter(request, response);
        else
        	response.setStatus(HttpServletResponse.SC_FORBIDDEN);
       
	}
	
	private String getRole(String jwtToken){
		if(jwtToken!= null){
			DecodedJWT jwt = JWT.decode(jwtToken.split("Bearer")[1].trim());
	        Claim claim = jwt.getClaim("authorities");
	        String[] authorities = claim.asArray(String.class);
	        return authorities[0];
		}
		return null;
		
	}
}
