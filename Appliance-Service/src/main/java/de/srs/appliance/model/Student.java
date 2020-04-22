package de.srs.appliance.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Student
 */
@Validated
@Entity
@Table(name="student")
@Data
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-13T15:21:57.525Z[GMT]")
public class Student  implements Serializable  {
  private static final long serialVersionUID = 1L;

  @JsonProperty("User")
  
  @OneToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="user_id", insertable=true, updatable=false, nullable=false, unique=true)
  private User user = null;
  
  @Id
  @GeneratedValue
  @JsonProperty("id")
  @Column(name="id")
  private Integer id = null;

  @JsonProperty("room_number")
  @Column(name="room_number")
  private Integer roomNumber = null;

  @JsonProperty("semester")
  @Column(name="semester")
  private String semester = null;

  @JsonProperty("join_date")
  @Column(name="join_date")
  private Date joinDate = null;
  
  @OneToMany(mappedBy="student", cascade=CascadeType.ALL)
  private List<Rent> rents;

  public Student user(User user) {
    this.user = user;
    return this;
  }

  public Student id(Integer id) {
	    this.id = id;
	    return this;
	  }

  public Student roomNumber(Integer roomNumber) {
    this.roomNumber = roomNumber;
    return this;
  }

  public Student semester(String semester) {
    this.semester = semester;
    return this;
  }

  public Student joinDate(Date joinDate) {
    this.joinDate = joinDate;
    return this;
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
