package de.srs.bulletinboard.model;

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
 * Announcement
 */
@Validated
@Entity
@Table(name="Announcement")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-13T15:21:57.525Z[GMT]")
public class Announcement  implements Serializable  {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  @JsonIgnore
  private Integer id = null;

  @JsonProperty("external_id")
  @Column(name="external_id", insertable=false, updatable=false)
  private Integer externalId = null;

  @JsonProperty("user")
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="admin_id", insertable=true, updatable=false , nullable=true)
  private User user = null;

  @JsonProperty("announcement_type")
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="announcement_type_id", insertable=true, updatable=true , nullable=true)
  private AnnouncementType announcementType = null;

  @JsonProperty("description")
  @Column(name="description")
  private String description = null;

  /**
   * Gets or Sets status
   */
  public enum PriorityEnum {
    LOW("LOW"),
    
    MEDIUM("MEDIUM"),
    
    HIGH("HIGH");

    private String value;

    PriorityEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PriorityEnum fromValue(String text) {
      for (PriorityEnum b : PriorityEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("priority")
  @Column(name="priority")
  @Enumerated(EnumType.ORDINAL)
  private PriorityEnum priority = null;

  @JsonProperty("creation_date")
  @Column(name="creation_date")
  private Date creationDate = null;

  @JsonProperty("appliance_serial_number")
  private Integer applianceSerialNumber = null;
  
  @OneToMany(mappedBy="announcement", cascade=CascadeType.ALL)
  private List<Reply> replies;

  public Announcement id(Integer id) {
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

  public Announcement externalId(Integer externalId) {
    this.externalId = externalId;
    return this;
  }

  /**
   * Get externalId
   * @return externalId
  **/
  @ApiModelProperty(value = "")
  
    public Integer getExternalId() {
    return externalId;
  }

  public void setExternalId(Integer externalId) {
    this.externalId = externalId;
  }

  public Announcement user(User user) {
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

  public Announcement announcementType(AnnouncementType announcementType) {
    this.announcementType = announcementType;
    return this;
  }

  /**
   * Get announcementType
   * @return announcementType
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public AnnouncementType getAnnouncementType() {
    return announcementType;
  }

  public void setAnnouncementType(AnnouncementType announcementType) {
    this.announcementType = announcementType;
  }

  public Announcement description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Announcement priority(PriorityEnum priority) {
    this.priority = priority;
    return this;
  }

  /**
   * Get priority
   * @return priority
  **/
  @ApiModelProperty(value = "")
  
    public PriorityEnum getPriority() {
    return priority;
  }

  public void setPriority(PriorityEnum priority) {
    this.priority = priority;
  }

  public Announcement creationDate(Date creationDate) {
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

  public Announcement appliance(Integer applianceSerialNumber) {
    this.applianceSerialNumber = applianceSerialNumber;
    return this;
  }

  /**
   * Get appliance
   * @return appliance
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Integer getApplianceSerialNumber() {
    return applianceSerialNumber;
  }

  public void setAppliance(Integer applianceSerialNumber) {
    this.applianceSerialNumber = applianceSerialNumber;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Announcement announcement = (Announcement) o;
    return Objects.equals(this.id, announcement.id) &&
        Objects.equals(this.externalId, announcement.externalId) &&
        Objects.equals(this.user, announcement.user) &&
        Objects.equals(this.announcementType, announcement.announcementType) &&
        Objects.equals(this.description, announcement.description) &&
        Objects.equals(this.priority, announcement.priority) &&
        Objects.equals(this.creationDate, announcement.creationDate) &&
        Objects.equals(this.applianceSerialNumber, announcement.applianceSerialNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, externalId, user, announcementType, description, priority, creationDate, applianceSerialNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Announcement {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    externalId: ").append(toIndentedString(externalId)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    announcementType: ").append(toIndentedString(announcementType)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    appliance: ").append(toIndentedString(applianceSerialNumber)).append("\n");
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
