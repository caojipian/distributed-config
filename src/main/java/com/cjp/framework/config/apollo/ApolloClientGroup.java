package com.cjp.framework.config.apollo;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;
import com.cjp.framework.config.ConfigClient;
import com.cjp.framework.config.ConfigClientGroup;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

public class ApolloClientGroup implements ConfigClientGroup{
  private Map<String,ConfigClient> clientMap=new HashMap<>();
  private Lock lock=new ReentrantReadWriteLock().writeLock();
  public ApolloClientGroup(String[] namespaceArray) {
    Stream.of(namespaceArray).forEach(namespace->{
      clientMap.put(namespace, new ApolloClient(ConfigService.getConfig(namespace)));
    });
  }
  
  @Override
  public ConfigClient get(String namespace) {
    if(clientMap.get(namespace)==null) {
      try {
        lock.lock();
        if(clientMap.get(namespace)==null) {
          clientMap.put(namespace,new ApolloClient(ConfigService.getConfig(namespace)));
        }
      }finally {
        lock.unlock();
      }
    }
    return clientMap.get(namespace);
  }

  static class ApolloClient implements ConfigClient{
    private Config config;
    private Properties properties=new Properties();
    public ApolloClient(Config config) {
      this.config=config;
      config.getPropertyNames().forEach(propertyName->{
        properties.put(propertyName, config.getProperty(propertyName, ""));
      });
    }

    @Override
    public String getProperty(String key,String defaultValue) {
      return config.getProperty(key,defaultValue);
    }

    @Override
    public Properties properties() {
      return properties;
    }
  }

}
