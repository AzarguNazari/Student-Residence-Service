package srs.bulletinboard.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Reply
 */
@Validated
@Entity
@Table(name="reply")
@Data
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-13T15:21:57.525Z[GMT]")
public class Reply  implements Serializable  {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  @Id
  @GeneratedValue
  private Integer id = null;

  @JsonProperty("user")
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="user_id", insertable=true, updatable=true, nullable=false)
  private User user = null;

  @JsonProperty("announcement")
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="announcement_id", insertable=true, updatable=true, nullable=false)
  private Announcement announcement = null;

  @JsonProperty("creation_date")
  @Column(name="creation_date")
  private Date creationDate = null;

  @JsonProperty("message_text")
  @Column(name="message_text")
  private String messageText = null;

  public Reply id(Integer id) {
    this.id = id;
    return this;
  }

  public Reply user(User user) {
    this.user = user;
    return this;
  }

  public Reply announcement(Announcement announcement) {
    this.announcement = announcement;
    return this;
  }

  public Reply creationDate(Date creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  public Reply messageText(String messageText) {
    this.messageText = messageText;
    return this;
  }
}
