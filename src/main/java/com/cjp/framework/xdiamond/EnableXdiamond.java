package com.cjp.framework.xdiamond;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@Import({CustomXdiamondConfigRegistrar.class,XdiamondConfig.class})
public @interface EnableXdiamond {
  String groupId() default "";
  String artifactId() default "";
  String version() default "1.0";
  String filePath() default "/var/conf/framework-config.properties";
}
