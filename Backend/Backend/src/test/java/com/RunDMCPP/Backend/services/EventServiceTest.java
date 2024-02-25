package com.RunDMCPP.Backend.services;

import com.RunDMCPP.Backend.enums.ErrorEnum;
import com.RunDMCPP.Backend.models.Event;
import com.RunDMCPP.Backend.models.Sermon;
import com.RunDMCPP.Backend.repositories.EventRepository;
import com.RunDMCPP.Backend.testData.TestEventData;
import com.RunDMCPP.Backend.testData.TestSermonData;
import com.RunDMCPP.Backend.utils.BackendErrorException;
import com.RunDMCPP.Backend.validation.EventValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventValidator eventValidator;

    @Test
    public void findById_Success(){
        Mockito.when(eventRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(TestEventData.event1()));

        Optional<Event> result;
        try {
            result = eventService.findById("1");
        } catch (BackendErrorException e) {
            throw new RuntimeException(e);
        }

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().equals(TestEventData.event1())).isTrue();
    }

    @Test
    public void findById_NoResult(){
        Mockito.when(eventRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.empty());

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            eventService.findById("1");
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.NOT_FOUND);

    }

    @Test
    public void createEvent_Success() throws BackendErrorException {
        Mockito.when(eventValidator.createValidator(ArgumentMatchers.any(Event.class))).thenReturn(true);
        Mockito.when(eventRepository.save(ArgumentMatchers.any(Event.class))).thenReturn(TestEventData.event1());

        Event result = eventService.createEvent(TestEventData.event1());

        assertThat(result).isNotNull();
        assertThat(result.equals(TestEventData.event1())).isTrue();

    }
    @Test
    public void createEvent_TransactionFail(){
        Mockito.when(eventValidator.createValidator(ArgumentMatchers.any(Event.class))).thenReturn(true);
        Mockito.when(eventRepository.save(ArgumentMatchers.any(Event.class))).thenThrow(OptimisticLockingFailureException.class);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            eventService.createEvent(TestEventData.event1());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.TRANSACTION_FAIL);

    }
    @Test
    public void createEvent_InvalidInput(){
        Mockito.when(eventValidator.createValidator(ArgumentMatchers.any(Event.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            eventService.createEvent(TestEventData.event1());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.INVALID_INPUT);
    }

    @Test
    public void updateEvent_Success() throws BackendErrorException {
        Event input = TestEventData.event2();
        input.setId("1");

        Mockito.when(eventValidator.readValidator(ArgumentMatchers.any(Event.class))).thenReturn(true);
        Mockito.when(eventValidator.updateValidator(any(Event.class), any(Event.class))).thenReturn(true);
        Mockito.when(eventRepository.findById(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(TestEventData.event1()));
        Mockito.when(eventRepository.save(ArgumentMatchers.any(Event.class))).thenReturn(input);

        Event result = eventService.updateEvent(input);

        assertThat(result).isNotNull();
        assertThat(result.equals(TestEventData.event1_2Data())).isTrue();

    }
    @Test
    public void updateEvent_InvalidInput(){
        Mockito.when(eventValidator.readValidator(ArgumentMatchers.any(Event.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            eventService.updateEvent(TestEventData.event2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.INVALID_INPUT);
    }
    @Test
    public void updateEvent_DataMismatch(){
        Mockito.when(eventValidator.readValidator(ArgumentMatchers.any(Event.class))).thenReturn(true);
        Mockito.when(eventRepository.findById(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(TestEventData.event1()));
        Mockito.when(eventValidator.updateValidator(any(Event.class), any(Event.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            eventService.updateEvent(TestEventData.event2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.DATA_MISMATCH);

    }
    @Test
    public void updateEvent_TransactionFail(){
        Mockito.when(eventValidator.readValidator(ArgumentMatchers.any(Event.class))).thenReturn(true);
        Mockito.when(eventValidator.updateValidator(any(Event.class), any(Event.class))).thenReturn(true);
        Mockito.when(eventRepository.findById(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(TestEventData.event1()));
        Mockito.when(eventRepository.save(ArgumentMatchers.any(Event.class))).thenThrow(OptimisticLockingFailureException.class);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            eventService.updateEvent(TestEventData.event2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.TRANSACTION_FAIL);

    }
    @Test
    public void updateEvent_NotFound(){
        Mockito.when(eventValidator.readValidator(ArgumentMatchers.any(Event.class))).thenReturn(true);
        Mockito.when(eventRepository.findById(ArgumentMatchers.any(String.class))).thenReturn(Optional.empty());

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            eventService.updateEvent(TestEventData.event2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.NOT_FOUND);
    }

    @Test
    public void deleteEvent_Success() throws BackendErrorException {
        Mockito.when(eventValidator.readValidator(any(Event.class))).thenReturn(true);
        Mockito.when(eventValidator.deleteValidator(any(Event.class), any(Event.class))).thenReturn(true);
        Mockito.when(eventRepository.findById(any(String.class))).thenReturn(Optional.of(TestEventData.event1()));
//        Mockito.when(eventRepository.deleteById(any(String.class))).thenReturn(true);

        eventService.deleteEvent(TestEventData.event1());

        assertThat(true).isTrue(); //assertion to make sure that deleteEvent() didn't throw an error
    }

    @Test
    public void deleteEvent_InvalidInput(){
        Mockito.when(eventValidator.readValidator(ArgumentMatchers.any(Event.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            eventService.deleteEvent(TestEventData.event2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.INVALID_INPUT);
    }
    @Test
    public void deleteEvent_TransactionFail(){
        when(eventValidator.readValidator(any(Event.class))).thenReturn(true);
        when(eventRepository.findById(any(String.class))).thenReturn(Optional.of(TestEventData.event1()));
        when(eventValidator.deleteValidator(any(Event.class), any(Event.class))).thenReturn(true);
        doThrow(new IllegalArgumentException()).when(eventRepository).deleteById(any(String.class));

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            eventService.deleteEvent(TestEventData.event2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.TRANSACTION_FAIL);

    }
    @Test
    public void deleteEvent_DataMismatch(){
        when(eventValidator.readValidator(any(Event.class))).thenReturn(true);
        when(eventRepository.findById(any(String.class))).thenReturn(Optional.of(TestEventData.event1()));
        when(eventValidator.deleteValidator(any(Event.class), any(Event.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            eventService.deleteEvent(TestEventData.event2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.DATA_MISMATCH);

    }
    @Test
    public void deleteEvent_NotFound(){
        when(eventValidator.readValidator(any(Event.class))).thenReturn(true);
        when(eventRepository.findById(any(String.class))).thenReturn(Optional.empty());

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            eventService.deleteEvent(TestEventData.event2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.NOT_FOUND);
    }

    /* TODO: Complete when autoDeleteEvents is reworked

    @Test
    public void autoDeleteEvents_Success() throws BackendErrorException{ }

    @Test
    public void autoDeleteEvents_Failure_TransactionFail(){ }

    @Test
    public void autoDeleteEvents_Failure_EventMissingDate(){ }

    @Test
    public void autoDeleteEvents_Failure_NoResults(){ }     //Do we even fail the transaction for this? If there is nothing to clear it did it's job.


    @Test
    public void autoDeleteEvents_Failure_OtherStuff(){ }

    */

}
