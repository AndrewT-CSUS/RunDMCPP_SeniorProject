package com.RunDMCPP.Backend.validation;

import com.RunDMCPP.Backend.models.Event;
import com.RunDMCPP.Backend.testData.TestEventData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EventValidatorTest {
    
    @InjectMocks
    private EventValidator eventValidator;

    @Test
    public void readValidator_Success(){
        boolean result = eventValidator.readValidator(TestEventData.event1());

        assertThat(result).isTrue();
    }
    @Test
    public void readValidator_Fail(){
        Event input = new Event();
        boolean result = eventValidator.readValidator(input);

        assertThat(result).isFalse();
    }

    @Test
    public void createValidator_Success(){
        Event input = TestEventData.event1();
        input.setId(null);
        boolean result = eventValidator.createValidator(input);

        assertThat(result).isTrue();
    }

    @Test
    public void createValidator_Fail_HasId(){
        Event input = TestEventData.event1();

        boolean result = eventValidator.createValidator(input);

        assertThat(result).isFalse();
    }
    @Test
    public void createValidator_Fail_NoTitle(){
        Event input = TestEventData.event1();
        input.setName(null);

        boolean result = eventValidator.createValidator(input);

        assertThat(result).isFalse();
    }
    @Test
    public void createValidator_Fail_NoDescription(){
        Event input = TestEventData.event1();
        input.setEventDescription(null);

        boolean result = eventValidator.createValidator(input);

        assertThat(result).isFalse();
    }
    @Test
    public void createValidator_Fail_NoLocation(){
        Event input = TestEventData.event1();
        input.setEventLocation(null);

        boolean result = eventValidator.createValidator(input);

        assertThat(result).isFalse();
    }

    @Test
    public void createValidator_Fail_NoDateTime(){
        Event input = TestEventData.event1();
        input.setDateTime(null);

        boolean result = eventValidator.createValidator(input);

        assertThat(result).isFalse();
    }

    @Test
    public void createValidator_Fail_MissingAllButId(){
        Event input = new Event();
        input.setId("1");

        boolean result = eventValidator.createValidator(input);

        assertThat(result).isFalse();

    }

    @Test
    public void updateValidator_Success(){
        boolean result = eventValidator.updateValidator(TestEventData.event1(), TestEventData.event1());

        assertThat(result).isTrue();
    }

    @Test
    public void updateValidator_Fail_NoId(){
        Event input = TestEventData.event1();
        input.setId(null);
        boolean result = eventValidator.updateValidator(input, TestEventData.event1());

        assertThat(result).isFalse();
    }


    @Test
    public void updateValidator_Fail_DataMismatch(){
        boolean result = eventValidator.updateValidator(TestEventData.event1(), TestEventData.event2());

        assertThat(result).isFalse();
    }

    @Test
    public void updateValidator_Success_CheckOnlyId(){
        boolean result = eventValidator.updateValidator(TestEventData.event1(), TestEventData.event1_2Data());

        assertThat(result).isTrue();
    }


    @Test
    public void deleteValidator_Success(){
        boolean result = eventValidator.deleteValidator(TestEventData.event1(), TestEventData.event1());

        assertThat(result).isTrue();
    }

    @Test
    public void deleteValidator_Fail_NoId(){
        Event event = TestEventData.event1();
        event.setId(null);
        boolean result = eventValidator.deleteValidator(event, TestEventData.event2());

        assertThat(result).isFalse();
    }


    @Test
    public void deleteValidator_Fail_NoTitle(){
        Event event = TestEventData.event1();
        event.setName(null);
        boolean result = eventValidator.deleteValidator(event, TestEventData.event1());

        assertThat(result).isFalse();
    }
    @Test
    public void deleteValidator_Fail_NoDesc(){
        Event event = TestEventData.event1();
        event.setEventDescription(null);
        boolean result = eventValidator.deleteValidator(event, TestEventData.event1());

        assertThat(result).isFalse();
    }

    @Test
    public void deleteValidator_Fail_NoDateTime(){
        Event event = TestEventData.event1();
        event.setDateTime(null);
        boolean result = eventValidator.deleteValidator(event, TestEventData.event1());

        assertThat(result).isFalse();
    }

    @Test
    public void deleteValidator_Fail_NoLocation(){
        Event event = TestEventData.event1();
        event.setEventLocation(null);
        boolean result = eventValidator.deleteValidator(event, TestEventData.event1());

        assertThat(result).isFalse();
    }

    @Test
    public void deleteValidator_Fail_MissingAllButId(){
        Event event = new Event();
        event.setId("1");
        boolean result = eventValidator.deleteValidator(event, TestEventData.event1());

        assertThat(result).isFalse();
    }

    @Test
    public void deleteValidator_Fail_MismatchId(){
        Event event = TestEventData.event1();
        event.setId("100");
        boolean result = eventValidator.deleteValidator(event, TestEventData.event1());

        assertThat(result).isFalse();
    }
    @Test
    public void deleteValidator_Fail_MismatchTitle(){
        Event event = TestEventData.event1();
        event.setName("Error");
        boolean result = eventValidator.deleteValidator(event, TestEventData.event1());

        assertThat(result).isFalse();
    }
    @Test
    public void deleteValidator_Fail_MismatchDesc(){
        Event event = TestEventData.event1();
        event.setEventDescription("Error");
        boolean result = eventValidator.deleteValidator(event, TestEventData.event1());

        assertThat(result).isFalse();
    }
    @Test
    public void deleteValidator_Fail_MismatchDateTime(){
        Event event = TestEventData.event1();
        event.setDateTime("Error");
        boolean result = eventValidator.deleteValidator(event, TestEventData.event1());

        assertThat(result).isFalse();
    }
    @Test
    public void deleteValidator_Fail_MismatchLocation(){
        Event event = TestEventData.event1();
        event.setEventLocation("Error");
        boolean result = eventValidator.deleteValidator(event, TestEventData.event1());

        assertThat(result).isFalse();
    }

    @Test
    public void deleteValidator_Fail_MismatchAllButId(){
        boolean result = eventValidator.deleteValidator(TestEventData.event1(), TestEventData.event1_2Data());

        assertThat(result).isFalse();
    }
}
