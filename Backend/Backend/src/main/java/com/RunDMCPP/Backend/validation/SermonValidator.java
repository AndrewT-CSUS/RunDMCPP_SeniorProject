package com.RunDMCPP.Backend.validation;

import com.RunDMCPP.Backend.models.Sermon;
import org.springframework.stereotype.Component;

@Component
public class SermonValidator {

    public boolean readValidator(Sermon input) {
        if (input.getId() != null) {
            return true;
        }
        return false;
    }

    public boolean createValidator(Sermon input) {
        if (input.getName() != null
                && input.getDescription() != null
                && input.getYoutubeLink() != null
                && input.getDateTime() != null
                && input.getId() == null
        ) {
            return true;
        }
        return false;
    }

    public boolean updateValidator(Sermon input, Sermon dbEntity) {

        if (input.getId() != null) {
            if (input.getId().equals(dbEntity.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteValidator(Sermon input, Sermon dbEntity) {

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
        return false;
    }
}
