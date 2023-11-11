package com.RunDMCPP.Backend.controllers;

import com.RunDMCPP.Backend.models.Event;
import com.RunDMCPP.Backend.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/DynamoDbPrototype/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    @RequestMapping("get")
    public ResponseEntity<List<Event>> getAll(){
        List<Event> events = eventService.findAll();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("get/{id}")
    public Optional<Event> get(@PathVariable String id){
        return eventService.findById(id);
    }

    @PutMapping("/create")
    public ResponseEntity<Event> create(@RequestBody final Event s){
        Event result = eventService.createEvent(s);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/edit")
    public ResponseEntity<Event> edit(@RequestBody Event s){
        return new ResponseEntity<>(eventService.editEvent(s), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Event> delete(@RequestBody Event s){
        eventService.deleteEvent(s);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Event> autoDelete(@RequestBody Event s) {
        eventService.autoDeleteEvents();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}