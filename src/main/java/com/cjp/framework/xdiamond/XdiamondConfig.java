package com.cjp.framework.xdiamond;

import java.util.Properties;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.cjp.framework.config.ConfigClient;
import com.cjp.framework.config.ConfigClientGroup;
import com.cjp.framework.config.DefaultResource;
import com.cjp.framework.config.Resource;
import io.github.xdiamond.client.XDiamondConfig;
import io.github.xdiamond.client.spring.XDiamondConfigFactoryBean;
/**
 * 支持spring boot
 * @author caojipian
 *
 */
@Configuration
@Import(ConfigClient.class)
public class XdiamondConfig{
    private static final String SERVER_HOST="serverHost";
    private static final String SERVER_PORT="serverPort";
    private static final String GROUP_ID="groupId";
    private static final String ARTIFACT_ID="artifactId";
    private static final String VERSION="version";
    private static final String PROFILE="profile";
    private static final String SECRET_KEY="secretKey";
  
	@Bean
    public XDiamondConfigFactoryBean productXDiamondConfigFactoryBean() {
	  Resource resource=new DefaultResource(CustomXdiamondConfigRegistrar.getFilePath());
	  Properties properties=resource.properties();
      XDiamondConfigFactoryBean xDiamondConfigFactoryBean=new XDiamondConfigFactoryBean();
      xDiamondConfigFactoryBean.setServerHost(properties.getProperty(SERVER_HOST));
      xDiamondConfigFactoryBean.setServerPort(properties.getProperty(SERVER_PORT));
      xDiamondConfigFactoryBean.setGroupId(properties.getProperty(GROUP_ID));
      xDiamondConfigFactoryBean.setArtifactId(properties.getProperty(ARTIFACT_ID));
      xDiamondConfigFactoryBean.setVersion(properties.getProperty(VERSION));
      xDiamondConfigFactoryBean.setProfile(properties.getProperty(PROFILE));
      xDiamondConfigFactoryBean.setSecretKey(properties.getProperty(SECRET_KEY));
      return xDiamondConfigFactoryBean;
    }
	
    @Bean
    public PropertyPlaceholderConfigurer productPropertyPlaceholderConfigurer(XDiamondConfigFactoryBean xDiamondConfigFactoryBean) {
      PropertyPlaceholderConfigurer propertyPlaceholderConfigurer=new PropertyPlaceholderConfigurer();
      propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
      XDiamondConfig xDiamondConfig;
      try {
          xDiamondConfig = xDiamondConfigFactoryBean.getObject();
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
      Properties properties=xDiamondConfig.getProperties();
      propertyPlaceholderConfigurer.setPropertiesArray(properties);
      return propertyPlaceholderConfigurer;
    }
    
    @Bean
    public ConfigClientGroup configClientGroup(XDiamondConfigFactoryBean xDiamondConfigFactoryBean) throws Exception {
      ConfigClientGroup configClientGroup=new XdiamondClient(xDiamondConfigFactoryBean.getObject());
      return configClientGroup;
    }
}
