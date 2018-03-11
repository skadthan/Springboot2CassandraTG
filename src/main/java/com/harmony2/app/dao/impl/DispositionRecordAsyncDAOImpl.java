package com.harmony2.app.dao.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.harmony2.app.dao.DispositionRecordAsyncDAO;
import com.harmony2.app.model.DispositionRecord;
import com.harmony2.app.util.CassandraUtil;

@Repository
@ComponentScan("com.harmony2")
public class DispositionRecordAsyncDAOImpl implements DispositionRecordAsyncDAO{

	@Autowired
	private CassandraUtil cassandraUtil;
	
	@Override
	@Async
	public CompletableFuture<Void> upsertCustomerOfferDispositionCountByDate(DispositionRecord dispositionRec, LocalDate date) {
		
		String vcql = "update customer_offer_disposition_count_by_date set dispositioncount = dispositioncount+1 where ssoid='"+dispositionRec.getSsoid()+"' and offername='"+dispositionRec.getOffername()+"' and dispositiontype='"+dispositionRec.getDisptype()+"' and dispositiondate='"+date+"'";
		System.out.println("vCQL: "+vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		return CompletableFuture.completedFuture(null);
		
	
		
	}

	@Override
	public CompletableFuture<Void> updateLastDateOfCustomerOfferDisposition(DispositionRecord dispositionRec, LocalDate date) {
		
		String vcql = "update customer_offer_disposition_lastupdate set dispositiondate='"+date+"' where ssoid='"+dispositionRec.getSsoid()+"' and offername='"+dispositionRec.getOffername()+"' and dispositiontype='"+dispositionRec.getDisptype()+"'";
		System.out.println("vCQL: "+vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		return CompletableFuture.completedFuture(null);
	}

	@Override
	public CompletableFuture<Void> upsertCustomerOfferCountByDate(DispositionRecord dispositionRec, LocalDate date) {
		
		String vcql = "update customer_offer_count_by_date set dispositioncount = dispositioncount+1 where ssoid='"+dispositionRec.getSsoid()+"' and offername='"+dispositionRec.getOffername()+"' and dispositiondate='"+date+"'";
		System.out.println("vCQL: "+vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		return CompletableFuture.completedFuture(null);
	}

	@Override
	public CompletableFuture<Void> upsertTargetingHistoryData(DispositionRecord dispositionRec, LocalDate date) {
		String dispType;
		
		switch (dispositionRec.getDisptype()) {
		case "Clicked":  dispType = "lastclickedate";
                 break;
        case "Viewed":  dispType = "lastvieweddate";
                 break;
        case "Converted":  dispType = "lastconvertedate";
                 break;
        case "Accepted":  dispType = "lastacceptedate";
                 break;
        case "Declined":  dispType = "lastdeclineddate";
                 break;
        case "Liked":  dispType = "lastlikeddate";
                 break;
        case "Disliked":  dispType = "lastdislikedate";
                 break;
        case "Postponed":  dispType = "lastpostponeddate";
                 break;
        default: dispType = null;
                 break;
    }
		String vcql = "update customer_targetting_history set "+dispType+" = '"+date+"' where ssoid='"+dispositionRec.getSsoid()+"' and offername='"+dispositionRec.getOffername()+"'";
		System.out.println("vCQL: "+vcql);
		cassandraUtil.getCassandraAsynCqlOperations().execute(vcql);
		return CompletableFuture.completedFuture(null);
	}

}
