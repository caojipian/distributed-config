package com.cjp.framework.xdiamond;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class CustomXdiamondConfigRegistrar implements ImportBeanDefinitionRegistrar{

  private static String groupId;
  private static String artifactId;
  private static String version;
  private static String filePath;
  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
      BeanDefinitionRegistry registry) {
    AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata
        .getAnnotationAttributes(EnableXdiamond.class.getName()));
    groupId=attributes.getString("groupId");
    artifactId=attributes.getString("artifactId");
    version=attributes.getString("version");
    filePath=attributes.getString("filePath");
  }
  public static String getGroupId() {
    return groupId;
  }
  public static String getArtifactId() {
    return artifactId;
  }
  public static String getVersion() {
    return version;
  }
  public static String getFilePath() {
    return filePath;
  }
}
