package com.RunDMCPP.Backend.validation;

import com.RunDMCPP.Backend.models.Announcement;
import org.springframework.stereotype.Component;

// AnnouncementValidator class used to validate announcements.
@Component
public class AnnouncementValidator {

    // Method to validate reading (fetching) an announcement
    public boolean readValidator(Announcement input) {
        // If the announcement has an id, it is valid
        if (input.getId() != null) {
            return true;
        }
        return false; // Otherwise, it is invalid
    }

    // Method to validate creating an announcement
    public boolean createValidator(Announcement input) {
        // If the announcement has a title and description, and no id, it is valid
        if (input.getTitle() != null
                && input.getDescription() != null
                && input.getId() == null
        ) {
            return true; 
        }
        return false; // Otherwise, it is invalid
    }

    // Method to validate updating an announcement
    public boolean updateValidator(Announcement input, Announcement dbEntity) {
        // If the announcement has an id & matches the one in the DB, it is valid
        if (input.getId() != null) {
            if (input.getId().equals(dbEntity.getId())) {
                return true;
            }
        }
        return false; // Otherwise, it is invalid
    }

    // Method to validate deleting an announcement
    public boolean deleteValidator(Announcement input, Announcement dbEntity) {
        // If the announcement has an id & matches the one in the DB, it is valid
        if (input.getId() != null
                && input.getTitle() != null
                && input.getDescription() != null) {
            if (input.getId().equals(dbEntity.getId())
                    && input.getTitle().equals(dbEntity.getTitle())
                    && input.getDescription().equals(dbEntity.getDescription())) {
                return true;
            }
        }
        return false; // Otherwise, it is invalid
    }

}
