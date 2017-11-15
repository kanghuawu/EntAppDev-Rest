package edu.sjsu.cmpe275.lab2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class PingController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("CMPE275 lab2 goes live!", HttpStatus.OK);
    }
}
