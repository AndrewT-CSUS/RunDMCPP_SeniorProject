package com.RunDMCPP.Backend.controllers;

import com.RunDMCPP.Backend.models.Announcement;
import com.RunDMCPP.Backend.services.AnnouncementService;
import com.RunDMCPP.Backend.utils.BackendErrorException;
import com.RunDMCPP.Backend.utils.BackendErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// Controller class listening for web requests to the /api/announcements endpoints and sends back responses
@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    // @Autowired: Spring automatically creates and give us an AnnouncementService object
    @Autowired
    private AnnouncementService announcementService;

    // Method gets all announcements when somebody asks for /api/announcements/get
    @GetMapping
    @RequestMapping("/get")
    public ResponseEntity<Iterable<Announcement>> getAll() {
        return new ResponseEntity<>(announcementService.getAnnouncements(), HttpStatus.OK);
    }

   // Method gets a specific announcement by id
    @GetMapping("get/{id}")
    public ResponseEntity get(@PathVariable String id) {
        // Try to get the announcement by id & send back HTTP status OK; else send back an error
        try {
            return new ResponseEntity<>(announcementService.getAnnouncement(id), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    // Method creates a new announcement
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody final Announcement a) {
        // Try to create the announcement & send back HTTP status OK; else send back error
        try {
            return new ResponseEntity<>(announcementService.createAnnouncement(a), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    // Method edits an announcement
    @PutMapping("/edit")
    public ResponseEntity edit(@RequestBody Announcement a) {
        // Try to edit the announcement & send back HTTP status OK; else send back an error
        try {
            return new ResponseEntity<>(announcementService.updateAnnouncement(a), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    // Method deletes an announcement
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Announcement a) {
        // Try to delete the announcement & send back HTTP status OK; else send back an error
        try {
            announcementService.deleteAnnouncement(a);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }


}
