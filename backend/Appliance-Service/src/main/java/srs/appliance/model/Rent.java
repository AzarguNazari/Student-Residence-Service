package srs.appliance.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Rent
 */
@Validated
@Entity
@Table(name = "rent")
@Data
public class Rent implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id = null;

    @JsonProperty("serial_number")
    @Column(name = "serial_number", insertable = false, nullable = false)
    private Integer serialNumber = null;

    @JsonProperty("student")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", insertable = true, updatable = false, nullable = false)
    private Student student = null;

    @JsonProperty("appliance")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appliance_id", insertable = true, updatable = false, nullable = false)
    private Appliance appliance = null;

    @JsonProperty("creation_date")
    @Column(name = "creation_date")
    private Date creationDate = null;

    @JsonProperty("selected_end_date")
    @Column(name = "selected_end_date")
    private Date selectedEndDate = null;

    @JsonProperty("actual_end_date")
    @Column(name = "actual_end_date")
    private Date actualEndDate = null;

    @JsonProperty("rent_amount")
    @Column(name = "rent_amount")
    private Double rentAmount = null;

    @JsonProperty("number_of_appliances")
    @Column(name = "number_of_appliances")
    private Integer numberOfAppliances = null;

    @JsonProperty("status")
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status = null;

    /**
     * Gets or Sets status
     */
    public enum StatusEnum {
        RENTED("RENTED"),

        TERMINATED("TERMINATED");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static StatusEnum fromValue(String text) {
            for (StatusEnum b : StatusEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    public Rent id(Integer id) {
        this.id = id;
        return this;
    }

    public Rent serialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public Rent student(Student student) {
        this.student = student;
        return this;
    }

    public Rent appliance(Appliance appliance) {
        this.appliance = appliance;
        return this;
    }


    public Rent creationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Rent selectedEndDate(Date selectedEndDate) {
        this.selectedEndDate = selectedEndDate;
        return this;
    }

    public Rent actualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
        return this;
    }

    public Rent rentAmount(Double rentAmount) {
        this.rentAmount = rentAmount;
        return this;
    }

    public Rent status(StatusEnum status) {
        this.status = status;
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
