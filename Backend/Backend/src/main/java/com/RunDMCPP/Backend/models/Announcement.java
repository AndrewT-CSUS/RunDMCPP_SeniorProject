package com.RunDMCPP.Backend.models;

import java.time.Instant;
import java.util.Objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

// Class representing an announcement, stored in the announcements table in DynamoDB
@DynamoDBTable(tableName = "announcement")
public class Announcement {

    private String id;          // Unique identifier for the announcement
    private String title;       // Title of the announcement
    private String description; // Description of the announcement
    private long ttl;           // Time to Live value to be configured in AWS

    // Getters and Setters
    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "title") 
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute
    public long getTtl(){ 
        return this.ttl;
    }
    public void setTtl(long t){
        ttl = t;
    }

    public Announcement() {
        long now = Instant.now().getEpochSecond(); // gets current time in seconds
        this.ttl = now + 60 * 60 * 24 * 28; // sets ttl to 4 weeks in seconds
    }
    
   @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Announcement that = (Announcement) o;
        return ttl == that.ttl && Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, ttl);
    }
}