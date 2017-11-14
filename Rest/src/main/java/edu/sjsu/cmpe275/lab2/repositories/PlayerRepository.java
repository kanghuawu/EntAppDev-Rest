package edu.sjsu.cmpe275.lab2.repositories;


import edu.sjsu.cmpe275.lab2.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    public Player findByEmail(String email);
}
