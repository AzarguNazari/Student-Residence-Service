package de.srs.AuthServer.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonProperty;

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
  
  public Student getStudent() {
	return student;
}

public void setStudent(Student student) {
	this.student = student;
}

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

  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  
    public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public User lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  
    public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


}
