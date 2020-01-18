package de.srs.appliance.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * Rent
 */
@Validated
@Entity
@Table(name="rent")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-13T15:21:57.525Z[GMT]")
public class Rent  implements Serializable  {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  @Id
  @GeneratedValue
  @JsonIgnore
  private Integer id = null;

  @JsonProperty("serial_number")
  @Column(name="serial_number", insertable=false, nullable=false)
  private Integer serialNumber = null;

  @JsonProperty("student")
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="student_id", insertable=true, updatable=false , nullable=false)
  private Student student = null;

  @JsonProperty("appliance")
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="appliance_id", insertable=true, updatable=false , nullable=false)
  private Appliance appliance = null;

  @JsonProperty("creation_date")
  @Column(name="creation_date")
  private Date creationDate = null;

  @JsonProperty("selected_end_date")
  @Column(name="selected_end_date")
  private Date selectedEndDate = null;

  @JsonProperty("actual_end_date")
  @Column(name="actual_end_date")
  private Date actualEndDate = null;

  @JsonProperty("rent_amount")
  @Column(name="rent_amount")
  private Double rentAmount = null;
  
  @JsonProperty("number_of_appliances")
  @Column(name="number_of_appliances")
  private Integer numberOfAppliances = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    RENTED("RENTED"),
    
    TERMINATED("TERMINATED");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("status")
  @Column(name="status")
  @Enumerated(EnumType.ORDINAL)
  private StatusEnum status = null;

  public Rent id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Rent serialNumber(Integer serialNumber) {
    this.serialNumber = serialNumber;
    return this;
  }

  /**
   * Get serialNumber
   * @return serialNumber
  **/
  @ApiModelProperty(value = "")
  
    public Integer getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(Integer serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Rent student(Student student) {
    this.student = student;
    return this;
  }

  /**
   * Get student
   * @return student
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Rent appliance(Appliance appliance) {
    this.appliance = appliance;
    return this;
  }

  /**
   * Get appliance
   * @return appliance
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Appliance getAppliance() {
    return appliance;
  }

  public void setAppliance(Appliance appliance) {
    this.appliance = appliance;
  }

  public Rent creationDate(Date creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Get creationDate
   * @return creationDate
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Rent selectedEndDate(Date selectedEndDate) {
    this.selectedEndDate = selectedEndDate;
    return this;
  }

  /**
   * Get selectedEndDate
   * @return selectedEndDate
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Date getSelectedEndDate() {
    return selectedEndDate;
  }

  public void setSelectedEndDate(Date selectedEndDate) {
    this.selectedEndDate = selectedEndDate;
  }

  public Rent actualEndDate(Date actualEndDate) {
    this.actualEndDate = actualEndDate;
    return this;
  }

  /**
   * Get actualEndDate
   * @return actualEndDate
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Date getActualEndDate() {
    return actualEndDate;
  }

  public void setActualEndDate(Date actualEndDate) {
    this.actualEndDate = actualEndDate;
  }

  public Rent rentAmount(Double rentAmount) {
    this.rentAmount = rentAmount;
    return this;
  }

  /**
   * Get rentAmount
   * @return rentAmount
  **/
  @ApiModelProperty(value = "")
  
    public Double getRentAmount() {
    return rentAmount;
  }

  public void setRentAmount(Double rentAmount) {
    this.rentAmount = rentAmount;
  }

  public Rent status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")
  
    public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  public Integer getNumberOfAppliances() {
	return numberOfAppliances;
}

public void setNumberOfAppliances(Integer numberOfAppliances) {
	this.numberOfAppliances = numberOfAppliances;
}

@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rent rent = (Rent) o;
    return Objects.equals(this.id, rent.id) &&
        Objects.equals(this.serialNumber, rent.serialNumber) &&
        Objects.equals(this.student, rent.student) &&
        Objects.equals(this.appliance, rent.appliance) &&
        Objects.equals(this.creationDate, rent.creationDate) &&
        Objects.equals(this.selectedEndDate, rent.selectedEndDate) &&
        Objects.equals(this.actualEndDate, rent.actualEndDate) &&
        Objects.equals(this.rentAmount, rent.rentAmount) &&
        Objects.equals(this.status, rent.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, serialNumber, student, appliance, creationDate, selectedEndDate, actualEndDate, rentAmount, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Rent {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("\n");
    sb.append("    student: ").append(toIndentedString(student)).append("\n");
    sb.append("    appliance: ").append(toIndentedString(appliance)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    selectedEndDate: ").append(toIndentedString(selectedEndDate)).append("\n");
    sb.append("    actualEndDate: ").append(toIndentedString(actualEndDate)).append("\n");
    sb.append("    rentAmount: ").append(toIndentedString(rentAmount)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
