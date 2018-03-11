package com.harmony2.app.dao.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import com.harmony2.app.dao.DispositionRecordAsyncDAO;
import com.harmony2.app.dao.DispositionRecordDAO;
import com.harmony2.app.dao.DispositionRecordRepository;
import com.harmony2.app.model.DispositionRecord;
import com.harmony2.app.util.CassandraUtil;

/**
 * DAOImpl class for Employee to perform CRUD operation.
 * @version 1.0
 * @since Feb 02, 2018
 */
@Repository
@ComponentScan("com.harmony2") 
public class DispositionRecordDAOImpl implements DispositionRecordDAO {
	    
	@Autowired
	private CassandraUtil cassandraUtil;
    
    @Autowired
    private DispositionRecordRepository dispositionRecordRepository;
    
    @Autowired
    private DispositionRecordAsyncDAO dispositionRecordAsyncDAO;
    

	@Override
	public DispositionRecord createDispositionRecord(DispositionRecord dispositionRec) {
		
		LocalDateTime dispoDateTime = LocalDateTime.ofInstant(dispositionRec.getRecordtime(), ZoneId.of(ZoneOffset.UTC.getId()));
		LocalDate dispoDate = dispoDateTime.toLocalDate();
		System.out.println("ssoid: "+dispositionRec.getSsoid()+" OfferName: "+dispositionRec.getOffername()+" Disptype: "+dispositionRec.getDisptype()+" Recordtime: "+dispoDate+"");
		
		
		long start = System.currentTimeMillis();
		dispositionRecordAsyncDAO.upsertCustomerOfferCountByDate(dispositionRec, dispoDate);
		dispositionRecordAsyncDAO.updateLastDateOfCustomerOfferDisposition(dispositionRec, dispoDate);
		dispositionRecordAsyncDAO.upsertTargetingHistoryData(dispositionRec, dispoDate);
		dispositionRecordAsyncDAO.upsertCustomerOfferDispositionCountByDate(dispositionRec, dispoDate);
		System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
		
		cassandraUtil.getCassandraOperations().insert(dispositionRec);
		
		
		return dispositionRec;
	}


	@Override
	public List<DispositionRecord> getDispositionRecord(String ssoid) {
		return dispositionRecordRepository.findAllBySsoid(ssoid);
	}

}

