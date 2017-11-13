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

@RestController
@RequestMapping(value = "/player")
public class PlayerController {
    private static final Logger logger = LoggerFactory.getLogger(SponsorController.class);

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Player> create(@RequestParam Map<String,String> reqParam) {

        if (!isParamValid(reqParam)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Player player = playerService.create(reqParam);

        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Player> get(@PathVariable("id") Long id) {

        Player player = playerService.findOne(id);

        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Player> update(@PathVariable("id") Long id, @RequestParam Map<String,String> reqParam) {

        if (!reqParam.containsKey(KEY_EMAIL)) {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Player player = playerService.update(id, reqParam);

        if (player == null) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Player> delete(@PathVariable("id") Long id) {
        Player player = playerService.delete(id);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    private boolean isParamValid(Map<String,String> reqParam) {
        return reqParam.containsKey(KEY_FIRSTNAME) &&
                reqParam.containsKey(KEY_LASTNAME) &&
                reqParam.containsKey(KEY_EMAIL);
    }
}
