package com.app.harmony2.app.restcontroller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.harmony2.app.TargetingHistoryApplication;
import com.harmony2.app.model.DispositionRecord;
import com.harmony2.app.service.DispositionRecordService;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import java.time.Clock;
//import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes={TargetingHistoryApplication.class})
@AutoConfigureMockMvc
public class DispositionControllerTest {

	@Autowired
    MockMvc mockMvc;

    @MockBean
    private DispositionRecordService DispositionService;
    
    
    @Test
    void getDispositionData() throws Exception {
    	
    	
		/*
		 * Clock c = Clock.systemUTC(); Duration d = Duration.ofHours(5); Clock clock =
		 * Clock.offset(c, d); System.out.println(clock.instant());
		 * Instant timestamp = clock.instant();
		 */
    	Instant timestamp = Instant.now();
    	String recordtime = timestamp.toString();
    	System.out.println(recordtime);

    	String ssoid = "2434bb6a-a92c-43e9-8d29-6c8d690ac7c2";
    	
    	DispositionRecord dispositionRecord = new DispositionRecord();
    	dispositionRecord.setSsoid(ssoid);
    	dispositionRecord.setDisptype("Loved");
    	dispositionRecord.setLayout("L1");
    	dispositionRecord.setOffername("AshuOffer1");
    	dispositionRecord.setPlacement("AccountMessage");
    	dispositionRecord.setRecordtime(timestamp);
    	dispositionRecord.setStyle("S1");
    
    	List<DispositionRecord> dispositioRecords = new ArrayList<DispositionRecord>();
    	dispositioRecords.add(dispositionRecord);
    	when(DispositionService.getDispositionRecord(ssoid)).thenReturn(dispositioRecords);

        mockMvc.perform(MockMvcRequestBuilders.post("/getdispositiondata")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"ssoid\": \"2434bb6a-a92c-43e9-8d29-6c8d690ac7c2\"}") 
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$[0].ssoid").value("2434bb6a-a92c-43e9-8d29-6c8d690ac7c2"))
        .andExpect(jsonPath("$[0].offername").value("AshuOffer1"))
        .andExpect(jsonPath("$[0].placement").value("AccountMessage"))
        .andExpect(jsonPath("$[0].layout").value("L1"))
        .andExpect(jsonPath("$[0].style").value("S1"))
        .andExpect(jsonPath("$[0].disptype").value("Loved"))
        .andExpect(jsonPath("$[0].recordtime").value(recordtime))
        .andDo(print());
    }
    

    @Test
    void postDispositionData() throws Exception {
    	
    	Instant timestamp = Instant.now();
    	String recordtime = timestamp.toString();
    	System.out.println(recordtime);

    	String ssoid = "2434bb6a-a92c-43e9-8d29-6c8d690ac7c2";
    	
    	DispositionRecord dispositionRecord = new DispositionRecord();
    	dispositionRecord.setSsoid(ssoid);
    	dispositionRecord.setDisptype("Loved");
    	dispositionRecord.setLayout("L1");
    	dispositionRecord.setOffername("AshuOffer2");
    	dispositionRecord.setPlacement("AccountMessage");
    	dispositionRecord.setRecordtime(timestamp);
    	dispositionRecord.setStyle("S1");
    	
    	String jsonString = "{ \"ssoid\": \"2434bb6a-a92c-43e9-8d29-6c8d690ac7c2\", \"offername\": \"AshuOffer2\",\"disptype\": \"Loved\", \"layout\": \"L1\", \"placement\":\"AccountMessage\",\"style\":\"S1\"}";
    	
    	//when(DispositionService.createDispositionRecord(dispositionRecord)).thenReturn(dispositionRecord);
        when(DispositionService.createDispositionRecord(Mockito.any(DispositionRecord.class))).thenReturn(dispositionRecord);


        mockMvc.perform(MockMvcRequestBuilders.post("/postdisposition")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonString) 
                .accept(MediaType.APPLICATION_JSON)
        )
        		  .andExpect(status().isOk())
				  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				  .andExpect(jsonPath("$.ssoid").value("2434bb6a-a92c-43e9-8d29-6c8d690ac7c2"))
				  .andExpect(jsonPath("$.offername").value("AshuOffer2"))
				  .andExpect(jsonPath("$.placement").value("AccountMessage"))
				  .andExpect(jsonPath("$.layout").value("L1"))
				  .andExpect(jsonPath("$.style").value("S1"))
				  .andExpect(jsonPath("$.disptype").value("Loved"))
				  .andExpect(jsonPath("$.recordtime").value(recordtime))
				 
        .andDo(print());
    }
}
