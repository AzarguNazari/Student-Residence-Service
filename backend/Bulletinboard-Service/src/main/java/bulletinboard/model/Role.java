package bulletinboard.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Role
 */
@Validated
@Entity
@Table(name="role")
@Data

public class Role  implements Serializable  {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  @Id
  @GeneratedValue
  @JsonIgnore
  private Integer id = null;

  @JsonProperty("name")
  @Column(name="name")
  private String name = null;
  
  @OneToMany(mappedBy="role", cascade={CascadeType.ALL})
  private List<User> users;

  public Role id(Integer id) {
    this.id = id;
    return this;
  }

  public Role name(String name) {
    this.name = name;
    return this;
  }

}
