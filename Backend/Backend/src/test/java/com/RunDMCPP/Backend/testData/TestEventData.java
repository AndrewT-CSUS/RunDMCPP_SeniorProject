package com.RunDMCPP.Backend.testData;

import com.RunDMCPP.Backend.models.Event;

import java.util.ArrayList;
import java.util.List;

public class TestEventData {

    public static Event event1(){
        Event e = new Event();
        e.setDateTime("2024-01-01T20:18");
        e.setId("1");
        e.setEventLocation("Testing Grounds");
        e.setEventDescription("Test data for event 1");
        e.setName("Event 1");
        return e;
    }

    public static Event event2(){
        Event e = new Event();
        e.setDateTime("2024-02-02T20:18");
        e.setId("2");
        e.setEventLocation("Alternate Testing Grounds");
        e.setEventDescription("Test data for event 2");
        e.setName("Event 2");
        return e;
    }

    public static Event event1_2Data(){
        Event e = new Event();
        e.setDateTime("2024-02-02T20:18");
        e.setId("1");
        e.setEventLocation("Alternate Testing Grounds");
        e.setEventDescription("Test data for event 2");
        e.setName("Event 2");
        return e;
    }

    public static List<Event> listOfEvents(){
        List<Event> events = new ArrayList<>();
        events.add(event1());
        events.add(event2());
        return events;
    }

}
