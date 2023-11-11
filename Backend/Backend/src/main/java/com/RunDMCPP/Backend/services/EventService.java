package com.RunDMCPP.Backend.services;

import com.RunDMCPP.Backend.enums.EventEnum;
import com.RunDMCPP.Backend.models.Event;
import com.RunDMCPP.Backend.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Optional<Event> findById(String id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event s) {
        //Maybe do some validation here on the input event
        Event result;
        try {
            result = eventRepository.save(s);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, EventEnum.TRANSACTION_FAIL_E.getValue());
        }
        return result;
    }

    public List<Event> findAll() {
        Iterable<Event> events = eventRepository.findAll();
        List<Event> eventList = new ArrayList<>();
        events.forEach(eventList::add);
        return eventList;
    }

    public Event editEvent(Event s) {
        //Validate the inputs later on
        Optional<Event> dbEntity = eventRepository.findById(s.getId());

        if (dbEntity.isPresent()) {
            try {
                if (s.getEventTitle() != null) {
                    dbEntity.get().setEventTitle(s.getEventTitle());
                }
                if (s.getEventDescription() != null) {
                    dbEntity.get().setEventDescription(s.getEventDescription());
                }
                if (s.getDateTime() != null) {
                    dbEntity.get().setDateTime(s.getDateTime());
                }

                if (s.getEventLocation() != null) {
                    dbEntity.get().setEventLocation(s.getEventLocation());
                }

                return eventRepository.save(dbEntity.get());
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, EventEnum.TRANSACTION_FAIL_E.getValue());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, EventEnum.NOT_FOUND_E.getValue());
        }
    }

    public void deleteEvent(Event s) {
        Optional<Event> dbEntity = eventRepository.findById(s.getId());
        if (dbEntity.isPresent()) {
            if (dbEntity.get().equals(s)) {

                eventRepository.deleteById(s.getId());
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, EventEnum.DATA_MISMATCH.getValue());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, EventEnum.NOT_FOUND_E.getValue());
        }
    }

    public void autoDeleteEvents() {

        for (int i = 0; i < eventRepository.count(); i++) {
            Optional<Event> dbEntity = eventRepository.findById(Integer.toString(i));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
            Calendar cal = Calendar.getInstance();
            try{  
                cal.setTime(sdf.parse(dbEntity.get().getDateTime()));  
            }catch(Exception e){  
                e.printStackTrace();  
            }
            
            cal.add(Calendar.DAY_OF_MONTH, 28);
            String expiryDate = sdf.format(cal.getTime());

            String currDate = sdf.format(Calendar.DAY_OF_MONTH);

            if (currDate == expiryDate ) {
                eventRepository.deleteById(dbEntity.get().getId());
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, EventEnum.NOT_FOUND_E.getValue());
            }
        }
    }
}