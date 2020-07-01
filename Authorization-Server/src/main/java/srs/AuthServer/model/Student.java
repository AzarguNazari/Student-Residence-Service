package srs.AuthServer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Student
 */
@Validated
@Entity
@Table(name = "student")
@Data
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-13T15:21:57.525Z[GMT]")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("User")

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = true, updatable = false, nullable = false, unique = true)
    private User user = null;

    @Id
    @GeneratedValue
    @JsonProperty("id")
    @Column(name = "id")
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
