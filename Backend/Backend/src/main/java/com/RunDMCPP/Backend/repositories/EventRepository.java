package com.RunDMCPP.Backend.repositories;

import com.RunDMCPP.Backend.models.Event;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface EventRepository extends CrudRepository<Event, String> {
}