package com.harmony2.app.dao;

import java.util.List;

import com.harmony2.app.model.TargetingHistory;

public interface TargetingHistoryDAO {

	
	public List<TargetingHistory> getTargetingHistoryData(String ssoid);
}
