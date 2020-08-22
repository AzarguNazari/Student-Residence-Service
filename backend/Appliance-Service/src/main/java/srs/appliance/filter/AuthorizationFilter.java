package srs.appliance.filter;

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

import srs.appliance.access.AccessControlConfig;
import srs.appliance.util.CommonUtil;


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

		if(jwtToken == null || jwtToken.length() == 0){
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		String role = CommonUtil.getRoleFromToken(jwtToken);
        CommonUtil.getUserDetailsFromToken(jwtToken);
        Boolean hasAccess = accessControlConfig.checkAccess(role, endpoint, method);
        
        if(hasAccess)
        	chain.doFilter(request, response);
        else
        	response.setStatus(HttpServletResponse.SC_FORBIDDEN);
       
	}
	
	
}
