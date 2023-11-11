package com.RunDMCPP.Backend.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.RunDMCPP.Backend.models.Announcement;

@EnableScan
public interface AnnouncementRepository extends CrudRepository<Announcement, String>{

}
