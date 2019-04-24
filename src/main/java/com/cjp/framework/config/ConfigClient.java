package com.cjp.framework.config;

import java.util.Properties;
public interface ConfigClient{
  
  public String getProperty(String key,String defaultValue);
  
  public Properties properties();
}
