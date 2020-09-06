package srs.AuthServer.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import srs.AuthServer.security.AccountDetailsService;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	
	@Autowired
	private AccountDetailsService accountDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenEnhancer jwtTokenEnhancer;
	
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
	    tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtTokenEnhancer, jwtAccessTokenConverter));
		endpoints.tokenStore(new JwtTokenStore(jwtAccessTokenConverter))
				 .authenticationManager(authenticationManager)
				 .userDetailsService(accountDetailsService)
				 .tokenEnhancer(tokenEnhancerChain);
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer client) throws Exception{
		client.inMemory()
			  .withClient("browser")
			  .secret(passwordEncoder.encode("browser"))
			  .authorizedGrantTypes("password", "refresh_token")
			  .accessTokenValiditySeconds(300)
			  .refreshTokenValiditySeconds(6000000)
			  .scopes("all");
	}
	
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(new JwtTokenStore(jwtAccessTokenConverter));
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }
	
}
