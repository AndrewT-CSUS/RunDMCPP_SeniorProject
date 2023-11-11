// SermonController.java
package com.RunDMCPP.Backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.RunDMCPP.Backend.models.Sermon;
import com.RunDMCPP.Backend.services.SermonService;
import java.util.List;
import java.util.Optional;

@RestController                                   
@RequestMapping("/api/DynamoDbPrototype/sermons") 
public class SermonController {
    @Autowired
    private SermonService sermonService;

    @GetMapping
    @RequestMapping("/all")
    public ResponseEntity<List<Sermon>> getAllSermons(){
        List<Sermon> sermons = sermonService.findAll();
        return new ResponseEntity<>(sermons, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Optional<Sermon> getSermonByID(@PathVariable String id){
        return sermonService.findById(id);
    }

     @PostMapping 
    public ResponseEntity<Sermon> addSermon(@RequestBody final Sermon s){
        Sermon result = sermonService.createSermon(s);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}") 
    public ResponseEntity<Sermon> updateSermon(@PathVariable String id, @RequestBody Sermon s) {
        return new ResponseEntity<>(sermonService.editSermon(s), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sermon> deleteSermon(@RequestBody Sermon s){
        sermonService.deleteSermon(s);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // todo: api endpoint to search sermons by title and by date range; i tried but uhhhhh yeah i'm not sure how to do it
    @GetMapping("/search/title/{title}")
    public ResponseEntity<List<Sermon>> searchByTitle(@PathVariable String title) {
        List<Sermon> sermons = sermonService.searchSermonsByTitle(title);
        return new ResponseEntity<>(sermons, HttpStatus.OK);
    }
    @GetMapping("/search/date")
    public ResponseEntity<List<Sermon>> searchByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        List<Sermon> sermons = sermonService.searchSermonsByDateRange(startDate, endDate);
        return new ResponseEntity<>(sermons, HttpStatus.OK);
    }
    

}
