package edu.sjsu.cmpe275.lab2.controllers;

import edu.sjsu.cmpe275.lab2.models.Player;
import edu.sjsu.cmpe275.lab2.models.Sponsor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/sponsor")
public class SponsorController {

    private static final Logger logger = LoggerFactory.getLogger(SponsorController.class);


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Sponsor> create(@RequestParam Map<String,String> reqParam) {


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Sponsor> get(@PathVariable("id") Long id) {


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Sponsor> update(@PathVariable("id") Long id, @RequestParam Map<String,String> reqParam) {


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Sponsor> delete(@PathVariable("id") Long id) {


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
