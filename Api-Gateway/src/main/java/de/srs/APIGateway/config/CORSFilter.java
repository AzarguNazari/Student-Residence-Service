package de.srs.APIGateway.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		 HttpServletResponse res = (HttpServletResponse) response;
	        HttpServletRequest req = (HttpServletRequest) request;
	       
	        res.addHeader("Access-Control-Allow-Origin", "*");
	        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
	        res.addHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Origin, Accept-Encoding, Content-Type, Authorization, x-requested-with");
	        if(req.getMethod().equalsIgnoreCase("OPTIONS")){
	        	res.setStatus(HttpServletResponse.SC_OK);
	        }else{
	        	chain.doFilter(request, response);
	        }
	        	
		
	}
	
	

}
