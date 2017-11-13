package edu.sjsu.cmpe275.lab2.models;

import javax.persistence.*;
import java.util.List;

import static edu.sjsu.cmpe275.lab2.GlobalVar.*;

@Entity
@Table(name = "PLAYER")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = KEY_ID)
    private long id;

    @Column(name = KEY_FIRSTNAME)
    private String firstname;

    @Column(name = KEY_LASTNAME)
    private String lastname;

    @Column(name = KEY_EMAIL)
    private String email;

    @Column(name = KEY_DESCRIPTION)
    private String description;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPONSOR_ID")
    private Sponsor sponsor;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "OPPONENT",
            joinColumns = { @JoinColumn(name = "PLAYER_ID", referencedColumnName = "ID") },
            inverseJoinColumns = { @JoinColumn(name = "OPPONENT_ID", referencedColumnName = "ID", unique = true) }
    )
    private List<Player> opponents;

    public Player() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public List<Player> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<Player> opponents) {
        this.opponents = opponents;
    }
}
