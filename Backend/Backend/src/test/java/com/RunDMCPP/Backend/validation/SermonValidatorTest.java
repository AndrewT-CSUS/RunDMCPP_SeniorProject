package com.RunDMCPP.Backend.validation;

import com.RunDMCPP.Backend.models.Sermon;
import com.RunDMCPP.Backend.testData.TestSermonData;
import com.RunDMCPP.Backend.validation.SermonValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SermonValidatorTest {

    @InjectMocks
    private SermonValidator sermonValidator;

    @Test
    public void readValidator_Success(){
        boolean result = sermonValidator.readValidator(TestSermonData.sermon1());

        assertThat(result).isTrue();
    }
    @Test
    public void readValidator_Failure(){
        Sermon input = new Sermon();
        boolean result = sermonValidator.readValidator(input);

        assertThat(result).isFalse();
    }

    @Test
    public void createValidator_Success(){
        Sermon input = TestSermonData.sermon1();
        input.setId(null);
        boolean result = sermonValidator.createValidator(input);

        assertThat(result).isTrue();
    }
    @Test
    public void createValidator_Fail_NoName(){
        Sermon input = TestSermonData.sermon1();
        input.setName(null);
        boolean result = sermonValidator.createValidator(input);

        assertThat(result).isFalse();
    }
    @Test
    public void createValidator_Fail_NoDesc(){
        Sermon input =TestSermonData.sermon1();
        input.setDescription(null);
        boolean result = sermonValidator.createValidator(input);

        assertThat(result).isFalse();
    }
    @Test
    public void createValidator_Fail_NoLink(){
        Sermon input =TestSermonData.sermon1();
        input.setYoutubeLink(null);
        boolean result = sermonValidator.createValidator(input);

        assertThat(result).isFalse();
    }
    @Test
    public void createValidator_Fail_NoDate(){
        Sermon input =TestSermonData.sermon1();
        input.setDateTime(null);
        boolean result = sermonValidator.createValidator(input);

        assertThat(result).isFalse();
    }
    @Test
    public void createValidator_Fail_HasId(){
        Sermon input =TestSermonData.sermon1();
        boolean result = sermonValidator.createValidator(input);

        assertThat(result).isFalse();
    }

    @Test
    public void createValidator_Fail_MissingAll(){
        Sermon input = new Sermon();
        boolean result = sermonValidator.createValidator(input);

        assertThat(result).isFalse();
    }

    @Test
    public void updateValidator_Success_AllSame(){
        boolean result = sermonValidator.updateValidator(TestSermonData.sermon1(), TestSermonData.sermon1());

        assertThat(result).isTrue();
    }
    @Test
    public void updateValidator_Success_OnlySameId(){
        boolean result = sermonValidator.updateValidator(TestSermonData.sermon1(), TestSermonData.sermon1_2Data());

        assertThat(result).isTrue();
    }
    @Test
    public void updateValidator_Fail(){
        boolean result = sermonValidator.updateValidator(TestSermonData.sermon1(), TestSermonData.sermon2());

        assertThat(result).isFalse();
    }

    @Test
    public void deleteValidator_Success(){
        boolean result = sermonValidator.deleteValidator(TestSermonData.sermon1(), TestSermonData.sermon1());

        assertThat(result).isTrue();
    }
@Test
    public void deleteValidator_Fail_MissingId(){
        Sermon input = TestSermonData.sermon1();
        input.setId(null);
        boolean result = sermonValidator.deleteValidator(input, TestSermonData.sermon1());

        assertThat(result).isFalse();
    }
    @Test
    public void deleteValidator_Fail_MissingName(){
        Sermon input = TestSermonData.sermon1();
        input.setName(null);
        boolean result = sermonValidator.deleteValidator(input, TestSermonData.sermon1());

        assertThat(result).isFalse();
    }
    @Test
    public void deleteValidator_Fail_MissingDesc(){
        Sermon input = TestSermonData.sermon1();
        input.setDescription(null);
        boolean result = sermonValidator.deleteValidator(input, TestSermonData.sermon1());

        assertThat(result).isFalse();
    }
    @Test
    public void deleteValidator_Fail_MissingLink(){
        Sermon input = TestSermonData.sermon1();
        input.setYoutubeLink(null);
        boolean result = sermonValidator.deleteValidator(input, TestSermonData.sermon1());

        assertThat(result).isFalse();
    }
    @Test
    public void deleteValidator_Fail_MissingDateTime(){
        Sermon input = TestSermonData.sermon1();
        input.setDateTime(null);
        boolean result = sermonValidator.deleteValidator(input, TestSermonData.sermon1());

        assertThat(result).isFalse();
    }
    @Test
    public void deleteValidator_Fail_MismatchData(){
        boolean result = sermonValidator.deleteValidator(TestSermonData.sermon2(), TestSermonData.sermon1());

        assertThat(result).isFalse();
    }


}
