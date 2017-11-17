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

/**
 * This class is for handling request to /sponsor
 *
 * @author Yuntian Shen
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/sponsor")
public class SponsorController {

    private static final Logger logger = LoggerFactory.getLogger(SponsorController.class);

    @Autowired
    private SponsorService sponsorService;

    /**
     * This method is for creating a new player. Only name is required in url parameter.
     *
     * @param reqParam The url parameters parsed into HashMap where keys are the parameters
     * @return Returns a newly created sponsor if successful. Returns status code 400 if request parameter does not
     * contain name.
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Sponsor> create(@RequestParam Map<String, String> reqParam) {
        if (!reqParam.containsKey(KEY_NAME)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Sponsor sponsor = sponsorService.create(reqParam);
        return new ResponseEntity<>(sponsor, HttpStatus.OK);
    }

    /**
     * This method is for retrieving a sponsor given an id.
     *
     * @param id The id to search a player.
     * @return Returns a sponsor if is being found. Otherwise, returns status code of 404.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Sponsor> get(@PathVariable("id") Long id) {
        Sponsor sponsor = sponsorService.findOne(id);
        if (sponsor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sponsor, HttpStatus.OK);
    }

    /**
     * This method is for updating a sponsor. Every parameters will be passed through url, and hence empty
     * body request. Parameter name, is required whereas others are are optional.
     *
     * @param id The id of the sponsor to be updated.
     * @param reqParam The url parameters to the parsed in to HashMap.
     * @return Returns the update sponsor if successful. Otherwise, returns status code 404 if not found; returns
     * 400 if name is not included.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Sponsor> update(@PathVariable("id") Long id, @RequestParam Map<String, String> reqParam) {
        if (!reqParam.containsKey(KEY_NAME)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Sponsor sponsor = sponsorService.update(id, reqParam);
        if (sponsor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sponsor, HttpStatus.OK);
    }

    /**
     * This method is for deleting an sponsor given an id.
     *
     * @param id The id of the sponsor to be deleted.
     * @return Returns the sponsor in json object if successful. Otherwise, returns status code 404 if not found; returns
     * status code 400 if the sponsor is still sponsoring a player.
     */
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
