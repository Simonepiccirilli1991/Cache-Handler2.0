package com.seor0.cache.config;

import org.springframework.stereotype.Component;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.seor0.cache.model.SessionUtente;

@Component
public class CacheClientSession {

	public static final String SESSIONS = "session";
	  private final HazelcastInstance hazelcastInstance 
	     = Hazelcast.newHazelcastInstance(createConfig());

	  public Config createConfig() {
	    Config config = new Config();
	    config.addMapConfig(mapConfig());
	    return config;
	  }

	  private MapConfig mapConfig() {
	    MapConfig mapConfig = new MapConfig(SESSIONS);
	    mapConfig.setTimeToLiveSeconds(360);
	    mapConfig.setMaxIdleSeconds(360);
	    return mapConfig;
	  }
	  
	  public SessionUtente put(String bt, SessionUtente session){
		    IMap<String, SessionUtente> map = hazelcastInstance.getMap(SESSIONS);
		    return map.putIfAbsent(bt, session);
		  }

		  public SessionUtente get(String bt){
		    IMap<String, SessionUtente> map = hazelcastInstance.getMap(SESSIONS);
		    return map.get(bt);
		  }
		  
		  public SessionUtente insert(String bt, SessionUtente request){
			    IMap<String, SessionUtente> map = hazelcastInstance.getMap(SESSIONS);
			    map.put(bt, request);
			    return request;
			  }
}
