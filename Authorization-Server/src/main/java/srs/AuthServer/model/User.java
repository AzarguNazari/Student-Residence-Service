package srs.AuthServer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User
 */
@Entity
@Table(name = "user_details")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("first_name")
    @Column(name = "first_name")
    private String firstName = null;

    @JsonProperty("last_name")
    @Column(name = "last_name")
    private String lastName = null;

    @JsonProperty("user_name")
    @Column(name = "username")
    private String username = null;

    @Column(name = "password")
    private String password = null;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private Student student = null;

    @JsonProperty("Role")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false, nullable = true)
    private Role role = null;
}
