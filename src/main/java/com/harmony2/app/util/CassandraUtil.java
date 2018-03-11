package com.harmony2.app.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.cql.AsyncCqlOperations;
import org.springframework.data.cassandra.core.cql.AsyncCqlTemplate;
import org.springframework.data.cassandra.core.AsyncCassandraOperations;
import org.springframework.data.cassandra.core.AsyncCassandraTemplate;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.LoadBalancingPolicy;
import com.datastax.driver.dse.DseCluster;
import com.datastax.driver.dse.DseSession;

/**
 * Utility class for getting the CassandraOperations object.
 * @version 1.0
 * @since Feb 02, 2018
 */

@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })
public class CassandraUtil {

    /**
     * Constant String for Keyspace
     */
    private static final String KEYSPACE = "cassandra.keyspace";
    /**
     * Constant String for ContactPoints
     */
    private static final String CONTACTPOINTS = "cassandra.contactpoints";
    /**
     * Constant String for Port 
     */
    private static final String PORT = "cassandra.port";
    
    @Autowired
    private Environment environment;

    public CassandraUtil() {
        System.out.println("CassandraUtil()");
    }
    
    private String getKeyspaceName() {
        return environment.getProperty(KEYSPACE);       
    }
    
    private String getContactPoints() {
        return environment
                .getProperty(CONTACTPOINTS);        
    }
    
    private int getPortNumber() {
        return Integer.parseInt(environment
                .getProperty(PORT));        
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(getContactPoints());
        cluster.setPort(getPortNumber());
        return cluster;
    }

    @Bean
    public CassandraMappingContext mappingContext() {
        return new CassandraMappingContext();
    }

    @Bean
    public CassandraConverter converter() {
        return new MappingCassandraConverter(mappingContext());
    }

    @Bean
    public CassandraSessionFactoryBean session() throws Exception {
        CassandraSessionFactoryBean cassandraSessionFactoryBean = new CassandraSessionFactoryBean();
        cassandraSessionFactoryBean.setCluster(cluster().getObject());
        cassandraSessionFactoryBean.setKeyspaceName(getKeyspaceName());
        cassandraSessionFactoryBean.setConverter(converter());
        cassandraSessionFactoryBean.setSchemaAction(SchemaAction.NONE);
        return cassandraSessionFactoryBean;
    }

    @Bean
    public CassandraOperations cassandraTemplate() throws Exception {
        return new CassandraTemplate(session().getObject());
    }
    
    @Bean
    public Session getPoolSession(){
        PoolingOptions poolingOptions = new PoolingOptions();
        poolingOptions
        .setCoreConnectionsPerHost(HostDistance.LOCAL,  4)
        .setMaxConnectionsPerHost( HostDistance.LOCAL, 10)
        .setMaxRequestsPerConnection(HostDistance.LOCAL, 32768)
        .setMaxRequestsPerConnection(HostDistance.REMOTE, 2000)
        .setHeartbeatIntervalSeconds(120);

        Cluster cluster = Cluster.builder()
            .addContactPoints(this.getContactPoints())
            .withPoolingOptions(poolingOptions)
            .build();

        Session session = cluster.connect(this.getKeyspaceName());
        return session;
        }
    
    
    @Bean
    public Cluster getCluster(){
        PoolingOptions poolingOptions = new PoolingOptions();
        poolingOptions
        .setCoreConnectionsPerHost(HostDistance.LOCAL,  4)
        .setMaxConnectionsPerHost( HostDistance.LOCAL, 10)
        .setMaxRequestsPerConnection(HostDistance.LOCAL, 32768)
        .setMaxRequestsPerConnection(HostDistance.REMOTE, 2000)
        .setHeartbeatIntervalSeconds(120);

        Cluster cluster = Cluster.builder()
            .addContactPoints(this.getContactPoints())
            .withPoolingOptions(poolingOptions)
            .build();
        
        return cluster;
        
        }
    @Bean
    public String getCassandraVersion() {
    	DseCluster dseCluster=null;
    	String version=null;

    	try{
    		dseCluster=DseCluster.builder().addContactPoint(this.getContactPoints()).build();
    		DseSession dseSession=dseCluster.connect();
    		Row row=dseSession.execute("select release_version from system.local").one();
    		//System.out.println("release_version--> "+row.getString("release_version"));
    		version=row.getString("release_version");
    		}
    	finally
    		{
    		if(dseCluster!=null)
    		dseCluster.close();
    		}
    	return version;
    }
    
    
	@PostConstruct
	@Bean
	public CassandraOperations getCassandraOperations(){
		Session session = this.getPoolSession();
		CassandraOperations template = new CassandraTemplate(session);
		return template;
	}
	
	@PostConstruct
	@Bean
	public AsyncCassandraOperations getCassandraAsyncOperations(){
		Session session = this.getPoolSession();
		AsyncCassandraOperations template = new AsyncCassandraTemplate(session);
		return template;
	}
	
	@PostConstruct
	@Bean
	public AsyncCqlOperations getCassandraAsynCqlOperations(){
		Session session = this.getPoolSession();
		AsyncCqlOperations template = new AsyncCqlTemplate(session);
		return template;
	}
	
	public void monitorCassandra() {
		
		Cluster cluster = this.getCluster();
		
		final LoadBalancingPolicy loadBalancingPolicy =
			     cluster.getConfiguration().getPolicies().getLoadBalancingPolicy();
			final PoolingOptions poolingOptions =
			cluster.getConfiguration().getPoolingOptions();

			ScheduledExecutorService scheduled =
			Executors.newScheduledThreadPool(1);
			scheduled.scheduleAtFixedRate(new Runnable() {
			    @Override
			    public void run() {
			        Session.State state = getPoolSession().getState();
			        for (Host host : state.getConnectedHosts()) {
			            HostDistance distance = loadBalancingPolicy.distance(host);
			            int connections = state.getOpenConnections(host);
			            int inFlightQueries = state.getInFlightQueries(host);
			            System.out.printf("%s connections=%d, current load=%d, maxload=%d%n",
			                host, connections, inFlightQueries,
			                connections *
			poolingOptions.getMaxRequestsPerConnection(distance));
			        }
			    }
			}, 5, 5, TimeUnit.SECONDS);
	}
	
}