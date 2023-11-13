package com.RunDMCPP.Backend.controllers;

import com.RunDMCPP.Backend.models.Event;
import com.RunDMCPP.Backend.services.EventService;
import com.RunDMCPP.Backend.utils.BackendErrorException;
import com.RunDMCPP.Backend.utils.BackendErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    @RequestMapping("/get")
    public ResponseEntity<Iterable<Event>> getAll(){
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/get/{id}")
    public Optional<Event> get(@PathVariable String id){
        return eventService.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody final Event s){
        try {
            return new ResponseEntity<>(eventService.createEvent(s), HttpStatus.CREATED);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity edit(@RequestBody Event s){
        try {
            return new ResponseEntity<>(eventService.updateEvent(s), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Event s){
    try{
        eventService.deleteEvent(s);
        return new ResponseEntity<>(HttpStatus.OK);
    } catch (BackendErrorException e) {
        return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
    }
    }

    @DeleteMapping("/autoDelete")
    public ResponseEntity autoDelete() {
        try{
            eventService.autoDeleteEvents();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }
}