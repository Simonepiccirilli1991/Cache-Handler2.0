package com.seor0.cache.config;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.seor0.cache.model.AppSession;

@Component
public class CacheClientAppSession {

	public static final String APPSESSION = "appsessions";
	  private final HazelcastInstance hazelcastInstance3 
	     = Hazelcast.newHazelcastInstance(createConfig3());

	  public Config createConfig3() {
	    Config config = new Config();
	    config.addMapConfig(mapConfig3());
	    return config;
	  }

	  private MapConfig mapConfig3() {
	    MapConfig mapConfig = new MapConfig(APPSESSION);
	    mapConfig.setTimeToLiveSeconds(360);
	    mapConfig.setMaxIdleSeconds(360);
	    return mapConfig;
	  }
	  
	  public AppSession put(String bt, AppSession session){
		    IMap<String, AppSession> map = hazelcastInstance3.getMap(APPSESSION);
		    return map.putIfAbsent(bt, session);
		  }

		  public AppSession get(String key){
		    IMap<String, AppSession> map = hazelcastInstance3.getMap(APPSESSION);
		    return map.get(key);
		    
//		    SessionUtente resp =  map.get(bt);
//		    // ogni volta che chiamo la get checko se sessione ancora valida max 5 min
//		    
//		    //LocalTime timeStart = LocalTime.parse(resp.getGeneTime());
//		    LocalTime timeStart = (StringUtils.isEmpty(resp.getUpdateTime())) ? LocalTime.parse(resp.getUpdateTime()) : LocalTime.parse(resp.getGeneTime());
//			LocalTime timeEnd = LocalTime.parse(new SimpleDateFormat("HH:mm:ss").format(new java.util.Date().getTime()));
//			// addo 5 minuti al tempo di start tempo massimo
//			System.out.println("Time start piu 5 min ="+ timeStart.plusMinutes(1) );
//			System.out.println("Time end ="+ timeEnd );
//			// check se tempo limite e edentro start +1 minuto
//			if(timeEnd.isAfter(timeStart.plusMinutes(5))) {
//				resp.setSessionActive(false); return resp;
//			}
//			
//			return resp;
		  }
		  
		  public AppSession insert(String key, AppSession request){
			    IMap<String, AppSession> map = hazelcastInstance3.getMap(APPSESSION);
			    map.put(key, request);
			    return request;
			  }
		  
		  public boolean exist(String bt){
			    IMap<String, AppSession> map = hazelcastInstance3.getMap(APPSESSION);
			    AppSession resp = new AppSession();
			    try {
			    	resp = map.get(bt);
			    }
			    catch(Exception e) {
			    	
			    }
			    if(!ObjectUtils.isEmpty(resp)) 
			    	return true;
			    
			    return false;
			  }
}
