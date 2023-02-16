package com.app.harmony2.app;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.harmony2.app.TargetingHistoryApplication;

import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;


@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes={TargetingHistoryApplication.class})
@AutoConfigureMockMvc
public class TargetingHistoryApplicationTest {

	@Test
    public void contextLoads() {
    }
	
   void testApplication() {
        MockedStatic<SpringApplication> utilities = Mockito.mockStatic(SpringApplication.class);
        utilities.when((MockedStatic.Verification) SpringApplication.run(WebsiteApplication.class, new String[]{})).thenReturn(null);
        TargetingHistoryApplication.main(new String[]{});
        assertThat(SpringApplication.run(TargetingHistoryApplication.class, new String[]{})).isEqualTo(null);
    }
}

