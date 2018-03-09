package com.harmony2.app.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.harmony2.app.model.DispositionRecord;

@Repository
@Configuration
public interface DispositionRecordRepository extends CassandraRepository<DispositionRecord, String> {
	@Query("SELECT*FROM customer_disposition WHERE ssoid=?0")
    List<DispositionRecord> findAllBySsoid(String ssoid);
	
	@Query("update customer_offer_count_by_date set dispositioncount = dispositioncount+1 where ssoid=?0 and offername=?1 and dispositiontype=?2 and dispositiondate=?3")
	void insertCustomerOfferCountByDate(String ssoid, String offername, String dispositiontype, LocalDate localDate );
}