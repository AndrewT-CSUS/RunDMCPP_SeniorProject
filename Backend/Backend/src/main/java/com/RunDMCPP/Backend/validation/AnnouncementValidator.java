package com.RunDMCPP.Backend.validation;

import com.RunDMCPP.Backend.models.Announcement;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementValidator {

    public boolean readValidator(Announcement input) {
        if (input.getId() != null) {
            return true;
        }
        return false;
    }

    public boolean createValidator(Announcement input) {
        //maybe check if id IS null
        if (input.getTitle() != null
                && input.getDescription() != null
                && input.getId() == null
        ) {
            return true;
        }
        return false;
    }

    public boolean updateValidator(Announcement input, Announcement dbEntity) {

        if (input.getId() != null) {
            if (input.getId().equals(dbEntity.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteValidator(Announcement input, Announcement dbEntity) {

        if (input.getId() != null
                && input.getTitle() != null
                && input.getDescription() != null) {
            if (input.getId().equals(dbEntity.getId())
                    && input.getTitle().equals(dbEntity.getTitle())
                    && input.getDescription().equals(dbEntity.getDescription())) {
                return true;
            }
        }
        return false;
    }

}
