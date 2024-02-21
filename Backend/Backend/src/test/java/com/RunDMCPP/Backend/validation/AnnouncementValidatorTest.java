package com.RunDMCPP.Backend.validation;

import com.RunDMCPP.Backend.models.Announcement;
import com.RunDMCPP.Backend.testData.TestAnnouncementData;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AnnouncementValidatorTest {

    @InjectMocks
    private AnnouncementValidator announcementValidator;

    @Test
    public void readValidator_Success(){
        boolean result = announcementValidator.readValidator(TestAnnouncementData.announcement1());

        assertThat(result).isTrue();
    }
    @Test
    public void readValidator_Fail(){
        Announcement input = new Announcement();
        boolean result = announcementValidator.readValidator(input);

        assertThat(result).isFalse();
    }

    @Test
    public void createValidator_Success(){
       Announcement input = TestAnnouncementData.announcement1();
       input.setId(null);
       boolean result = announcementValidator.createValidator(input);

       assertThat(result).isTrue();
    }

    @Test
    public void createValidator_Fail_HasId(){
        Announcement input = TestAnnouncementData.announcement1();

        boolean result = announcementValidator.createValidator(input);

        assertThat(result).isFalse();
    }
    @Test
    public void createValidator_Fail_NoTitle(){
        Announcement input = TestAnnouncementData.announcement1();
        input.setTitle(null);

        boolean result = announcementValidator.createValidator(input);

        assertThat(result).isFalse();
    }
    @Test
    public void createValidator_Fail_NoDescription(){
        Announcement input = TestAnnouncementData.announcement1();
        input.setDescription(null);

        boolean result = announcementValidator.createValidator(input);

        assertThat(result).isFalse();
    }

    @Test
    public void updateValidator_Success(){
        boolean result = announcementValidator.updateValidator(TestAnnouncementData.announcement1(), TestAnnouncementData.announcement1());

        assertThat(result).isTrue();
    }

    @Test
    public void updateValidator_Fail_NoId(){
        Announcement input = TestAnnouncementData.announcement1();
        input.setId(null);
        boolean result = announcementValidator.updateValidator(input, TestAnnouncementData.announcement1());

        assertThat(result).isFalse();
    }


    @Test
    public void updateValidator_Fail_DataMismatch(){
        boolean result = announcementValidator.updateValidator(TestAnnouncementData.announcement1(), TestAnnouncementData.announcement2());

        assertThat(result).isFalse();
    }


    @Test
    public void deleteValidator_Success(){
        boolean result = announcementValidator.deleteValidator(TestAnnouncementData.announcement1(), TestAnnouncementData.announcement1());

        assertThat(result).isTrue();
    }

    @Test
    public void deleteValidator_Fail_NoId(){
        Announcement announcement = TestAnnouncementData.announcement1();
        announcement.setId(null);
        boolean result = announcementValidator.deleteValidator(announcement, TestAnnouncementData.announcement1());

        assertThat(result).isFalse();
    }


    @Test
    public void deleteValidator_Fail_NoTitle(){
        Announcement announcement = TestAnnouncementData.announcement1();
        announcement.setTitle(null);
        boolean result = announcementValidator.deleteValidator(announcement, TestAnnouncementData.announcement1());

        assertThat(result).isFalse();
    }
    @Test
    public void deleteValidator_Fail_NoDesc(){
        Announcement announcement = TestAnnouncementData.announcement1();
        announcement.setDescription(null);
        boolean result = announcementValidator.deleteValidator(announcement, TestAnnouncementData.announcement1());

        assertThat(result).isFalse();
    }
    @Test
    public void deleteValidator_Fail_MismatchId(){
        Announcement announcement = TestAnnouncementData.announcement1();
        announcement.setId("100");
        boolean result = announcementValidator.deleteValidator(announcement, TestAnnouncementData.announcement1());

        assertThat(result).isFalse();
    }
    @Test
    public void deleteValidator_Fail_MismatchTitle(){
        Announcement announcement = TestAnnouncementData.announcement1();
        announcement.setTitle("Error");
        boolean result = announcementValidator.deleteValidator(announcement, TestAnnouncementData.announcement1());

        assertThat(result).isFalse();
    }
    @Test
    public void deleteValidator_Fail_MismatchDesc(){
        Announcement announcement = TestAnnouncementData.announcement1();
        announcement.setDescription("Error");
        boolean result = announcementValidator.deleteValidator(announcement, TestAnnouncementData.announcement1());

        assertThat(result).isFalse();
    }
}
