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

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Student
 */
@Validated
@Entity
@Table(name="student")
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

  /**
   * Get user
   * @return user
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
  
  public Student id(Integer id) {
	    this.id = id;
	    return this;
	  }

	  /**
	   * Get roomNumber
	   * @return roomNumber
	  **/
	  @ApiModelProperty(value = "")
	  
	    public Integer getId() {
	    return id;
	  }

	  public void setId(Integer id) {
	    this.id = id;
	  }


  public Student roomNumber(Integer roomNumber) {
    this.roomNumber = roomNumber;
    return this;
  }

  /**
   * Get roomNumber
   * @return roomNumber
  **/
  @ApiModelProperty(value = "")
  
    public Integer getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(Integer roomNumber) {
    this.roomNumber = roomNumber;
  }

  public Student semester(String semester) {
    this.semester = semester;
    return this;
  }

  /**
   * Get semester
   * @return semester
  **/
  @ApiModelProperty(value = "")
  
    public String getSemester() {
    return semester;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }

  public Student joinDate(Date joinDate) {
    this.joinDate = joinDate;
    return this;
  }

  /**
   * Get joinDate
   * @return joinDate
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Date getJoinDate() {
    return joinDate;
  }

  public void setJoinDate(Date joinDate) {
    this.joinDate = joinDate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return Objects.equals(this.user, student.user) &&
        Objects.equals(this.roomNumber, student.roomNumber) &&
        Objects.equals(this.semester, student.semester) &&
        Objects.equals(this.joinDate, student.joinDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, roomNumber, semester, joinDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Student {\n");
    
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    roomNumber: ").append(toIndentedString(roomNumber)).append("\n");
    sb.append("    semester: ").append(toIndentedString(semester)).append("\n");
    sb.append("    joinDate: ").append(toIndentedString(joinDate)).append("\n");
    sb.append("}");
    return sb.toString();
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
