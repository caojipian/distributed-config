package com.cjp.framework.config;

import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceLoader;

/**
 *      连接参数默认获取方式
 * @author caojipian
 *
 */
public class DefaultResource extends FileResource implements Resource{
  
  public DefaultResource(String filePath) {
    super(filePath);
  }
  @Override
  public Properties properties() {
    Properties properties=new Properties();
    ServiceLoader<Resource> service=ServiceLoader.load(Resource.class);
    Iterator<Resource> iter=service.iterator();
    //判断是否存在SPI扩展，如没有则使用文件方式获取
    boolean spi=false;
    while(iter.hasNext()) {
      spi=true;
      Resource resouce=iter.next();
      properties.putAll(resouce.properties());;
    }
    if(!spi) {
      properties.putAll(super.properties());
    }
    return properties;
  }
  
}
