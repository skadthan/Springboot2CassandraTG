package com.harmony2.app.dao.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.harmony2.app.dao.TargetingHistoryDAO;
import com.harmony2.app.model.TargetingHistory;
import com.harmony2.app.util.CassandraUtil;

import com.datastax.driver.core.ColumnDefinitions.Definition;


@Repository
@ComponentScan("com.harmony2") 
public class TargetingHistoryDAOImpl implements  TargetingHistoryDAO {

	@Autowired
	private CassandraUtil cassandraUtil;
	private PreparedStatement GET_TARGETING_HISTORY_DATA=null;
	
	  @PostConstruct
	    private void initStatements(){
		  Session session = cassandraUtil.getPoolSession();
			
	        if (session == null){
	            System.out.println("Cassandra 3.1.1 not available");
	        } else {
	        	GET_TARGETING_HISTORY_DATA = session.prepare("select * from customer_targetting_history where ssoid=?");
	        }

	    }       
	
	@Override
	public List<TargetingHistory> getTargetingHistoryData(String ssoid) {
		Session session = cassandraUtil.getPoolSession();
		List<TargetingHistory> tgHistoryList = new ArrayList<>();
		BoundStatement bound = GET_TARGETING_HISTORY_DATA.bind(ssoid);
        ResultSet results =session.execute(bound);
  
        Iterator<Row> it = results.iterator();
        while (it.hasNext()) {
            Row row = it.next();

          tgHistoryList.add(mapTgHistoryPojo(row));
            
        }

		return tgHistoryList;
}

	public TargetingHistory mapTgHistoryPojo(Row row) {
		
		TargetingHistory tgHistory = new TargetingHistory();
		String ssoid = null;
		String offername= null;
		int lastaccepteddays=3650;
		int lastclikedays=3650;
		int lastconverteddays=3650;
		int lastdeclineddays=3650;
		int lastdislikeddays=3650;
		int lastlikeddays=3650;
		int lastpostponeddays=3650;
		int lastvieweddays=3650;
		
		 for (Definition columnDefinition : row.getColumnDefinitions()) {
       
		switch (columnDefinition.getName().toUpperCase()) {
		case "SSOID":
			 ssoid = (String) readColumnValue(row,columnDefinition.getName());
		     continue;
		case "OFFERNAME":
			offername= ((String) readColumnValue(row,columnDefinition.getName()));
			continue;
		case "LASTACCEPTEDATE":
			lastaccepteddays=getDiffDays(readColumnValue(row,columnDefinition.getName()));
			continue;
		case "LASTCLICKEDATE":
			lastclikedays=getDiffDays(readColumnValue(row,columnDefinition.getName()));
			continue;
		case "LASTCONVERTEDATE":
			lastconverteddays=getDiffDays(readColumnValue(row,columnDefinition.getName()));
			continue;
		case "LASTDECLINEDDATE":
			lastdeclineddays=getDiffDays(readColumnValue(row,columnDefinition.getName()));
			continue;
		case "LASTDISLIKEDATE":
			lastdislikeddays=getDiffDays(readColumnValue(row,columnDefinition.getName()));
			continue;
		default:
			System.out.println("Unable to map the POJO for TargetingHistory");
			continue;
		case "LASTLIKEDDATE":
			lastlikeddays=getDiffDays(readColumnValue(row,columnDefinition.getName()));
			continue;
		case "LASTPOSTPONEDDATE":
			lastpostponeddays=getDiffDays(readColumnValue(row,columnDefinition.getName()));
			continue;
		case "LASTVIEWEDDATE":
			lastvieweddays=getDiffDays(readColumnValue(row,columnDefinition.getName()));
			continue;
		}

}
		 tgHistory.setSsoid(ssoid);
		 tgHistory.setOffername(offername);
		 tgHistory.setNoofdayssincelastcommunicationaccepted(lastaccepteddays);
		 tgHistory.setNoofdayssincelastcommunicationclicked(lastclikedays);
		 tgHistory.setNoofdayssincelastcommunicationconverted(lastconverteddays);
		 tgHistory.setNoofdayssincelastcommunicationdeclined(lastdeclineddays);
		 tgHistory.setNoofdayssincelastcommunicationdisliked(lastdislikeddays);
		 tgHistory.setNoofdayssincelastcommunicationliked(lastlikeddays);
		 tgHistory.setNoofdayssincelastcommunicationpostponed(lastpostponeddays);
		 tgHistory.setNoofdayssincelastcommunicationviewed(lastvieweddays);
		return tgHistory;
		
	}

	public int getDiffDays(Object date) {
		int days;
		
		if (date != null ) {
			String strDate = date.toString();
			LocalDate parsedDate = LocalDate.parse(strDate);
             Duration duration = Duration.between(parsedDate.atStartOfDay(),LocalDate.now().atStartOfDay() );
			 days = (int) duration.toDays();
			
		}	
		else {
			days = 3650;
		}
		return days;
	}

	public Object readColumnValue(Row row, String colname) {
		
		ColumnDefinitions cdef = row.getColumnDefinitions();
		String type = cdef.getType(colname).getName().toString().toUpperCase();
		switch (type) {
		case "BIGINT":
			return row.getLong(colname);
		case "BOOLEAN":
			return row.getBool(colname);
		case "BLOB":
			return row.getBytes(colname);
		case "DOUBLE":
			return row.getDouble(colname);
		case "INT":
			return row.getInt(colname);
		case "TIMESTAMP":
			return row.getTimestamp(colname);
		case "DATE":
			return row.getDate(colname);
		default:
			System.out.println("Invalid Column type: columname="+colname+", columntype="+type);
		case "VARCHAR":
			return row.getString(colname);
		}
	}
}