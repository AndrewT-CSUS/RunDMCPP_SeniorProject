package com.RunDMCPP.Backend.controllers;

import com.RunDMCPP.Backend.models.Sermon;
import com.RunDMCPP.Backend.services.SermonService;
import com.RunDMCPP.Backend.testData.TestExceptionData;
import com.RunDMCPP.Backend.testData.TestSermonData;
import com.RunDMCPP.Backend.utils.BackendErrorException;
import com.RunDMCPP.Backend.utils.BackendErrorResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SermonControllerTest {

    @InjectMocks
    private SermonController sermonController;
    @Mock
    private SermonService sermonService;

    @Test
    public void getSermonById_Success() throws BackendErrorException {
        when(sermonService.findById(any(String.class))).thenReturn(Optional.of(TestSermonData.sermon1()));

        ResponseEntity result = sermonController.getSermonByID("1");
        Sermon sermon = ((Optional<Sermon>) result.getBody()).get();

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(sermon.equals(TestSermonData.sermon1())).isTrue();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getSermonById_Failure() throws BackendErrorException {
        when(sermonService.findById(any(String.class))).thenThrow(TestExceptionData.backendError_NotFound());

        ResponseEntity result = sermonController.getSermonByID("1");

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void addSermon_Success() throws BackendErrorException {
        when(sermonService.createSermon(any(Sermon.class))).thenReturn(TestSermonData.sermon1());

        ResponseEntity result = sermonController.addSermon(TestSermonData.sermon1());

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().equals(TestSermonData.sermon1())).isTrue();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void addSermon_Failure() throws BackendErrorException {
        when(sermonService.createSermon(any(Sermon.class))).thenThrow(TestExceptionData.backendError_InvalidInput());

        ResponseEntity result = sermonController.addSermon(TestSermonData.sermon1());

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(3);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void updateSermon_Success() throws BackendErrorException {
        when(sermonService.editSermon(any(Sermon.class))).thenReturn(TestSermonData.sermon1());

        ResponseEntity result = sermonController.updateSermon(TestSermonData.sermon1());

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().equals(TestSermonData.sermon1())).isTrue();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void updateSermon_Failure() throws BackendErrorException {
        when(sermonService.editSermon(any(Sermon.class))).thenThrow(TestExceptionData.backendError_InvalidInput());

        ResponseEntity result = sermonController.updateSermon(TestSermonData.sermon1());

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(3);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void deleteSermon_Success(){
        ResponseEntity result = sermonController.deleteSermon(TestSermonData.sermon1());

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deleteSermon_Failure() throws BackendErrorException {
        doThrow(TestExceptionData.backendError_InvalidInput()).when(sermonService).deleteSermon(any(Sermon.class));

        ResponseEntity result = sermonController.deleteSermon(TestSermonData.sermon1());

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(3);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void searchByTitle_Success() throws BackendErrorException {
        when(sermonService.searchSermonsByTitle(any(String.class))).thenReturn(TestSermonData.listOfSermons());

        ResponseEntity result = sermonController.searchByTitle("Test");
        List<Sermon> resultList =  (List<Sermon>)result.getBody();

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(resultList!=null).isTrue();
        assertThat(resultList.contains(TestSermonData.sermon1())).isTrue();
        assertThat(resultList.contains(TestSermonData.sermon2())).isTrue();
    }

    @Test
    public void searchByTitle_Failure() throws BackendErrorException {
        when(sermonService.searchSermonsByTitle(any(String.class))).thenThrow(TestExceptionData.backendError_NotFound());

        ResponseEntity result = sermonController.searchByTitle("Test");

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


    @Test
    public void searchByDateRange_Success() throws BackendErrorException {
        when(sermonService.searchSermonsByDateRange(any(String.class), any(String.class))).thenReturn(TestSermonData.listOfSermons());

        ResponseEntity result = sermonController.searchByDateRange("Date1", "Date2");   //Just for test, these don't really matter
        List<Sermon> resultList =  (List<Sermon>)result.getBody();

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(resultList!=null).isTrue();
        assertThat(resultList.contains(TestSermonData.sermon1())).isTrue();
        assertThat(resultList.contains(TestSermonData.sermon2())).isTrue();
    }

    @Test
    public void searchByDateRange_Failure() throws BackendErrorException {
        when(sermonService.searchSermonsByDateRange(any(String.class), any(String.class))).thenThrow(TestExceptionData.backendError_NotFound());

        ResponseEntity result = sermonController.searchByDateRange("Date1", "Date2");   //Just for test, these don't really matter

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
