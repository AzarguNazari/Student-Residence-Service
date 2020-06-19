package srs.AuthServer.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Role
 */

@Entity
@Table(name = "role")
@Setter
@Getter
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id = null;

    @Column(name = "name")
    private String name = null;

    @OneToMany(mappedBy = "role", cascade = {CascadeType.ALL})
    private List<User> users;

    public Role id(Integer id) {
        this.id = id;
        return this;
    }

    public Role name(String name) {
        this.name = name;
        return this;
    }
}