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
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping
    @RequestMapping("/get")
    public ResponseEntity<Iterable<Announcement>> getAll() {
        return new ResponseEntity<>(announcementService.getAnnouncements(), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity get(@PathVariable String id) {
        try {
            return new ResponseEntity<>(announcementService.getAnnouncement(id), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody final Announcement a) {
        try {
            return new ResponseEntity<>(announcementService.createAnnouncement(a), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity edit(@RequestBody Announcement a) {
        try {
            return new ResponseEntity<>(announcementService.updateAnnouncement(a), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Announcement a) {
        try {
            announcementService.deleteAnnouncement(a);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }


}
