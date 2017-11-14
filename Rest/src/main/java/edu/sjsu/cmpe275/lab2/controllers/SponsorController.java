package edu.sjsu.cmpe275.lab2.controllers;

import edu.sjsu.cmpe275.lab2.models.Sponsor;
import edu.sjsu.cmpe275.lab2.services.SponsorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static edu.sjsu.cmpe275.lab2.GlobalVar.KEY_NAME;

@RestController
@RequestMapping(value = "/sponsor")
public class SponsorController {

    private static final Logger logger = LoggerFactory.getLogger(SponsorController.class);

    @Autowired
    private SponsorService sponsorService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Sponsor> create(@RequestParam Map<String, String> reqParam) {
        if (!reqParam.containsKey(KEY_NAME)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Sponsor sponsor = sponsorService.create(reqParam);
        return new ResponseEntity<>(sponsor, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Sponsor> get(@PathVariable("id") Long id) {
        Sponsor sponsor = sponsorService.findOne(id);
        if (sponsor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sponsor, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Sponsor> update(@PathVariable("id") Long id, @RequestParam Map<String, String> reqParam) {
        if (reqParam.containsKey(KEY_NAME)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Sponsor sponsor = sponsorService.update(id, reqParam);
        if (sponsor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sponsor, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Sponsor> delete(@PathVariable("id") Long id) {
        try {
            Sponsor sponsor = sponsorService.delete(id);
            if (sponsor == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
