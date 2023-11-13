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
@RestController
@RequestMapping("/api/sermons")
public class SermonController {
    @Autowired
    private SermonService sermonService;

    @GetMapping
    @RequestMapping("/get")
    public ResponseEntity<Iterable<Sermon>> getAllSermons() {
        return new ResponseEntity<>(sermonService.findAll(), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/get/{id}")
    public ResponseEntity getSermonByID(@PathVariable String id) {
        try {
            return new ResponseEntity<>(sermonService.findById(id), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity addSermon(@RequestBody final Sermon s) {
        try {
            return new ResponseEntity<>(sermonService.createSermon(s), HttpStatus.CREATED);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity updateSermon(@RequestBody Sermon s) {
        try {
            return new ResponseEntity<>(sermonService.editSermon(s), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteSermon(@RequestBody Sermon s) {
        try {
            sermonService.deleteSermon(s);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    @GetMapping("/search/title/{title}")
    public ResponseEntity searchByTitle(@PathVariable String title) {
        try {
            return new ResponseEntity<>(sermonService.searchSermonsByTitle(title), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    @GetMapping("/search/date")
    public ResponseEntity searchByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        try {
            return new ResponseEntity<>(sermonService.searchSermonsByDateRange(startDate, endDate), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }


}
