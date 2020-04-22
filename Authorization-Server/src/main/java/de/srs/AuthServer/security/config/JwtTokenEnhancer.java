package de.srs.AuthServer.security.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import de.srs.AuthServer.model.AuthUser;

public class JwtTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken token, OAuth2Authentication auth) {
		final Map<String, Object> userClaims = new HashMap<>();
		userClaims.put("id", ((AuthUser)auth.getPrincipal()).getId());
		userClaims.put("first_name", ((AuthUser)auth.getPrincipal()).getFirstName());
		userClaims.put("last_name", ((AuthUser)auth.getPrincipal()).getLastName());
		userClaims.put("student_id", ((AuthUser)auth.getPrincipal()).getStudent()!=null ? ((AuthUser)auth.getPrincipal()).getStudent().getId() : null);
		
		((DefaultOAuth2AccessToken) token).setAdditionalInformation(userClaims);
	    return token;
	}

}
