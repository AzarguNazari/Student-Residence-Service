package de.srs.appliance.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * Appliance
 */
@Validated
@Entity
@Table(name="appliance")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-13T15:21:57.525Z[GMT]")
public class Appliance  implements Serializable  {
  private static final long serialVersionUID = 1L;

  
  @Id
  @GeneratedValue
  @JsonIgnore
  private Integer id = null;

  @JsonProperty("serial_number")
  @Column(name="serial_number", unique = true, nullable = true, insertable=false)
  private Integer serialNumber = null;
  
  @JsonProperty("created_on")
  @Column(name="created_on")
  private Date createdOn = null;

  @JsonProperty("model_name")
  @Column(name="model_name")
  private String modelName = null;

  @JsonProperty("appliance_type")
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="appliance_type", insertable=true, updatable=true , nullable=true)
  private ApplianceType applianceType = null;

  /**
   * Gets or Sets status
   */
  public enum StateEnum {
    AVAILABLE("AVAILABLE"),
    
    BROKEN("BROKEN");

    private String value;

    StateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StateEnum fromValue(String text) {
      for (StateEnum b : StateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("state")
  @Column(name="state")
  @Enumerated(EnumType.ORDINAL)
  private StateEnum state = null;

  @JsonProperty("price_per_day")
  @Column(name="price_per_day")
  private Float pricePerDay = null;

  @JsonProperty("max_time")
  @Column(name="max_time")
  private Integer maxTime = null;

  @JsonProperty("available_appliances")
  @Column(name="available_appliances")
  private Integer availableAppliances = null;
  
  @OneToMany(mappedBy="appliance", cascade=CascadeType.ALL)
  private List<Rent> rents;
  
  @JsonProperty("deleted_on")
  @Column(name = "deleted_on")
  private Date deletedOn = null;

  public Appliance id(Integer id) {
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

  public Appliance serialNumber(Integer serialNumber) {
    this.serialNumber = serialNumber;
    return this;
  }

  /**
   * Get serialNo
   * @return serialNo
  **/
  @ApiModelProperty(value = "")
  
    public Integer getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(Integer serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Appliance modelName(String modelName) {
    this.modelName = modelName;
    return this;
  }

  /**
   * Get modelName
   * @return modelName
  **/
  @ApiModelProperty(value = "")
  
    public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public Appliance applianceType(ApplianceType applianceType) {
    this.applianceType = applianceType;
    return this;
  }

  /**
   * Get applianceType
   * @return applianceType
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public ApplianceType getApplianceType() {
    return applianceType;
  }

  public void setApplianceType(ApplianceType applianceType) {
    this.applianceType = applianceType;
  }

  public Appliance state(StateEnum state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  **/
  @ApiModelProperty(value = "")
  
    public StateEnum getState() {
    return state;
  }

  public void setState(StateEnum state) {
    this.state = state;
  }

  public Appliance pricePerDay(Float pricePerDay) {
    this.pricePerDay = pricePerDay;
    return this;
  }

  /**
   * Get pricePerDay
   * @return pricePerDay
  **/
  @ApiModelProperty(value = "")
  
    public Float getPricePerDay() {
    return pricePerDay;
  }

  public void setPricePerDay(Float pricePerDay) {
    this.pricePerDay = pricePerDay;
  }

  public Appliance maxTime(Integer maxTime) {
    this.maxTime = maxTime;
    return this;
  }

  /**
   * Get maxTime
   * @return maxTime
  **/
  @ApiModelProperty(value = "")
  
    public Integer getMaxTime() {
    return maxTime;
  }

  public void setMaxTime(Integer maxTime) {
    this.maxTime = maxTime;
  }

  public Appliance availableAppliances(Integer availableAppliances) {
    this.availableAppliances = availableAppliances;
    return this;
  }

  /**
   * Get availableAppliances
   * @return availableAppliances
  **/
  @ApiModelProperty(value = "")
  
    public Integer getAvailableAppliances() {
    return availableAppliances;
  }

  public void setAvailableAppliances(Integer availableAppliances) {
    this.availableAppliances = availableAppliances;
  }


  public Date getCreatedOn() {
	return createdOn;
}

public void setCreatedOn(Date createdOn) {
	this.createdOn = createdOn;
}

@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Appliance appliance = (Appliance) o;
    return Objects.equals(this.id, appliance.id) &&
        Objects.equals(this.serialNumber, appliance.serialNumber) &&
        Objects.equals(this.modelName, appliance.modelName) &&
        Objects.equals(this.applianceType, appliance.applianceType) &&
        Objects.equals(this.state, appliance.state) &&
        Objects.equals(this.pricePerDay, appliance.pricePerDay) &&
        Objects.equals(this.maxTime, appliance.maxTime) &&
        Objects.equals(this.availableAppliances, appliance.availableAppliances);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, serialNumber, modelName, applianceType, state, pricePerDay, maxTime, availableAppliances);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Appliance {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("\n");
    sb.append("    modelName: ").append(toIndentedString(modelName)).append("\n");
    sb.append("    applianceType: ").append(toIndentedString(applianceType)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    pricePerDay: ").append(toIndentedString(pricePerDay)).append("\n");
    sb.append("    maxTime: ").append(toIndentedString(maxTime)).append("\n");
    sb.append("    availableAppliances: ").append(toIndentedString(availableAppliances)).append("\n");
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
