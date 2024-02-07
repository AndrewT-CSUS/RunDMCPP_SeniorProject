package com.RunDMCPP.Backend.validation;

import com.RunDMCPP.Backend.models.Event;
import org.springframework.stereotype.Component;

// EventValidator class used to validate events.
@Component 
public class EventValidator {
    // Method to validate reading (fetching) an event
    public boolean readValidator(Event input) {
        // If the event has an id, it is valid
        if (input.getId() != null) {
            return true;
        }
        return false; // Otherwise, it is invalid
    }

    // Method to validate creating an event
    public boolean createValidator(Event input) {
        // If the event has a title, description, location, date/time, id, it is valid
        if (input.getEventTitle() != null
                && input.getEventDescription() != null
                && input.getEventLocation() != null
                && input.getDateTime() != null
                && input.getId() == null
        ) {
            return true; 
        }
        return false; // Otherwise, it is invalid
    }

    // Method to validate updating an event
    public boolean updateValidator(Event input, Event dbEntity) {
        // If the event has an id & matches the one in the DB, it is valid
        if (input.getId() != null) {
            if (input.getId().equals(dbEntity.getId())) {
                return true;
            }
        }
        return false; // Otherwise, it is invalid
    }

    // Method to validate deleting an event
    public boolean deleteValidator(Event input, Event dbEntity) {
        // If event has id, title, desc, location, date/time & matches the one in the DB, it is valid
        if (input.getId() != null
                && input.getEventTitle() != null
                && input.getEventDescription() != null
                && input.getDateTime() != null
                && input.getEventLocation() != null
        ) {
            if (input.equals(dbEntity)) {
                return true; 
            }
        }
        return false; // Otherwise, it is invalid
    }
}
