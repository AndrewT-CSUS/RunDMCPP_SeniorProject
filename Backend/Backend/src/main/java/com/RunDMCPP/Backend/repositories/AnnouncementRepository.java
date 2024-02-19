package com.RunDMCPP.Backend.repositories;

import java.util.List;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import com.RunDMCPP.Backend.models.Announcement;

@EnableScan
public interface AnnouncementRepository extends CrudRepository<Announcement, String> {
    // Method to find events by name
    List<Announcement> findByTitleContaining(String title);
}
