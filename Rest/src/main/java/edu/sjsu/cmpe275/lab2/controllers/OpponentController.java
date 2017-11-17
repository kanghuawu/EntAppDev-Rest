package edu.sjsu.cmpe275.lab2.controllers;


import edu.sjsu.cmpe275.lab2.models.Player;
import edu.sjsu.cmpe275.lab2.services.OpponentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is responsible for responding requests to /opponents
 *
 * @author Kang-Hua Wu
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/opponents")
public class OpponentController {

    private static final Logger logger = LoggerFactory.getLogger(OpponentController.class);

    @Autowired
    private OpponentService opponentService;

    /**
     * This method sets up opponent relationship between two players. The relationship
     * is bidirectional.
     *
     * @param id1 Tne of the player's id for creating opponent relationship
     * @param id2 The other player's id for creating opponent relationship
     * @return Returns the id1 player object in json who has added player id2 as opponent
     */
    @RequestMapping(value = "/{id1}/{id2}", method = RequestMethod.PUT)
    public ResponseEntity<Player> setUpMatch(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {
        if (id1.equals(id2)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Player player = opponentService.setupMatch(id1, id2);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    /**
     * This method removes the opponent relationship between two players.
     *
     * @param id1 One of the player's id for removing opponents relationship
     * @param id2 The other player's id for removing opponents relationship
     * @return Returns the id1 player object in json who has removed a opponent, player id2
     */
    @RequestMapping(value = "/{id1}/{id2}", method = RequestMethod.DELETE)
    public ResponseEntity<Player> delete(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {
        Player player = opponentService.deleteMatch(id1, id2);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
}
