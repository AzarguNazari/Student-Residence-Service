package srs.appliance.model;

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
 * Appliance
 */
@Validated
@Entity
@Table(name = "appliance")
@Data
public class Appliance implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id = null;

    @JsonProperty("serial_number")
    @Column(name = "serial_number", unique = true, nullable = true, insertable = false)
    private Integer serialNumber = null;

    @JsonProperty("created_on")
    @Column(name = "created_on")
    private Date createdOn = null;

    @JsonProperty("model_name")
    @Column(name = "model_name")
    private String modelName = null;

    @JsonProperty("appliance_type")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appliance_type", insertable = true, updatable = true, nullable = true)
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
    @Column(name = "state")
    @Enumerated(EnumType.ORDINAL)
    private StateEnum state = null;

    @JsonProperty("price_per_day")
    @Column(name = "price_per_day")
    private Float pricePerDay = null;

    @JsonProperty("max_time")
    @Column(name = "max_time")
    private Integer maxTime = null;

    @JsonProperty("available_appliances")
    @Column(name = "available_appliances")
    private Integer availableAppliances = null;

    @OneToMany(mappedBy = "appliance", cascade = CascadeType.ALL)
    private List<Rent> rents;

    @JsonProperty("deleted_on")
    @Column(name = "deleted_on")
    private Date deletedOn = null;

    public Appliance id(Integer id) {
        this.id = id;
        return this;
    }

    public Appliance serialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public Appliance modelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public Appliance applianceType(ApplianceType applianceType) {
        this.applianceType = applianceType;
        return this;
    }

    public Appliance state(StateEnum state) {
        this.state = state;
        return this;
    }

    public Appliance pricePerDay(Float pricePerDay) {
        this.pricePerDay = pricePerDay;
        return this;
    }

    public Appliance maxTime(Integer maxTime) {
        this.maxTime = maxTime;
        return this;
    }

    public Appliance availableAppliances(Integer availableAppliances) {
        this.availableAppliances = availableAppliances;
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
