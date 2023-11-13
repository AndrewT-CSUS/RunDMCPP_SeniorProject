package com.RunDMCPP.Backend.services;

import com.RunDMCPP.Backend.enums.ErrorEnum;
import com.RunDMCPP.Backend.models.Event;
import com.RunDMCPP.Backend.repositories.EventRepository;
import com.RunDMCPP.Backend.utils.BackendErrorException;
import com.RunDMCPP.Backend.validation.EventValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventValidator eventValidator;

    public Iterable<Event> findAll() {
        return eventRepository.findAll();
    }

    public Optional<Event> findById(String id) {
        //TODO
        return eventRepository.findById(id);
    }

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

    public Event updateEvent(Event event) throws BackendErrorException {
        if (!eventValidator.readValidator(event)) {
            throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
        }
        Optional<Event> dbEntity = eventRepository.findById(event.getId());

        if (dbEntity.isPresent()) {
            if (eventValidator.updateValidator(event, dbEntity.get())) {
                if (event.getEventTitle() != null) {
                    dbEntity.get().setEventTitle(event.getEventTitle());
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

                try {
                    return eventRepository.save(dbEntity.get());
                } catch (Exception e) {
                    throw new BackendErrorException(ErrorEnum.TRANSACTION_FAIL);
                }
            }
            throw new BackendErrorException(ErrorEnum.DATA_MISMATCH);
        }
        throw new BackendErrorException(ErrorEnum.NOT_FOUND);
    }

    public void deleteEvent(Event event) throws BackendErrorException {
        if (!eventValidator.readValidator(event)) {
            throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
        }
        Optional<Event> dbEntity = eventRepository.findById(event.getId());
        if (dbEntity.isPresent()) {
            if (eventValidator.deleteValidator(event, dbEntity.get())) {
                try {
                    eventRepository.deleteById(event.getId());
                } catch (Exception e) {
                    throw new BackendErrorException(ErrorEnum.TRANSACTION_FAIL);
                }
            } else {
                throw new BackendErrorException(ErrorEnum.DATA_MISMATCH);
            }
        } else {
            throw new BackendErrorException(ErrorEnum.NOT_FOUND);
        }
    }

    public void autoDeleteEvents() throws BackendErrorException {

        for (int i = 0; i < eventRepository.count(); i++) {
            Optional<Event> dbEntity = eventRepository.findById(Integer.toString(i));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            try {
                cal.setTime(sdf.parse(dbEntity.get().getDateTime()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            cal.add(Calendar.DAY_OF_MONTH, 28);
            String expiryDate = sdf.format(cal.getTime());

            String currDate = sdf.format(Calendar.DAY_OF_MONTH);

            if (currDate == expiryDate) {
                eventRepository.deleteById(dbEntity.get().getId());
            } else {
                throw new BackendErrorException(ErrorEnum.NOT_FOUND);
            }
        }
    }
}