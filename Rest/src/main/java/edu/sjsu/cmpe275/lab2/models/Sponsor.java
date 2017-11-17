package edu.sjsu.cmpe275.lab2.models;

import javax.persistence.*;
import java.io.Serializable;

import static edu.sjsu.cmpe275.lab2.GlobalVar.*;

/**
 * This class is a sponsor entity to stored in database.
 *
 * @author Yuntian Shen
 * @version 1.0
 */
@Entity
@Table(name = "SPONSOR")
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = KEY_ID)
    private long id;

    @Column(name = KEY_NAME)
    private String name;

    @Column(name = KEY_DESCRIPTION)
    private String description;

    @Embedded
    private Address address = new Address();

    public Sponsor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
