package com.harmony2.app.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.harmony2.app.model.DispositionRecord;
import com.harmony2.app.model.TargetingHistory;
import com.harmony2.app.service.DispositionRecordService;
import com.harmony2.app.service.TargetingHistoryService;


/**
 * @author Suresh Kadthan
 * @version 1.0
 * @since Feb 02, 2018
 */
@RestController
public class TargetingHistoryController {
    
    @Autowired
    private TargetingHistoryService targetingHistoryService;
    
    public TargetingHistoryController() {
        System.out.println("TargetingHistoryController()");
    }
   
    @RequestMapping(value = "/gettargetinghistorydata", method = RequestMethod.POST)
    public List<TargetingHistory> findBySsoId(@RequestBody DispositionRecord dispositionRec) {        
        return targetingHistoryService.getTargetingHistoryData(dispositionRec.getSsoid());
    }
 
}