package com.harmony2.app.dao.impl;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
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
public class DispositionDAOImpl implements DispositionRecordDAO {
	    
	@Autowired
	private CassandraUtil cassandraUtil;
    
    @Autowired
    private DispositionRecordRepository dispositionRecordRepository;
    

	@Override
	public DispositionRecord createDispositionRecord(DispositionRecord dispositionRec) {
		
		LocalDateTime dispoDateTime = LocalDateTime.ofInstant(dispositionRec.getRecordtime(), ZoneId.of(ZoneOffset.UTC.getId()));
		 LocalDate dispoDate = dispoDateTime.toLocalDate();
		 long start = System.currentTimeMillis();
		
		 System.out.println("ssoid: "+dispositionRec.getSsoid()+" OfferName: "+dispositionRec.getOffername()+" Disptype: "+dispositionRec.getDisptype()+" Recordtime: "+dispoDate+"");
		
		//dispositionRecordRepository.insertCustomerOfferCountByDate(dispositionRec.getSsoid(), dispositionRec.getOffername(), dispositionRec.getDisptype(), dispoDate);
		System.out.println("Elapsed time1: " + (System.currentTimeMillis() - start));
		

		String vcql = "update customer_offer_count_by_date set dispositioncount = dispositioncount+1 where ssoid='"+dispositionRec.getSsoid()+"' and offername='"+dispositionRec.getOffername()+"' and dispositiontype='"+dispositionRec.getDisptype()+"' and dispositiondate='"+dispoDate+"'";
		System.out.println("vCQL: "+vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		
		cassandraUtil.getCassandraOperations().insert(dispositionRec);
		return dispositionRec;
	}


	@Override
	public List<DispositionRecord> getDispositionRecord(String ssoid) {
		// TODO Auto-generated method stub
		//return cassandraDAOTemplate.findById(ssoid,DispositionRecord.class);
		return dispositionRecordRepository.findAllBySsoid(ssoid);
	}

}

