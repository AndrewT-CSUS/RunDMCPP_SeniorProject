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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
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
        when(sermonRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(TestSermonData.sermon1()));

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
        when(sermonRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.empty());

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.findById("1");
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.NOT_FOUND);

    }

    @Test
    public void createSermon_Success() throws BackendErrorException {
        when(sermonValidator.createValidator(any(Sermon.class))).thenReturn(true);
        when(sermonRepository.save(any(Sermon.class))).thenReturn(TestSermonData.sermon1());

        Sermon result = sermonService.createSermon(TestSermonData.sermon1());

        assertThat(result).isNotNull();
        assertThat(result.equals(TestSermonData.sermon1())).isTrue();

    }
    @Test
    public void createSermon_TransactionFail(){
        when(sermonValidator.createValidator(any(Sermon.class))).thenReturn(true);
        when(sermonRepository.save(any(Sermon.class))).thenThrow(OptimisticLockingFailureException.class);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.createSermon(TestSermonData.sermon1());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.TRANSACTION_FAIL);

    }
    @Test
    public void createSermon_InvalidInput(){
        when(sermonValidator.createValidator(any(Sermon.class))).thenReturn(false);

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

        when(sermonValidator.readValidator(any(Sermon.class))).thenReturn(true);
        when(sermonValidator.updateValidator(any(Sermon.class), any(Sermon.class))).thenReturn(true);
        when(sermonRepository.findById(any(String.class))).thenReturn(Optional.of(TestSermonData.sermon1()));
        when(sermonRepository.save(any(Sermon.class))).thenReturn(input);

        Sermon result = sermonService.editSermon(input);

        assertThat(result).isNotNull();
        assertThat(result.equals(TestSermonData.sermon1_2Data())).isTrue();

    }
    @Test
    public void editSermon_InvalidInput(){
        when(sermonValidator.readValidator(any(Sermon.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.editSermon(TestSermonData.sermon2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.INVALID_INPUT);
    }
    @Test
    public void editSermon_DataMismatch(){
        when(sermonValidator.readValidator(any(Sermon.class))).thenReturn(true);
        when(sermonRepository.findById(any(String.class))).thenReturn(Optional.of(TestSermonData.sermon1()));
        when(sermonValidator.updateValidator(any(Sermon.class), any(Sermon.class))).thenReturn(false);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.editSermon(TestSermonData.sermon2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.DATA_MISMATCH);

    }
    @Test
    public void editSermon_TransactionFail(){
        when(sermonValidator.readValidator(any(Sermon.class))).thenReturn(true);
        when(sermonValidator.updateValidator(any(Sermon.class), any(Sermon.class))).thenReturn(true);
        when(sermonRepository.findById(any(String.class))).thenReturn(Optional.of(TestSermonData.sermon1()));
        when(sermonRepository.save(any(Sermon.class))).thenThrow(OptimisticLockingFailureException.class);

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.editSermon(TestSermonData.sermon2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.TRANSACTION_FAIL);

    }
    @Test
    public void editSermon_NotFound(){
        when(sermonValidator.readValidator(any(Sermon.class))).thenReturn(true);
        when(sermonRepository.findById(any(String.class))).thenReturn(Optional.empty());

        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            sermonService.editSermon(TestSermonData.sermon2());
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.NOT_FOUND);
    }

    @Test
    public void deleteSermon_Success() throws BackendErrorException {
        when(sermonValidator.readValidator(any(Sermon.class))).thenReturn(true);
        when(sermonValidator.deleteValidator(any(Sermon.class), any(Sermon.class))).thenReturn(true);
        when(sermonRepository.findById(any(String.class))).thenReturn(Optional.of(TestSermonData.sermon1()));
//        Mockito.when(sermonRepository.deleteById(any(String.class))).thenReturn(true);

        sermonService.deleteSermon(TestSermonData.sermon1());

        assertThat(true).isTrue(); //assertion to make sure that deleteSermon() didn't throw an error
    }

    @Test
    public void deleteSermon_InvalidInput(){
        when(sermonValidator.readValidator(any(Sermon.class))).thenReturn(false);

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
       when(sermonRepository.findByNameContaining(any(String.class))).thenReturn(TestSermonData.smallListOfSermons());

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

    @Test
    public void get10Recent_MoreThan10_Success() throws BackendErrorException{
        when(sermonRepository.findAll()).thenReturn(TestSermonData.largerListOfDisorderdSermons());

        List<Sermon> result = sermonService.get10Recent();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(10);

        assertThat(result.contains(TestSermonData.sermon3_UnparseableDate())).isEqualTo(false);
        assertThat(result.contains(TestSermonData.sermon1())).isEqualTo(false);
        assertThat(result.contains(TestSermonData.sermon11())).isEqualTo(true);

        //Dates should be ordered so that the oldest dates are at the end of the list. So if d1 is earlier than d2, that means the sort failed
        boolean inOrder = true;
        for(int i = 1; i < result.size(); i++){
            LocalDateTime d1 = LocalDateTime.parse(result.get(i - 1).getDateTime());
            LocalDateTime d2 = LocalDateTime.parse(result.get(i).getDateTime());
            if(d1.isBefore(d2)){
                inOrder = false;
                break;
            }
        }
        assertThat(inOrder).isTrue();

    }


    @Test
    public void get10Recent_LessThan10_Success() throws BackendErrorException{
        when(sermonRepository.findAll()).thenReturn(TestSermonData.middleListOfSermons());

        List<Sermon> result = sermonService.get10Recent();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(TestSermonData.middleListOfSermons().size());

        assertThat(result.contains(TestSermonData.sermon3_UnparseableDate())).isEqualTo(false);

        //Dates should be ordered so that the oldest dates are at the end of the list. So if d1 is earlier than d2, that means the sort failed
        boolean inOrder = true;
        for(int i = 1; i < result.size(); i++){
            LocalDateTime d1 = LocalDateTime.parse(result.get(i - 1).getDateTime());
            LocalDateTime d2 = LocalDateTime.parse(result.get(i).getDateTime());
            if(d1.isBefore(d2)){
                inOrder = false;
                break;
            }
        }
        assertThat(inOrder).isTrue();

    }

    @Test
    public void get10Recent_Exactly1_Success() throws BackendErrorException{
        when(sermonRepository.findAll()).thenReturn(TestSermonData.singleItemList());

        List<Sermon> result = sermonService.get10Recent();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(TestSermonData.singleItemList().size());

        assertThat(result.contains(TestSermonData.sermon3_UnparseableDate())).isEqualTo(false);
    }

    @Test
    public void get10Recent_Failure_NoResultsFound(){
        when(sermonRepository.findAll()).thenReturn(new ArrayList<>());


        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            List<Sermon> result = sermonService.get10Recent();
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.NOT_FOUND);
    }

    @Test
    public void get10Recent_Failure_NoParseableResults(){
        when(sermonRepository.findAll()).thenReturn(TestSermonData.singleItemList_Unparseable());


        BackendErrorException exception = assertThrows(BackendErrorException.class, () -> {
            List<Sermon> result = sermonService.get10Recent();
        });

        assertThat(exception).isNotNull();
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getErrorEnum()).isEqualTo(ErrorEnum.NOT_FOUND);
    }

}
