package com.RunDMCPP.Backend.validation;

import com.RunDMCPP.Backend.models.Event;
import org.springframework.stereotype.Component;

@Component
public class EventValidator {

    public boolean readValidator(Event input) {
        if (input.getId() != null) {
            return true;
        }
        return false;
    }

    public boolean createValidator(Event input) {
        if (input.getEventTitle() != null
                && input.getEventDescription() != null
                && input.getEventLocation() != null
                && input.getDateTime() != null
                && input.getId() == null
        ) {
            return true;
        }
        return false;
    }

    public boolean updateValidator(Event input, Event dbEntity) {

        if (input.getId() != null) {
            if (input.getId().equals(dbEntity.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteValidator(Event input, Event dbEntity) {

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
        return false;
    }
}
