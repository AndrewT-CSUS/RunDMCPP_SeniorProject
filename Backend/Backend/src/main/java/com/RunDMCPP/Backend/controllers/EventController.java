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
// Controller class listening for web requests to the /api/events endpoints and sends back responses
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/events")
public class EventController {

    // @Autowired lets Spring automatically create and give us an EventService object
    @Autowired
    private EventService eventService;

    // Method gets all events at '/api/events/get' and responds with a list
    @GetMapping
    @RequestMapping("/get")
    public ResponseEntity<Iterable<Event>> getAll(){
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

    // Method gets a specific event by id; accessible at /api/events/get/{id}
    @GetMapping
    @RequestMapping("/get/{id}")
    public Optional<Event> get(@PathVariable String id){
        return eventService.findById(id);
    }

    // Method creates a new event at '/api/events/create'
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody final Event s){
        // Try to create the event; else send back an error
        try {
            return new ResponseEntity<>(eventService.createEvent(s), HttpStatus.CREATED);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    // Method edits an event at '/api/events/edit'
    @PutMapping("/edit")
    public ResponseEntity edit(@RequestBody Event s){
        // Try to edit the event & return updated event w/ HTTP status 200; else send back an error
        try {
            return new ResponseEntity<>(eventService.editEvent(s), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }

    // Method deletes an event at '/api/events/delete'
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Event s){
    // Try to delete the event & return HTTP status 200; else send back an error
    try{
        eventService.deleteEvent(s);
        return new ResponseEntity<>(HttpStatus.OK);
    } catch (BackendErrorException e) {
        return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
    }
    }

    // Method automatically deletes events based on a condition at '/api/events/autoDelete'
    @DeleteMapping("/autoDelete")
    public ResponseEntity autoDelete() {
        // Try to auto delete events & return HTTP status 200; else send back an error
        try{
            eventService.autoDeleteEvents();
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
            return new ResponseEntity<>(eventService.searchEventByTitle(title), HttpStatus.OK);
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
            return new ResponseEntity<>(eventService.searchEventByDateRange(startDate, endDate), HttpStatus.OK);
        } catch (BackendErrorException e) {
            return new ResponseEntity<>(new BackendErrorResponse(e), e.getHttpStatus());
        }
    }
}