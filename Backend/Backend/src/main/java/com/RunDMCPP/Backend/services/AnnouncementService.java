package com.RunDMCPP.Backend.services;

import com.RunDMCPP.Backend.enums.ErrorEnum;
import com.RunDMCPP.Backend.models.Announcement;
import com.RunDMCPP.Backend.repositories.AnnouncementRepository;
import com.RunDMCPP.Backend.utils.BackendErrorException;
import com.RunDMCPP.Backend.validation.AnnouncementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.RunDMCPP.Backend.models.Announcement;
import com.RunDMCPP.Backend.repositories.AnnouncementRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// AnnouncementService. Services are Springs components that deal w/ business logic.
@Service
public class AnnouncementService {
    // @Autowired: Spring automatically creates and gives us Announcement Repo/Validator objects
    @Autowired
    private AnnouncementRepository announcementRepo;
    @Autowired
    private AnnouncementValidator announcementValidator;

    // Method gets all announcements from the database
    public Iterable<Announcement> getAnnouncements() {
        return announcementRepo.findAll();
    }

    // Method gets a specific announcement by id, throws error if not found
    public Optional<Announcement> getAnnouncement(String id) throws BackendErrorException {
        Optional<Announcement> announcement = announcementRepo.findById(id);
        if (announcement.isPresent()) {
            return announcement;
        } else
            throw new BackendErrorException(HttpStatus.NOT_FOUND, ErrorEnum.NOT_FOUND);
    }

    // Method creates a new announcement, validates input, throws error if invalid
    public Announcement createAnnouncement(Announcement announcement) throws BackendErrorException {
        if (announcementValidator.createValidator(announcement)) {
            try {
                return announcementRepo.save(announcement);
            } catch (Exception e) {
                throw new BackendErrorException(ErrorEnum.TRANSACTION_FAIL);
            }
        }
        throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
    }

    // Method updates an existing announcement, validates input, throws error if invalid
    public Announcement updateAnnouncement(Announcement announcement) throws BackendErrorException {
        // If the announcement is invalid, throw an error
        if (!announcementValidator.readValidator(announcement)) {
            throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
        }
        // Try to find announcement in DB by id
        Optional<Announcement> dbEntity = announcementRepo.findById(announcement.getId());

        // Check if announcement exists in DB
        if (dbEntity.isPresent()) {
            // Check if updated data is valid
            if (announcementValidator.updateValidator(announcement, dbEntity.get())) {
                // If updated data is valid, update the announcement w/ new title
                if (announcement.getTitle() != null) {
                    dbEntity.get().setTitle(announcement.getTitle());
                }
                // If updated data is valid, update the announcement w/ new description
                if (announcement.getDescription() != null) {
                    dbEntity.get().setDescription(announcement.getDescription());
                }
                // If updated data is valid, update the announcement w/ new date, else throw error
                try {
                    return announcementRepo.save(dbEntity.get());
                } catch (Exception e) {
                    throw new BackendErrorException(ErrorEnum.TRANSACTION_FAIL);
                }
            }
            // If updated data is invalid, throw error
            throw new BackendErrorException(ErrorEnum.DATA_MISMATCH); 
        }
        // If announcement doesn't exist in DB, throw error
        throw new BackendErrorException(HttpStatus.NOT_FOUND, ErrorEnum.NOT_FOUND);
    }

    // Method deletes an announcement, and checks if the announcement exists in the DB
    public void deleteAnnouncement(Announcement announcement) throws BackendErrorException {
        // If the announcement is invalid, throw an error
        if (!announcementValidator.readValidator(announcement)) {
            throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
        }
        // Try to find announcement in DB by id
        Optional<Announcement> dbEntity = announcementRepo.findById(announcement.getId());

        // Check if announcement exists in DB
        if (dbEntity.isPresent()) {
            // Check if updated data is valid, else throw error
            if (announcementValidator.deleteValidator(announcement, dbEntity.get())) {
                announcementRepo.deleteById(announcement.getId());
            } else {
                throw new BackendErrorException(ErrorEnum.DATA_MISMATCH);
            }
        } else {
            // If announcement doesn't exist in DB, throw error
            throw new BackendErrorException(HttpStatus.NOT_FOUND, ErrorEnum.NOT_FOUND);
        }
    }

    // Method searches for announcements by title, throws error if not found
    public List<Announcement> searchAnnouncementsByTitle(String title) throws BackendErrorException {
        List<Announcement> results = announcementRepo.findByTitleContaining(title);
        if(results.isEmpty()){
            throw new BackendErrorException(ErrorEnum.NOT_FOUND);
        }
        return results;
    }

    public List<Announcement> getThreeRecentAnnouncements(){
        List<Announcement> allAnnouncements = (List<Announcement>) announcementRepo.findAll();

        if(allAnnouncements.isEmpty()) {
            return Collections.emptyList();
        }

        List<Announcement> sortedAnnouncements = allAnnouncements.stream().sorted((a,b) -> Long.compare(b.getTtl(), a.getTtl())).collect(Collectors.toList());

        return sortedAnnouncements.stream().limit(3).collect(Collectors.toList());
    }
}
