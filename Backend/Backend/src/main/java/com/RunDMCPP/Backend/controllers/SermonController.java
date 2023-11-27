// SermonController.java
package com.RunDMCPP.Backend.controllers;

import com.RunDMCPP.Backend.utils.BackendErrorException;
import com.RunDMCPP.Backend.utils.BackendErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.RunDMCPP.Backend.models.Sermon;
import com.RunDMCPP.Backend.services.SermonService;

import java.util.List;
import java.util.Optional;
@CrossOrigin(maxAge = 3600)

// Controller class listening for web requests to the /api/sermons endpoints and sends back responses
@RestController
@RequestMapping("/api/sermons")
public class SermonController {

    // @Autowired lets Spring automatically create and give us an SermonService object
    @Autowired
    private SermonService sermonService;

    // Method gets all sermons at '/api/sermons/get'
    @GetMapping
    @RequestMapping("/get")
    public ResponseEntity<Iterable<Sermon>> getAllSermons() {
        return new ResponseEntity<>(sermonService.findAll(), HttpStatus.OK);
    }

    // Method gets a specific sermon by id
    @GetMapping
    @RequestMapping("/get/{id}")
    public ResponseEntity getSermonByID(@PathVariable String id) {
        // Try to get the sermon by id & return HTTP status OK; else return error
        try {
            return new ResponseEntity<>(sermonService.findById(id), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    // Method adds a new sermon at
    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity addSermon(@RequestBody final Sermon s) {
        // Try to return new sermon w/ CREATED status; else return error
        try {
            return new ResponseEntity<>(sermonService.createSermon(s), HttpStatus.CREATED);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    // Method updates an existing sermon
    @PutMapping("/edit")
    public ResponseEntity updateSermon(@RequestBody Sermon s) {
        // Try to update the sermon & return updated sermon w/ HTTP status OK; else return error
        try {
            return new ResponseEntity<>(sermonService.editSermon(s), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    // Method deletes an existing sermon
    @DeleteMapping("/delete")
    public ResponseEntity deleteSermon(@RequestBody Sermon s) {
        // Try to delete the sermon & return HTTP status OK; else return error
        try {
            sermonService.deleteSermon(s);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    // Method searches for sermons by title
    @GetMapping("/search/title/{title}")
    public ResponseEntity searchByTitle(@PathVariable String title) {
        // Try to return matching sermons w/ HTTP status OK; else return error
        try {
            return new ResponseEntity<>(sermonService.searchSermonsByTitle(title), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }


    // Method searches for sermons by date range
    @GetMapping("/search/date")
    public ResponseEntity searchByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        // Try to return matching sermons w/ HTTP status OK; else return error
        try {
            return new ResponseEntity<>(sermonService.searchSermonsByDateRange(startDate, endDate), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }


}
