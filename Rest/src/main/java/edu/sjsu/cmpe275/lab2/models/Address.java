package edu.sjsu.cmpe275.lab2.models;

import javax.persistence.*;

import static edu.sjsu.cmpe275.lab2.GlobalVar.*;

@Embeddable
public class Address {

    @Column(name = KEY_STREET)
    private String street;

    @Column(name = KEY_CITY)
    private String city;

    @Column(name = KEY_STATE)
    private String state;

    @Column(name = KEY_ZIP)
    private String zip;

    public Address() {}

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
