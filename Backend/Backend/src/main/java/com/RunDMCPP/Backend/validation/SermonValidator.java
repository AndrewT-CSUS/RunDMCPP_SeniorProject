package com.RunDMCPP.Backend.validation;

import com.RunDMCPP.Backend.models.Sermon;
import org.springframework.stereotype.Component;

// SermonValidator class used to validate sermons.
@Component
public class SermonValidator {
    // Method to validate reading (fetching) a sermon
    public boolean readValidator(Sermon input) {
        // If the sermon has an id, it is valid
        if (input.getId() != null) {
            return true;
        }
        return false; // Otherwise, it is invalid
    }

    // Method to validate creating a sermon
    public boolean createValidator(Sermon input) {
        // If event has desc, yt link, date/time, id, it is valid
        if (input.getName() != null
                && input.getDescription() != null
                && input.getYoutubeLink() != null
                && input.getDateTime() != null
                && input.getId() == null
        ) {
            return true;
        }
        return false; // Otherwise, it is invalid
    }

    // Method to validate updating a sermon
    public boolean updateValidator(Sermon input, Sermon dbEntity) {
        // If the sermon has an id & matches the one in the DB, it is valid
        if (input.getId() != null) {
            if (input.getId().equals(dbEntity.getId())) {
                return true;
            }
        }
        return false; // Otherwise, it is invalid
    } 

    // Method to validate deleting a sermon
    public boolean deleteValidator(Sermon input, Sermon dbEntity) {
        // If sermon has name, desc, yt link, date/time & matches the one in the DB, it is valid
        if (input.getId() != null
                && input.getName() != null
                && input.getDescription() != null
                && input.getYoutubeLink() != null
                && input.getDateTime() != null
        ) {
            if (input.equals(dbEntity)) {
                return true;
            }
        }
        return false; // Otherwise, it is invalid
    }
}
