package com.harmony2.app.dao;

import java.util.concurrent.CompletableFuture;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDate;
import com.harmony2.app.model.DispositionRecord;

/**
 * DAO interface for Employee to perform CRUD operation.
 * @version 1.0
 * @since Feb 02, 2018
 */
@EnableAsync
@EnableCaching
public interface DispositionRecordAsyncDAO {
	
	@Async
	public CompletableFuture<Void> upsertCustomerOfferDispositionCountByDate(DispositionRecord dispositionRec, LocalDate date);
	
	@Async
	public CompletableFuture<Void> updateLastDateOfCustomerOfferDisposition(DispositionRecord dispositionRec, LocalDate date);
   
	@Async
	public CompletableFuture<Void> upsertCustomerOfferCountByDate(DispositionRecord dispositionRec, LocalDate date);
	
	@Async
	public CompletableFuture<Void> upsertTargetingHistoryData(DispositionRecord dispositionRec, LocalDate date);
	
}
