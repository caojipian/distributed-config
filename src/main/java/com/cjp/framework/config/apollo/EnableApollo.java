package com.cjp.framework.config.apollo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import com.ctrip.framework.apollo.core.ConfigConsts;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ApolloConfig.class,CustomApolloConfigRegistrar.class})
public @interface EnableApollo {
  String[] namespace() default {ConfigConsts.NAMESPACE_APPLICATION};
  String appId();
  String filePath() default "/var/conf/framework-config.properties";
}
