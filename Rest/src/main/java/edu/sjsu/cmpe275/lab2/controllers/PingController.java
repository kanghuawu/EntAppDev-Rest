package edu.sjsu.cmpe275.lab2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is for testing purpose when deploying to cloud.
 *
 * @author Kang-Hua Wu
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/")
public class PingController {

    /**
     * This method is to make sure the rest is up and running and always return the
     * same response.
     *
     * @return Always returns CMPE275 lab2 goes live!
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("CMPE275 lab2 goes live!", HttpStatus.OK);
    }
}
