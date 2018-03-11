package com.harmony2.app;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.harmony2.app.util.CassandraUtil;

@Component
public class RunPostConstruct {
	

	  @Autowired
	  CassandraUtil cassandraUtil;
	  
	  @PostConstruct
	  public void init(){
		  cassandraUtil.monitorCassandra();
	  }


}
