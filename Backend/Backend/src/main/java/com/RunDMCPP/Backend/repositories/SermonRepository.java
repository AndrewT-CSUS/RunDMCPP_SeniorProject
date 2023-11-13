// SermonRepository.java
package com.RunDMCPP.Backend.repositories;
import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import com.RunDMCPP.Backend.models.Sermon;

@EnableScan
public interface SermonRepository extends CrudRepository<Sermon, String> {

    // Method to find sermons by title
    List<Sermon> findByNameContaining(String name);

    // Method to find sermons by a range of dateTime
    // todo: this is a simplified version.  we might need to format and handle dateTime strings properly later?
    List<Sermon> findByDateTimeBetween(String startDateTime, String endDateTime);
}