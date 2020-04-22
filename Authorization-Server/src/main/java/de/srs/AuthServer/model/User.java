package de.srs.AuthServer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User
 */
@Entity
@Table(name="user_details")

public class User  implements Serializable  {
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

  @JsonProperty("user_name")
  @Column(name="username")
  private String username = null;
  
  @Column(name="password")
  private String password=null;
  
  @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
  private Student student = null ;
  
  @JsonProperty("Role")
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="role_id", insertable=false, updatable=false , nullable=true)
  private Role role = null;

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

  public User username(String username) {
    this.username = username;
    return this;
  }

  public void setPassword(String password){
	  this.password = password;
  }
  
  public String getPassword(){
	  return password;
  }
  
    public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public User role(Role role) {
    this.role = role;
    return this;
  }


  
    public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }


public Student getStudent() {
	return student;
}


public void setStudent(Student student) {
	this.student = student;
}


  
}
