package com.nixsolutions.bean;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressID;
    @NotNull
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "flat_number")
    private String flatNumber;
    @NotNull
    @Column(name = "city_name")
    private String cityName;
    @NotNull
    @Column(name = "street_name")
    private String streetName;

    public Address() {
    }

    public Address(Long addressID, String houseNumber, String flatNumber, String cityName, String streetName) {
        this.addressID = addressID;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.cityName = cityName;
        this.streetName = streetName;
    }

    public Long getAddressID() {
        return addressID;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressID=" + addressID +
                ", houseNumber='" + houseNumber + '\'' +
                ", flatNumber='" + flatNumber + '\'' +
                ", cityName='" + cityName + '\'' +
                ", streetName='" + streetName + '\'' +
                '}';
    }
}
