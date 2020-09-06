package srs.AuthServer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Student
 */
@Validated
@Entity
@Table(name = "student")
@Data
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("User")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", updatable = false, nullable = false, unique = true)
    private User user = null;

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("room_number")
    @Column(name = "room_number")
    private Integer roomNumber = null;

    @JsonProperty("semester")
    @Column(name = "semester")
    private String semester = null;

    @JsonProperty("join_date")
    @Column(name = "join_date")
    private Date joinDate = null;
}
