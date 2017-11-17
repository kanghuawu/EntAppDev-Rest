package edu.sjsu.cmpe275.lab2.controllers;


import edu.sjsu.cmpe275.lab2.models.Player;
import edu.sjsu.cmpe275.lab2.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static edu.sjsu.cmpe275.lab2.GlobalVar.*;

/**
 * This class is responsible for handling request to /player
 *
 * @author Chenhua Zhu
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/player")
public class PlayerController {
    private static final Logger logger = LoggerFactory.getLogger(SponsorController.class);

    @Autowired
    private PlayerService playerService;

    /**
     * This method is for creating a new player. Every parameters will be passed through
     * url, and hence empty body request. Parameters, firstname, lastname and email are required whereas
     * description, address and sponsor are optional.
     *
     * @param reqParam The parameters will be parsed to HashMap and handled later.
     * @return Returns a newly created player with its content if successfully created. Otherwise, returns
     * empty response with status code of 400.
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Player> create(@RequestParam Map<String, String> reqParam) {
        if (!reqParam.containsKey(KEY_FIRSTNAME) ||
                !reqParam.containsKey(KEY_LASTNAME) ||
                !reqParam.containsKey(KEY_EMAIL)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Player player = playerService.create(reqParam);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    /**
     * This method for searching a player given an id.
     *
     * @param id the id of the searching player
     * @return returns a json object if the player is found. Otherwise, returns empty body with status code of 404.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Player> get(@PathVariable("id") Long id) {
        Player player = playerService.findOne(id);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    /**
     * This method is for update an existing player's attributes with given a player id. Email is required in url parameter.
     *
     * @param id The id of a player
     * @param reqParam The parameter in the url parsed to HashMap where keys are the parameters
     * @return Teturns an updated player in json object if successful. Returns 400 if email is missing; returns 404 if
     * the player or sponsor in parameter is not found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Player> update(@PathVariable("id") Long id, @RequestParam Map<String, String> reqParam) {
        if (!reqParam.containsKey(KEY_EMAIL)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Player player = playerService.update(id, reqParam);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    /**
     * This method is for deleting an existing player.
     * @param id The id of a player to be deleted.
     * @return Return the player in json object of being deleted. Returns status code 404 if the player is not found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Player> delete(@PathVariable("id") Long id) {
        Player player = playerService.delete(id);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
}
