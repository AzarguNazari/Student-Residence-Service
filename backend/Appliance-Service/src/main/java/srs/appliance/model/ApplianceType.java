package srs.appliance.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ApplianceType
 */
@Validated
@Entity
@Table(name = "appliance_type")
@Data
public class ApplianceType implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue

    private Integer id = null;

    @JsonProperty("name")
    @Column(name = "name")
    private String name = null;

    @OneToMany(mappedBy = "applianceType", cascade = CascadeType.ALL)
    private List<Appliance> appliances;

    public ApplianceType id(Integer id) {
        this.id = id;
        return this;
    }

    public ApplianceType name(String name) {
        this.name = name;
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
