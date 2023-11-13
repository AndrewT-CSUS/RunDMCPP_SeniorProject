package com.RunDMCPP.Backend.services;

import java.util.Optional;

import com.RunDMCPP.Backend.enums.ErrorEnum;
import com.RunDMCPP.Backend.utils.BackendErrorException;
import com.RunDMCPP.Backend.validation.AnnouncementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.RunDMCPP.Backend.models.Announcement;
import com.RunDMCPP.Backend.repositories.AnnouncementRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepo;
    @Autowired
    private AnnouncementValidator announcementValidator;

    public Iterable<Announcement> getAnnouncements() {
        return announcementRepo.findAll();
    }

    public Optional<Announcement> getAnnouncement(String id) throws BackendErrorException {
        Optional<Announcement> announcement = announcementRepo.findById(id);
        if (announcement.isPresent()) {
            return announcement;
        } else
            throw new BackendErrorException(ErrorEnum.NOT_FOUND);
    }

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

    public Announcement updateAnnouncement(Announcement announcement) throws BackendErrorException {
        if (!announcementValidator.readValidator(announcement)) {
            throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
        }
        Optional<Announcement> dbEntity = announcementRepo.findById(announcement.getId());

        if (dbEntity.isPresent()) {
            if (announcementValidator.updateValidator(announcement, dbEntity.get())) {
                if (announcement.getTitle() != null) {
                    dbEntity.get().setTitle(announcement.getTitle());
                }
                if (announcement.getDescription() != null) {
                    dbEntity.get().setDescription(announcement.getDescription());
                }

                try {
                    return announcementRepo.save(dbEntity.get());
                } catch (Exception e) {
                    throw new BackendErrorException(ErrorEnum.TRANSACTION_FAIL);
                }
            }
            throw new BackendErrorException(ErrorEnum.DATA_MISMATCH);
        }
        throw new BackendErrorException(ErrorEnum.NOT_FOUND);
    }

    public void deleteAnnouncement(Announcement announcement) throws BackendErrorException {
        if (!announcementValidator.readValidator(announcement)) {
            throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
        }
        Optional<Announcement> dbEntity = announcementRepo.findById(announcement.getId());
        if (dbEntity.isPresent()) {
            if (announcementValidator.deleteValidator(announcement, dbEntity.get())) {
                announcementRepo.deleteById(announcement.getId());
            } else {
                throw new BackendErrorException(ErrorEnum.DATA_MISMATCH);
            }
        } else {
            throw new BackendErrorException(ErrorEnum.NOT_FOUND);
        }
    }

}

