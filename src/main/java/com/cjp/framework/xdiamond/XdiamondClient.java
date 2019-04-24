package com.cjp.framework.xdiamond;

import java.util.Properties;
import org.springframework.util.StringUtils;
import com.cjp.framework.config.ConfigClient;
import com.cjp.framework.config.ConfigClientGroup;
import io.github.xdiamond.client.XDiamondConfig;

public class XdiamondClient implements ConfigClient,ConfigClientGroup{
  private XDiamondConfig config;
  public XdiamondClient(XDiamondConfig config) {
    this.config=config;
  }

  @Override
  public String getProperty(String key,String defaultValue) {
    String property=config.getProperty(key);
    if(StringUtils.isEmpty(property)) {
      return defaultValue;
    }
    return property;
  }

  @Override
  public Properties properties() {
    return config.getProperties();
  }

  @Override
  public ConfigClient get(String namespace) {
    return this;
  }
}
