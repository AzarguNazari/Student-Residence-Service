package bulletinboard.model;

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

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User
 */
@Validated
@Entity
@Table(name="user_details")
@Data

public class User  implements Serializable  {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  @Id
  @GeneratedValue
  @JsonIgnore
  private Integer id = null;

  @JsonProperty("first_name")
  @Column(name="first_name")
  private String firstName = null;

  @JsonProperty("last_name")
  @Column(name="last_name")
  private String lastName = null;

  @JsonProperty("username")
  @Column(name="username")
  private String username = null;
  
  @JsonIgnore
  @Column(name="password")
  private String password=null;

  @JsonProperty("Role")
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="role_id", insertable=true, updatable=false, nullable=false)
  private Role role = null;

  @OneToOne(mappedBy="user", fetch=FetchType.LAZY)
  private Student student;
  

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

  public User username(String username) {
    this.username = username;
    return this;
  }

  public User role(Role role) {
    this.role = role;
    return this;
  }
}
