package com.cjp.framework.config;

import java.io.IOException;
import java.util.Properties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
/**
 * 连接参数来源
 * @author 62368
 *
 */
public class FileResource implements Resource{
  private final String filePath;
  public FileResource(String filePath) {
    this.filePath=filePath;
  }
  
  @Override
  public Properties properties() {
    Properties properties=null;
    try {
        properties=PropertiesLoaderUtils.loadProperties(new FileSystemResource(filePath));
    } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException("配置文件加载失败!");
    }
    return properties;
  }

}
