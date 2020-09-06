package bulletinboard.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Announcement
 */
@Validated
@Entity
@Data
@Table(name="Announcement")

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

  public Announcement externalId(Integer externalId) {
    this.externalId = externalId;
    return this;
  }

  public Announcement user(User user) {
    this.user = user;
    return this;
  }

  public Announcement announcementType(AnnouncementType announcementType) {
    this.announcementType = announcementType;
    return this;
  }

  public Announcement description(String description) {
    this.description = description;
    return this;
  }

  public Announcement priority(PriorityEnum priority) {
    this.priority = priority;
    return this;
  }

  public Announcement creationDate(Date creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  public Announcement appliance(Integer applianceSerialNumber) {
    this.applianceSerialNumber = applianceSerialNumber;
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
