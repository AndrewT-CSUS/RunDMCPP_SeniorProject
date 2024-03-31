package com.RunDMCPP.Backend.services;

import com.RunDMCPP.Backend.enums.ErrorEnum;
import com.RunDMCPP.Backend.models.Event;
import com.RunDMCPP.Backend.models.Event;
import com.RunDMCPP.Backend.repositories.EventRepository;
import com.RunDMCPP.Backend.utils.BackendErrorException;
import com.RunDMCPP.Backend.validation.EventValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

// EventService. Services are Springs components that deal w/ business logic.
@Service
public class EventService {

    // @Autowired: Spring automatically creates and give us Event Repo/Validator objects
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventValidator eventValidator;

    // Method gets all events from the database
    public Iterable<Event> findAll() {
        return eventRepository.findAll();
    }

    // Method gets a specific event by id
    public Optional<Event> findById(String id) throws BackendErrorException {
        Optional<Event> dbEntity = eventRepository.findById(id);
        if(dbEntity.isPresent()){
            return dbEntity;
        }
        throw new BackendErrorException(HttpStatus.NOT_FOUND, ErrorEnum.NOT_FOUND);
    }

    // Method creates a new event, validates input, throws error if invalid
    public Event createEvent(Event event) throws BackendErrorException {
        if (eventValidator.createValidator(event)) {
            try {
                return eventRepository.save(event);
            } catch (Exception e) {
                throw new BackendErrorException(ErrorEnum.TRANSACTION_FAIL);
            }
        }
        throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
    }

    // Method updates an existing event, validates input, throws error if invalid
    public Event updateEvent(Event event) throws BackendErrorException {
        // If the event is invalid, throw an error
        if (!eventValidator.readValidator(event)) {
            throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
        }
        // Try to find event in DB by id
        Optional<Event> dbEntity = eventRepository.findById(event.getId());

        // Check if event exists in DB
        if (dbEntity.isPresent()) {
            // If the event is valid, update the event
            if (eventValidator.updateValidator(event, dbEntity.get())) {
                // If the event title/desc/time/location is not null, update with the new data
                if (event.getName() != null) {
                    dbEntity.get().setName(event.getName());
                }
                if (event.getEventDescription() != null) {
                    dbEntity.get().setEventDescription(event.getEventDescription());
                }
                if (event.getDateTime() != null) {
                    dbEntity.get().setDateTime(event.getDateTime());
                }

                if (event.getEventLocation() != null) {
                    dbEntity.get().setEventLocation(event.getEventLocation());
                }
                // Try to save the updated event, throw error if transaction fails
                try {
                    return eventRepository.save(dbEntity.get());
                } catch (Exception e) {
                    throw new BackendErrorException(ErrorEnum.TRANSACTION_FAIL);
                }
            }
            // If the event is invalid, throw error
            throw new BackendErrorException(ErrorEnum.DATA_MISMATCH);
        }
        // If event doesn't exist in DB, throw error
        throw new BackendErrorException(HttpStatus.NOT_FOUND, ErrorEnum.NOT_FOUND);
    }

    // Method deletes an existing event, validates input, throws error if invalid
    public void deleteEvent(Event event) throws BackendErrorException {
        // If the event is invalid, throw an error
        if (!eventValidator.readValidator(event)) {
            throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
        }
        // Try to find event in DB by id
        Optional<Event> dbEntity = eventRepository.findById(event.getId());

        // Check if event exists in DB
        if (dbEntity.isPresent()) {
            // If the event is valid, delete the event
            if (eventValidator.deleteValidator(event, dbEntity.get())) {
                // Try to delete the event, throw error if transaction fails
                try {
                    eventRepository.deleteById(event.getId());
                } catch (Exception e) {
                    throw new BackendErrorException(ErrorEnum.TRANSACTION_FAIL);
                }
            } else {
                // If the event is invalid, throw error
                throw new BackendErrorException(ErrorEnum.DATA_MISMATCH);
            }
        } else {
            // If event doesn't exist in DB, throw error
            throw new BackendErrorException(HttpStatus.NOT_FOUND, ErrorEnum.NOT_FOUND);
        }
    }

    // Method searches for sermons by title, throws error if not found
    public List<Event> searchEventByTitle(String title) throws BackendErrorException {
        List<Event> results = eventRepository.findByNameContaining(title);
        if(results.isEmpty()){
            throw new BackendErrorException(ErrorEnum.NOT_FOUND);
        }
        return results;
    }

    // Method searches for sermons by date range, throws error if not found
    public List<Event> searchEventByDateRange(String startDate, String endDate) throws BackendErrorException {
        List<Event> results = eventRepository.findByDateTimeBetween(startDate, endDate);
        if(results.isEmpty()){
            throw new BackendErrorException(ErrorEnum.NOT_FOUND);
        }
        return results;
    }
}