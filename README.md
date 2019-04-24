# Introduction
distributed-config是一个分布式配置集成组件，遵循它的标准接口集成各种流行的分布式配置平台可提供统一的调用方式，方便随时进行切换不同的分布式平台而不需要修改业务代码。

# 使用方式
### 使用前提：项目需要是基于spring3.x以上版本，jdk1.8及以上版本

#### 配置
```Java
@SpringBootApplication
//开启apollo
@EnableApollo(appId = "user")
//如使用xdiamond
//@EnableXdiamond(groupId="com.xxx",artifactId="user")
public class App 
{
    public static void main( String[] args )
    {
      SpringApplication.run(App.class, args);
      //无论使用apollo还是xdiamond,获取配置代码都如下，不需要改动，只需要把上面的@EnableApollo改成@EnableXdiamond,
      //当然注解中有个文件路径参数是获取连接参数的，需要改成相应平台的参数
      ConfigClient configClient=context.getBean(ConfigClientGroup.class).get(ConfigConsts.NAMESPACE_APPLICATION);
      System.out.println(configClient.getProperty("swagger.switch", "fff"));
    }
}
```
#### pom.xml配置
```Xml
<dependency>
  <groupId>com.cjp.framework</groupId>
  <artifactId>distributed-config</artifactId>
  <version>1.0</version>
</dependency>
