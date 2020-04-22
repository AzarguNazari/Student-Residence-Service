package de.srs.AuthServer.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonProperty;

@Setter @Getter
public class AuthUser extends User {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@JsonProperty("id")
	private Integer id = null;

  @JsonProperty("first_name")
  @Column(name="first_name")
  private String firstName = null;

  @JsonProperty("last_name")
  @Column(name="last_name")
  private String lastName = null;

private Student student = null;

 
  public AuthUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,
			Integer id, String firstName, String lastName, Student student){
	  
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.student = student;
	}
  
  public User id(Integer id) {
    this.id = id;
    return this;
  }

  public User firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public User lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }
}
