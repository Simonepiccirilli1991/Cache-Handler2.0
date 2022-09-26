package com.seor0.cache.config;

import org.springframework.stereotype.Component;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.seor0.cache.model.OtpBo;

@Component
public class CacheClientOtp {

	public static final String OTPS = "otps";
	  private final HazelcastInstance hazelcastInstance 
	     = Hazelcast.newHazelcastInstance(createConfig2());

	  public Config createConfig2() {
	    Config config = new Config();
	    config.addMapConfig(mapConfig());
	    return config;
	  }

	  private MapConfig mapConfig() {
	    MapConfig mapConfig = new MapConfig(OTPS);
	    mapConfig.setTimeToLiveSeconds(360);
	    mapConfig.setMaxIdleSeconds(120);
	    return mapConfig;
	  }
	  
	  public OtpBo put(String number, OtpBo car){
		    IMap<String, OtpBo> map = hazelcastInstance.getMap(OTPS);
		    return map.putIfAbsent(number, car);
		  }

		  public OtpBo get(String key){
		    IMap<String, OtpBo> map = hazelcastInstance.getMap(OTPS);
		    return map.get(key);
		  }
		  
		  public String insert(String key, OtpBo request){
			    IMap<String, OtpBo> map = hazelcastInstance.getMap(OTPS);
			    map.put(key, request);
			    return key;
			  }
}
