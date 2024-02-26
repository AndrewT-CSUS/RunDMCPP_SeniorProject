package com.RunDMCPP.Backend.repositories;
import java.util.List;

import com.RunDMCPP.Backend.models.Event;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface EventRepository extends CrudRepository<Event, String> {
    // Method to find events by title
    List<Event> findByNameContaining(String name);

    // Method to find events by a range of dateTime
    // todo: this is a simplified version.  we might need to format and handle dateTime strings properly later?
    List<Event> findByDateTimeBetween(String startDateTime, String endDateTime);
}