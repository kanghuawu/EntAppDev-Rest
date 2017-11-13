package edu.sjsu.cmpe275.lab2.models;

import javax.persistence.*;

import static edu.sjsu.cmpe275.lab2.GlobalVar.*;

@Entity
@Table(name = "SPONSOR")
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = KEY_ID, updatable = false, nullable = false)
    private long id;

    @Column(name = KEY_NAME, updatable = false, nullable = false)
    private String name;

    @Column(name = KEY_DESCRIPTION)
    private String description;

    @Embedded
    private Address address;

    public Sponsor() {}

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
