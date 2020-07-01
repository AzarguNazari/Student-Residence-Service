package srs.AuthServer.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Role
 */

@Entity
@Table(name = "role")
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id = null;

    @Column(name = "name")
    private String name = null;

    @OneToMany(mappedBy = "role", cascade = {CascadeType.ALL})
    private List<User> users;
}