package com.RunDMCPP.Backend.services;

import com.RunDMCPP.Backend.enums.ErrorEnum;
import com.RunDMCPP.Backend.models.Announcement;
import com.RunDMCPP.Backend.repositories.AnnouncementRepository;
import com.RunDMCPP.Backend.testData.TestAnnouncementData;
import com.RunDMCPP.Backend.utils.BackendErrorException;
import com.RunDMCPP.Backend.validation.AnnouncementValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AnnouncementServiceTest {
    @InjectMocks
    private AnnouncementService announcementService;

    @Mock
    private AnnouncementRepository announcementRepository;

    @Mock
    private AnnouncementValidator announcementValidator;

    @Test
    public void getAnnouncement_Success() throws BackendErrorException {
        when(announcementRepository.findById(any(String.class))).thenReturn(Optional.of(TestAnnouncementData.announcement1()));

        Optional<Announcement> result = announcementService.getAnnouncement("1");

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().equals(TestAnnouncementData.announcement1())).isTrue();
    }

    @Test
    public void getAnnouncement_NoResult(){
        when(announcementRepository.findById(any(String.class))).thenReturn(Optional.empty());

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            announcementService.getAnnouncement("1");
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.NOT_FOUND);
    }

    @Test
    public void createAnnouncement_Success() throws BackendErrorException {
        when(announcementValidator.createValidator(any(Announcement.class))).thenReturn(true);
        when(announcementRepository.save(any(Announcement.class))).thenReturn(TestAnnouncementData.announcement1());

        Announcement announcement = announcementService.createAnnouncement(TestAnnouncementData.announcement1());

        assertThat(announcement).isNotNull();
        assertThat(announcement.equals(TestAnnouncementData.announcement1())).isTrue();

    }

    @Test
    public void createAnnouncement_Fail_TransactionFail(){
        when(announcementValidator.createValidator(any(Announcement.class))).thenReturn(true);
        when(announcementRepository.save(any(Announcement.class))).thenThrow(OptimisticLockingFailureException.class);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            announcementService.createAnnouncement(TestAnnouncementData.announcement1());
        });


        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.TRANSACTION_FAIL);

    }
    @Test
    public void createAnnouncement_Fail_InvalidInput(){
        when(announcementValidator.createValidator(any(Announcement.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            announcementService.createAnnouncement(TestAnnouncementData.announcement1());
        });


        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.INVALID_INPUT);


    }

    @Test
    public void updateAnnouncement_Success() throws BackendErrorException {
        when(announcementValidator.readValidator(any(Announcement.class))).thenReturn(true);
        when(announcementRepository.findById(any(String.class))).thenReturn(Optional.of(TestAnnouncementData.announcement1()));
        when(announcementValidator.updateValidator(any(Announcement.class), any(Announcement.class))).thenReturn(true);
        when(announcementRepository.save(any(Announcement.class))).thenReturn(TestAnnouncementData.announcement1());

        Announcement announcement = announcementService.updateAnnouncement(TestAnnouncementData.announcement1());

        assertThat(announcement).isNotNull();
        assertThat(announcement.equals(TestAnnouncementData.announcement1())).isNotNull();
    }

    @Test
    public void updateAnnouncement_Fail_TransactionFail(){
        when(announcementValidator.readValidator(any(Announcement.class))).thenReturn(true);
        when(announcementRepository.findById(any(String.class))).thenReturn(Optional.of(TestAnnouncementData.announcement1()));
        when(announcementValidator.updateValidator(any(Announcement.class), any(Announcement.class))).thenReturn(true);
        when(announcementRepository.save(any(Announcement.class))).thenThrow(OptimisticLockingFailureException.class);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            announcementService.updateAnnouncement(TestAnnouncementData.announcement1());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.TRANSACTION_FAIL);
    }

    @Test
    public void updateAnnouncement_Fail_InvalidInput(){

        when(announcementValidator.readValidator(any(Announcement.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            announcementService.updateAnnouncement(TestAnnouncementData.announcement1());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.INVALID_INPUT);
    }

    @Test
    public void updateAnnouncement_Fail_DataMismatch(){

        when(announcementValidator.readValidator(any(Announcement.class))).thenReturn(true);
        when(announcementRepository.findById(any(String.class))).thenReturn(Optional.of(TestAnnouncementData.announcement1()));
        when(announcementValidator.updateValidator(any(Announcement.class), any(Announcement.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            announcementService.updateAnnouncement(TestAnnouncementData.announcement1());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.DATA_MISMATCH);
    }
    @Test
    public void updateAnnouncement_Fail_NotFound(){
        when(announcementValidator.readValidator(any(Announcement.class))).thenReturn(true);
        when(announcementRepository.findById(any(String.class))).thenReturn(Optional.empty());

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            announcementService.updateAnnouncement(TestAnnouncementData.announcement1());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.NOT_FOUND);

    }

    @Test
    public void deleteAnnouncement_Success() throws BackendErrorException{
        when(announcementValidator.readValidator(any(Announcement.class))).thenReturn(true);
        when(announcementRepository.findById(any(String.class))).thenReturn(Optional.of(TestAnnouncementData.announcement1()));
        when(announcementValidator.deleteValidator(any(Announcement.class), any(Announcement.class))).thenReturn(true);

        announcementService.deleteAnnouncement(TestAnnouncementData.announcement1());
        assertThat(true).isTrue(); //assertion to make sure that deleteAnnouncement() didn't throw an error
    }

    @Test
    public void deleteAnnouncement_Fail_InvalidInput(){
        when(announcementValidator.readValidator(any(Announcement.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            announcementService.deleteAnnouncement(TestAnnouncementData.announcement1());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.INVALID_INPUT);
    }
    @Test
    public void deleteAnnouncement_Fail_DataMismatch(){
        when(announcementValidator.readValidator(any(Announcement.class))).thenReturn(true);
        when(announcementRepository.findById(any(String.class))).thenReturn(Optional.of(TestAnnouncementData.announcement1()));

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            announcementService.deleteAnnouncement(TestAnnouncementData.announcement2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.DATA_MISMATCH);
    }
    @Test
    public void deleteAnnouncement_Fail_NotFound(){
        when(announcementValidator.readValidator(any(Announcement.class))).thenReturn(true);
        when(announcementRepository.findById(any(String.class))).thenReturn(Optional.empty());

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            announcementService.deleteAnnouncement(TestAnnouncementData.announcement1());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.NOT_FOUND);

    }

    @Test
    public void searchAnnouncementByTitle_Success() throws BackendErrorException {
        when(announcementRepository.findByTitleContaining(any(String.class))).thenReturn(TestAnnouncementData.listOfAnnouncements());

        List<Announcement> announcements = announcementService.searchAnnouncementsByTitle("Test");

        assertThat(announcements).isNotNull();
        assertThat(announcements.isEmpty()).isFalse();
        assertThat(announcements.contains(TestAnnouncementData.announcement1())).isTrue();
        assertThat(announcements.contains(TestAnnouncementData.announcement2())).isTrue();
        assertThat(announcements.contains(TestAnnouncementData.announcement3())).isTrue();
    }

    @Test
    public void searchAnnouncementByTitle_Failure_NoResults(){
        when(announcementRepository.findByTitleContaining(any(String.class))).thenReturn(Collections.emptyList());

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            announcementService.searchAnnouncementsByTitle("Test");
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);  //TODO: UPDATE!
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.NOT_FOUND);
    }

    @Test
    public void getThreeRecentAnnouncements_Success_MoreThanThree(){
        when(announcementRepository.findAll()).thenReturn(TestAnnouncementData.longListOfAnnouncements());

        List<Announcement> announcements = announcementService.getThreeRecentAnnouncements();

        assertThat(announcements).isNotNull();
        assertThat(announcements.isEmpty()).isFalse();
        assertThat(announcements.size()).isEqualTo(3);

        assertThat(announcements.contains(TestAnnouncementData.announcement5())).isTrue();
        assertThat(announcements.contains(TestAnnouncementData.announcement4())).isTrue();
        assertThat(announcements.contains(TestAnnouncementData.announcement3())).isTrue();
        assertThat(announcements.contains(TestAnnouncementData.announcement2())).isFalse();
        assertThat(announcements.contains(TestAnnouncementData.announcement1())).isFalse();
    }

    @Test
    public void getThreeRecentAnnouncements_Success_LessThanThree(){
        when(announcementRepository.findAll()).thenReturn(TestAnnouncementData.listOfOneAnnouncement());

        List<Announcement> announcements = announcementService.getThreeRecentAnnouncements();

        assertThat(announcements).isNotNull();
        assertThat(announcements.isEmpty()).isFalse();
        assertThat(announcements.size()).isEqualTo(1);

        assertThat(announcements.contains(TestAnnouncementData.announcement1())).isTrue();
    }

    @Test
    public void getThreeRecentAnnouncements_NoResults(){
        when(announcementRepository.findAll()).thenReturn(Collections.emptyList());

        List<Announcement> announcements = announcementService.getThreeRecentAnnouncements();

        assertThat(announcements).isNotNull();
        assertThat(announcements.isEmpty()).isTrue();
    }

}
