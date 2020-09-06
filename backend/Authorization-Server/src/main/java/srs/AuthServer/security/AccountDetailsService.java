package srs.AuthServer.security;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import srs.AuthServer.model.AuthUser;
import srs.AuthServer.model.User;
import srs.AuthServer.repository.UserDetailsRepository;

@Service
@Transactional
public class AccountDetailsService implements UserDetailsService{

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDetailsRepository.findByUsername(username)
										 .orElseThrow(() -> new UsernameNotFoundException("user "+username+" not found"));

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
		
		org.springframework.security.core.userdetails.User userDetails =  new AuthUser(user.getUsername(), user.getPassword(), true, true, true, true, authorities, user.getId(), user.getFirstName(), user.getLastName(), user.getStudent() != null ? user.getStudent(): null);
		return userDetails;
	}
	
}
