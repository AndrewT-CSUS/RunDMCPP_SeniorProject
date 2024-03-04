package com.RunDMCPP.Backend.controllers;

import com.RunDMCPP.Backend.models.Event;
import com.RunDMCPP.Backend.services.EventService;
import com.RunDMCPP.Backend.testData.TestEventData;
import com.RunDMCPP.Backend.testData.TestExceptionData;
import com.RunDMCPP.Backend.utils.BackendErrorException;
import com.RunDMCPP.Backend.utils.BackendErrorResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EventControllerTest {
    
    @InjectMocks
    private EventController eventController;
    
    @Mock
    private EventService eventService;

    //No need to test the controller endpoint for getting all items, it's just a wrapper for the service method

    @Test
    public void getEventById_Success() throws BackendErrorException {
        when(eventService.findById(any(String.class))).thenReturn(Optional.of(TestEventData.event1()));

        ResponseEntity result = eventController.get("1");
        Event event = ((Optional<Event>) result.getBody()).get();


        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(event.equals(TestEventData.event1())).isTrue();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getEventById_Failure() throws BackendErrorException {
        when(eventService.findById(any(String.class))).thenThrow(TestExceptionData.backendError_NotFound());

        ResponseEntity result = eventController.get("1");

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void addEvent_Success() throws BackendErrorException{
        when(eventService.createEvent(any(Event.class))).thenReturn(TestEventData.event1());

        ResponseEntity result = eventController.create(TestEventData.event1());
        Event event = (Event) result.getBody();


        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(event.equals(TestEventData.event1())).isTrue();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void addEvent_Failure() throws BackendErrorException{
        when(eventService.createEvent(any(Event.class))).thenThrow(TestExceptionData.backendError_InvalidInput());

        ResponseEntity result = eventController.create(TestEventData.event1());


        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(3);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void updateEvent_Success() throws BackendErrorException{
        when(eventService.updateEvent(any(Event.class))).thenReturn(TestEventData.event1());

        ResponseEntity result = eventController.edit(TestEventData.event1());
        Event event = (Event) result.getBody();


        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(event.equals(TestEventData.event1())).isTrue();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void updateEvent_Failure() throws BackendErrorException{
        when(eventService.updateEvent(any(Event.class))).thenThrow(TestExceptionData.backendError_InvalidInput());

        ResponseEntity result = eventController.edit(TestEventData.event1());


        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(3);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void deleteEvent_Success(){
        ResponseEntity result = eventController.delete(TestEventData.event1());

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deleteEvent_Failure() throws BackendErrorException{
        doThrow(TestExceptionData.backendError_InvalidInput()).when(eventService).deleteEvent(any(Event.class));

        ResponseEntity result = eventController.delete(TestEventData.event1());


        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(3);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
       @Test
    public void searchByTitle_Success() throws BackendErrorException {
        when(eventService.searchEventByTitle(any(String.class))).thenReturn(TestEventData.listOfEvents());

        ResponseEntity result = eventController.searchByTitle("Test");
        List<Event> events =  (List<Event>)result.getBody();

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(events.contains(TestEventData.event1())).isTrue();
        assertThat(events.contains(TestEventData.event2())).isTrue();
    }

    @Test
    public void searchByTitle_Failure() throws BackendErrorException {
        when(eventService.searchEventByTitle(any(String.class))).thenThrow(TestExceptionData.backendError_NotFound());

        ResponseEntity result = eventController.searchByTitle("Test");

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


    @Test
    public void searchByDateRange_Success() throws BackendErrorException {
        when(eventService.searchEventByDateRange(any(String.class), any(String.class))).thenReturn(TestEventData.listOfEvents());

        ResponseEntity result = eventController.searchByDateRange("Date1", "Date2");   //Just for test, these don't really matter
        List<Event> events =  (List<Event>)result.getBody();

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(events.contains(TestEventData.event1())).isTrue();
        assertThat(events.contains(TestEventData.event2())).isTrue();
    }

    @Test
    public void searchByDateRange_Failure() throws BackendErrorException {
        when(eventService.searchEventByDateRange(any(String.class), any(String.class))).thenThrow(TestExceptionData.backendError_NotFound());

        ResponseEntity result = eventController.searchByDateRange("Date1", "Date2");   //Just for test, these don't really matter

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
