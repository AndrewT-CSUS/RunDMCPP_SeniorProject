package com.RunDMCPP.Backend.controllers;

import com.RunDMCPP.Backend.models.Announcement;
import com.RunDMCPP.Backend.services.AnnouncementService;
import com.RunDMCPP.Backend.testData.TestAnnouncementData;
import com.RunDMCPP.Backend.testData.TestExceptionData;
import com.RunDMCPP.Backend.utils.BackendErrorException;
import com.RunDMCPP.Backend.utils.BackendErrorResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.Collections;
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
public class AnnouncementControllerTest {
    @InjectMocks
    private AnnouncementController announcementController;

    @Mock
    private AnnouncementService announcementService;

    @Test
    public void getAnnouncementById_Success() throws BackendErrorException{
        when(announcementService.getAnnouncement(any(String.class))).thenReturn(Optional.of(TestAnnouncementData.announcement1()));

        ResponseEntity result = announcementController.get("1");
        Announcement announcement = ((Optional<Announcement>) result.getBody()).get();


        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(announcement.equals(TestAnnouncementData.announcement1())).isTrue();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getAnnouncementById_Failure() throws BackendErrorException {
        when(announcementService.getAnnouncement(any(String.class))).thenThrow(TestExceptionData.backendError_NotFound());

        ResponseEntity result = announcementController.get("1");

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void addAnnouncement_Success() throws BackendErrorException{
        when(announcementService.createAnnouncement(any(Announcement.class))).thenReturn(TestAnnouncementData.announcement1());

        ResponseEntity result = announcementController.create(TestAnnouncementData.announcement1());
        Announcement announcement = (Announcement) result.getBody();


        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(announcement.equals(TestAnnouncementData.announcement1())).isTrue();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void addAnnouncement_Failure() throws BackendErrorException{
        when(announcementService.createAnnouncement(any(Announcement.class))).thenThrow(TestExceptionData.backendError_InvalidInput());

        ResponseEntity result = announcementController.create(TestAnnouncementData.announcement1());


        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(3);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void updateAnnouncement_Success() throws BackendErrorException{
        when(announcementService.updateAnnouncement(any(Announcement.class))).thenReturn(TestAnnouncementData.announcement1());

        ResponseEntity result = announcementController.edit(TestAnnouncementData.announcement1());
        Announcement announcement = (Announcement) result.getBody();


        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(announcement.equals(TestAnnouncementData.announcement1())).isTrue();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void updateAnnouncement_Failure() throws BackendErrorException{
        when(announcementService.updateAnnouncement(any(Announcement.class))).thenThrow(TestExceptionData.backendError_InvalidInput());

        ResponseEntity result = announcementController.edit(TestAnnouncementData.announcement1());


        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(3);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void deleteAnnouncement_Success(){
        ResponseEntity result = announcementController.delete(TestAnnouncementData.announcement1());

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deleteAnnouncement_Failure() throws BackendErrorException{
        doThrow(TestExceptionData.backendError_InvalidInput()).when(announcementService).deleteAnnouncement(any(Announcement.class));

        ResponseEntity result = announcementController.delete(TestAnnouncementData.announcement1());


        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(3);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void searchByTitle_Success() throws BackendErrorException {
        when(announcementService.searchAnnouncementsByTitle(any(String.class))).thenReturn(TestAnnouncementData.listOfAnnouncements());

        ResponseEntity result = announcementController.searchByTitle("Test");
        List<Announcement> announcements =  (List<Announcement>)result.getBody();

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(announcements.contains(TestAnnouncementData.announcement1())).isTrue();
        assertThat(announcements.contains(TestAnnouncementData.announcement2())).isTrue();
        assertThat(announcements.contains(TestAnnouncementData.announcement3())).isTrue();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void searchByTitle_Failure() throws BackendErrorException {
        when(announcementService.searchAnnouncementsByTitle(any(String.class))).thenThrow(TestExceptionData.backendError_NotFound());

        ResponseEntity result = announcementController.searchByTitle("Test");

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(((BackendErrorResponse) result.getBody()).getCode()).isEqualTo(1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }



    @Test
    public void getThreeRecent_Success(){
        when(announcementService.getThreeRecentAnnouncements()).thenReturn(TestAnnouncementData.listOfAnnouncements());

        ResponseEntity<List<Announcement>> result = announcementController.getThreeRecent();
        List<Announcement> announcements = result.getBody();

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(announcements.contains(TestAnnouncementData.announcement1())).isTrue();
        assertThat(announcements.contains(TestAnnouncementData.announcement2())).isTrue();
        assertThat(announcements.contains(TestAnnouncementData.announcement3())).isTrue();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getThreeRecent_Failure(){
        when(announcementService.getThreeRecentAnnouncements()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Announcement>> result = announcementController.getThreeRecent();
        List<Announcement> announcements = result.getBody();

        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(announcements.isEmpty()).isTrue();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
