package com.cjp.framework.config.apollo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cjp.framework.config.ConfigClientGroup;

@Configuration
public class ApolloConfig {
  
  @Bean
  public ConfigClientGroup configClient() throws Exception {
    String[] namespaceArray=CustomApolloConfigRegistrar.getNamespaces();
    ConfigClientGroup configClientGroup=new ApolloClientGroup(namespaceArray);
    return configClientGroup;
  }
}
