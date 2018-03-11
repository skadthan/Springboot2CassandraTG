package com.harmony2.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony2.app.dao.TargetingHistoryDAO;
import com.harmony2.app.model.TargetingHistory;
import com.harmony2.app.service.TargetingHistoryService;

@Service
public class TargetingHistoryServiceImpl implements TargetingHistoryService {

    @Autowired  
    private TargetingHistoryDAO targetingHistoryDAO;
    
	@Override
	public List<TargetingHistory> getTargetingHistoryData(String ssoid) {
		
		return targetingHistoryDAO.getTargetingHistoryData(ssoid);
	}

}
