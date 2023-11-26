package com.RunDMCPP.Backend.services;

import com.RunDMCPP.Backend.enums.ErrorEnum;
import com.RunDMCPP.Backend.models.Sermon;
import com.RunDMCPP.Backend.repositories.SermonRepository;
import com.RunDMCPP.Backend.utils.BackendErrorException;
import com.RunDMCPP.Backend.validation.SermonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

// SermonService. Services are Springs components that deal w/ business logic.
@Service
public class SermonService {

    // @Autowired: Spring automatically creates and give us Sermon Repo/Validator objects
    @Autowired
    private SermonRepository sermonRepository;
    @Autowired
    private SermonValidator sermonValidator;

    // Method gets all sermons from the database
    public Iterable<Sermon> findAll() {
        return sermonRepository.findAll();
    }

    // Method gets a specific sermon by id, throws error if not found
    public Optional<Sermon> findById(String id) throws BackendErrorException {
        Optional<Sermon> dbEntity = sermonRepository.findById(id);
        if(dbEntity.isPresent()){
            return dbEntity;
        }
        throw new BackendErrorException(ErrorEnum.NOT_FOUND);
    }

    // Method creates a new sermon, validates input, throws error if invalid
    public Sermon createSermon(Sermon sermon) throws BackendErrorException {
        if (sermonValidator.createValidator(sermon)) {
            try {
                return sermonRepository.save(sermon);
            } catch (Exception e) {
                throw new BackendErrorException(ErrorEnum.TRANSACTION_FAIL);
            }
        }
        throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
    }

    // Method updates an existing sermon, validates input, throws error if invalid
    public Sermon editSermon(Sermon sermon) throws BackendErrorException {
        // If the sermon is invalid, throw an error
        if (!sermonValidator.readValidator(sermon)) {
            throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
        }
        // Try to find sermon in DB by id
        Optional<Sermon> dbEntity = sermonRepository.findById(sermon.getId());

        // Check if sermon exists in DB
        if (dbEntity.isPresent()) {
            // If the sermon is valid, update the sermon
            if (sermonValidator.updateValidator(sermon, dbEntity.get())) {
                // Set the sermon's fields to the new values
                if (sermon.getName() != null) {
                    dbEntity.get().setName(sermon.getName());
                }
                if (sermon.getDescription() != null) {
                    dbEntity.get().setDescription(sermon.getDescription());
                }
                if (sermon.getDateTime() != null) {
                    dbEntity.get().setDateTime(sermon.getDateTime());
                }
                if (sermon.getYoutubeLink() != null) {
                    dbEntity.get().setYoutubeLink(sermon.getYoutubeLink());
                }

                // Try to save the updated sermon, throw error if transaction fails
                try {
                    return sermonRepository.save(dbEntity.get());
                } catch (Exception e) {
                    throw new BackendErrorException(ErrorEnum.TRANSACTION_FAIL);
                }
            }
            // If the sermon is invalid, throw error
            throw new BackendErrorException(ErrorEnum.DATA_MISMATCH);
        }
        // If sermon doesn't exist in DB, throw error
        throw new BackendErrorException(ErrorEnum.NOT_FOUND);
    }

    // Method deletes a sermon, and checks if the sermon exists in the DB
    public void deleteSermon(Sermon sermon) throws BackendErrorException {
        // If the sermon is invalid, throw an error
        if (!sermonValidator.readValidator(sermon)) {
            throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
        }
        // Try to find sermon in DB by id
        Optional<Sermon> dbEntity = sermonRepository.findById(sermon.getId());

        // Check if sermon exists in DB
        if (dbEntity.isPresent()) {
            // Check if updated data is valid, else throw error
            if (sermonValidator.deleteValidator(sermon, dbEntity.get())) {
                // Try to delete sermon, else throw error
                try {
                    sermonRepository.deleteById(sermon.getId());
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

    // Method searches for sermons by title, throws error if not found
    public List<Sermon> searchSermonsByTitle(String title) throws BackendErrorException {
        List<Sermon> results = sermonRepository.findByNameContaining(title);
        if(results.isEmpty()){
            throw new BackendErrorException(ErrorEnum.NOT_FOUND);
        }
        return results;
    }

    // Method searches for sermons by date range, throws error if not found
    public List<Sermon> searchSermonsByDateRange(String startDate, String endDate) throws BackendErrorException {
        List<Sermon> results = sermonRepository.findByDateTimeBetween(startDate, endDate);
        if(results.isEmpty()){
            throw new BackendErrorException(ErrorEnum.NOT_FOUND);
        }
        return results;
    }
}
