package edu.sjsu.cmpe275.lab2.controllers;


import edu.sjsu.cmpe275.lab2.models.Player;
import edu.sjsu.cmpe275.lab2.services.OpponentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/opponents")
public class OpponentController {

    private static final Logger logger = LoggerFactory.getLogger(OpponentController.class);

    @Autowired
    private OpponentService opponentService;

    @RequestMapping(value = "/{id1}/{id2}", method = RequestMethod.PUT)
    public ResponseEntity<Void> setUpMatch(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {
        Player player = opponentService.setupMatch(id1, id2);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id1}/{id2}", method = RequestMethod.DELETE)
    public ResponseEntity<List<Player>> delete(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {
        Player player = opponentService.deleteMatch(id1, id2);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}