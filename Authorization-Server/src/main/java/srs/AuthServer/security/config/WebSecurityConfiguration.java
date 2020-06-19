package srs.AuthServer.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import srs.AuthServer.security.AccountAuthenticationProvider;
import srs.AuthServer.security.AccountDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	

	@Autowired
	private AccountDetailsService accountDetailsService;
	
	@Autowired
	private AccountAuthenticationProvider accountAuthenticationProvider;
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(accountDetailsService);
		auth.authenticationProvider(accountAuthenticationProvider);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter(){
		final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		KeyStoreKeyFactory keyStoreKeyFactory = 
			      new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "mypass".toCharArray());
		jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
		return jwtAccessTokenConverter;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public TokenEnhancer jwtTokenEnhancer(){
		return new JwtTokenEnhancer();
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/oauth/**", "/actuator/**").permitAll()
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().csrf().disable();
    }
}
