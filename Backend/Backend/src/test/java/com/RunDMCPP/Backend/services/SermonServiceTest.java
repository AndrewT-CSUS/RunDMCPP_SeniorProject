package com.RunDMCPP.Backend.services;

import com.RunDMCPP.Backend.enums.ErrorEnum;
import com.RunDMCPP.Backend.models.Sermon;
import com.RunDMCPP.Backend.repositories.SermonRepository;
import com.RunDMCPP.Backend.testData.TestSermonData;
import com.RunDMCPP.Backend.utils.BackendErrorException;
import com.RunDMCPP.Backend.validation.SermonValidator;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SermonServiceTest {

    @InjectMocks
    private SermonService sermonService;

    @Mock
    private SermonRepository sermonRepository;

    @Mock
    private SermonValidator sermonValidator;

    @BeforeEach
    public void setUp(){
    }

//    @Test
//    public void quickTest(){
//        Sermon testData = TestSermonData.sermon1();
//
//        assertThat(testData.getId()).isNotNull();
//    }

    @Test
    public void findById_Success(){
        Mockito.when(sermonRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(TestSermonData.sermon1()));

        Optional<Sermon> result;
        try {
            result = sermonService.findById("1");
        } catch (BackendErrorException e) {
            throw new RuntimeException(e);
        }

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().equals(TestSermonData.sermon1())).isTrue();
    }

    @Test
    public void findById_NoResult(){
        Mockito.when(sermonRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.empty());

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.findById("1");
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.NOT_FOUND);

    }

    @Test
    public void createSermon_Success() throws BackendErrorException {
        Mockito.when(sermonValidator.createValidator(ArgumentMatchers.any(Sermon.class))).thenReturn(true);
        Mockito.when(sermonRepository.save(ArgumentMatchers.any(Sermon.class))).thenReturn(TestSermonData.sermon1());

        Sermon result = sermonService.createSermon(TestSermonData.sermon1());

        assertThat(result).isNotNull();
        assertThat(result.equals(TestSermonData.sermon1())).isTrue();

    }
    @Test
    public void createSermon_TransactionFail(){
        Mockito.when(sermonValidator.createValidator(ArgumentMatchers.any(Sermon.class))).thenReturn(true);
        Mockito.when(sermonRepository.save(ArgumentMatchers.any(Sermon.class))).thenThrow(OptimisticLockingFailureException.class);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.createSermon(TestSermonData.sermon1());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.TRANSACTION_FAIL);

    }
    @Test
    public void createSermon_InvalidInput(){
        Mockito.when(sermonValidator.createValidator(ArgumentMatchers.any(Sermon.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.createSermon(TestSermonData.sermon1());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.INVALID_INPUT);
    }

    @Test
    public void editSermon_Success() throws BackendErrorException {
        Sermon input = TestSermonData.sermon2();
        input.setId("1");

        Mockito.when(sermonValidator.readValidator(ArgumentMatchers.any(Sermon.class))).thenReturn(true);
        Mockito.when(sermonValidator.updateValidator(any(Sermon.class), any(Sermon.class))).thenReturn(true);
        Mockito.when(sermonRepository.findById(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(TestSermonData.sermon1()));
        Mockito.when(sermonRepository.save(ArgumentMatchers.any(Sermon.class))).thenReturn(input);

        Sermon result = sermonService.editSermon(input);

        assertThat(result).isNotNull();
        assertThat(result.equals(TestSermonData.sermon1_2Data())).isTrue();

    }
    @Test
    public void editSermon_InvalidInput(){
        Mockito.when(sermonValidator.readValidator(ArgumentMatchers.any(Sermon.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.editSermon(TestSermonData.sermon2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.INVALID_INPUT);
    }
    @Test
    public void editSermon_DataMismatch(){
        Mockito.when(sermonValidator.readValidator(ArgumentMatchers.any(Sermon.class))).thenReturn(true);
        Mockito.when(sermonRepository.findById(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(TestSermonData.sermon1()));
        Mockito.when(sermonValidator.updateValidator(any(Sermon.class), any(Sermon.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.editSermon(TestSermonData.sermon2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.DATA_MISMATCH);

    }
    @Test
    public void editSermon_TransactionFail(){
        Mockito.when(sermonValidator.readValidator(ArgumentMatchers.any(Sermon.class))).thenReturn(true);
        Mockito.when(sermonValidator.updateValidator(any(Sermon.class), any(Sermon.class))).thenReturn(true);
        Mockito.when(sermonRepository.findById(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(TestSermonData.sermon1()));
        Mockito.when(sermonRepository.save(ArgumentMatchers.any(Sermon.class))).thenThrow(OptimisticLockingFailureException.class);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.editSermon(TestSermonData.sermon2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.TRANSACTION_FAIL);

    }
    @Test
    public void editSermon_NotFound(){
        Mockito.when(sermonValidator.readValidator(ArgumentMatchers.any(Sermon.class))).thenReturn(true);
        Mockito.when(sermonRepository.findById(ArgumentMatchers.any(String.class))).thenReturn(Optional.empty());

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.editSermon(TestSermonData.sermon2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.NOT_FOUND);
    }

    @Test
    public void deleteSermon_Success() throws BackendErrorException {
        Mockito.when(sermonValidator.readValidator(any(Sermon.class))).thenReturn(true);
        Mockito.when(sermonValidator.deleteValidator(any(Sermon.class), any(Sermon.class))).thenReturn(true);
        Mockito.when(sermonRepository.findById(any(String.class))).thenReturn(Optional.of(TestSermonData.sermon1()));
//        Mockito.when(sermonRepository.deleteById(any(String.class))).thenReturn(true);

        sermonService.deleteSermon(TestSermonData.sermon1());

        assertThat(true).isTrue(); //assertion to make sure that deleteSermon() didn't throw an error
    }

    @Test
    public void deleteSermon_InvalidInput(){
        Mockito.when(sermonValidator.readValidator(ArgumentMatchers.any(Sermon.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.deleteSermon(TestSermonData.sermon2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.INVALID_INPUT);
    }
    @Test
    public void deleteSermon_TransactionFail(){
        when(sermonValidator.readValidator(any(Sermon.class))).thenReturn(true);
        when(sermonRepository.findById(any(String.class))).thenReturn(Optional.of(TestSermonData.sermon1()));
        when(sermonValidator.deleteValidator(any(Sermon.class), any(Sermon.class))).thenReturn(true);
        doThrow(new IllegalArgumentException()).when(sermonRepository).deleteById(any(String.class));

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.deleteSermon(TestSermonData.sermon2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.TRANSACTION_FAIL);

    }
    @Test
    public void deleteSermon_DataMismatch(){
        when(sermonValidator.readValidator(any(Sermon.class))).thenReturn(true);
        when(sermonRepository.findById(any(String.class))).thenReturn(Optional.of(TestSermonData.sermon1()));
        when(sermonValidator.deleteValidator(any(Sermon.class), any(Sermon.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.deleteSermon(TestSermonData.sermon2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.DATA_MISMATCH);

    }
    @Test
    public void deleteSermon_NotFound(){

        when(sermonValidator.readValidator(any(Sermon.class))).thenReturn(true);
        when(sermonRepository.findById(any(String.class))).thenReturn(Optional.empty());

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.deleteSermon(TestSermonData.sermon2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.NOT_FOUND);
    }

    @Test
    public void searchSermonsByTitle_Success() throws BackendErrorException {
       when(sermonRepository.findByNameContaining(any(String.class))).thenReturn(TestSermonData.listOfSermons());

        List<Sermon> result = sermonService.searchSermonsByTitle("Test");

        assertThat(result).isNotNull();
        assertThat(result.isEmpty()).isFalse();
        assertThat(result.contains(TestSermonData.sermon1())).isTrue();
    }

    @Test
    public void searchSermonsByTitle_NoResults(){
        when(sermonRepository.findByNameContaining(any(String.class))).thenReturn(new ArrayList<>());

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.searchSermonsByTitle("Test");
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.NOT_FOUND);
    }

}
