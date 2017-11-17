package edu.sjsu.cmpe275.lab2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static edu.sjsu.cmpe275.lab2.GlobalVar.*;

/**
 * This class is a player entity to stored in database.
 *
 * @author Chenhua Zhu
 * @version
 */
@Entity
@Table(name = "PLAYER")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = KEY_ID)
    private long id;

    @Column(name = KEY_FIRSTNAME)
    private String firstname;

    @Column(name = KEY_LASTNAME)
    private String lastname;

    @Column(name = KEY_EMAIL, unique=true)
    private String email;

    @Column(name = KEY_DESCRIPTION)
    private String description;

    @Embedded
    private Address address = new Address();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SPONSOR_ID")
    private Sponsor sponsor;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(
            name = "OPPONENT",
            joinColumns = {@JoinColumn(name = "PLAYER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "OPPONENT_ID", referencedColumnName = "ID")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"PLAYER_ID", "OPPONENT_ID"})}
    )
    @JsonIgnoreProperties("opponents")
    private List<Player> opponents = new ArrayList<>();

    public Player() {
    }

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

    public boolean hasOpponent(Player player) {
        return opponents.contains(player);
    }

    public void addOpponent(Player player) {
        if (!hasOpponent(player)) {
            opponents.add(player);
        }
    }

    public void removeOpponent(Player player) {
        if (hasOpponent(player)) {
            opponents.remove(player);
        }
    }
}
