package com.RunDMCPP.Backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RunDMCPP.Backend.models.Announcement;
import com.RunDMCPP.Backend.repositories.AnnouncementRepository;

@Service
public class AnnouncementService{
    @Autowired
    AnnouncementRepository announcementRepo;

    public Iterable<Announcement> getAnnouncements(){
        return announcementRepo.findAll();
    }
    
    public Optional<Announcement> getAnnouncement(String id){
        Optional<Announcement> announcement = announcementRepo.findById(id);
        if(announcement.isPresent()){
            return announcement;
        }
        else
            throw new IllegalArgumentException("Announcement " + id + " not found.");
    }
    
    public void updateAnnouncement(Announcement a) {
        Optional<Announcement> entity = announcementRepo.findById(a.getId());

        if (entity.isPresent()) {
                if (a.getTitle() != null) {
                    entity.get().setTitle(a.getTitle());
                }
                if (a.getDescription() != null) {
                    entity.get().setDescription(a.getDescription());
                }
                
        }
        announcementRepo.save(entity.get());
    }

    public void deleteAnnouncement(Announcement a){
        Optional<Announcement> announcement = announcementRepo.findById(a.getId());
        if(announcement.isPresent()){
            if(a.equals(announcement)){
                announcementRepo.deleteById(a.getId());
            }
        }
        else{
            throw new IllegalArgumentException("Announcement " + a.getId() + " not found.");
        }
    }

}

