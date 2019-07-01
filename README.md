*Spring Boot gives us a property called <b>server.context-path</b> which is used to change springboot context path. <br/>
*Spring Boot gives us a property called <b>server.port</b> which is used to change tomcat port number.<br/>
*Spring Boot gives us a property called <b>server.session.timeout</b> which is used to set tomcat session timeout. <br/>
*Spring Boot gives us a property called <b>spring.profiles.active</b> which is used to read environment properties based on active-profile under Spring Boot Environment <br/>
*Spring Boot gives us a property called <b>spring.main.lazy-initialization</b> to tell spring to do lazy loading <br/>

<b>application.properties</b>
<br/>
server.port=8085
<br/>
server.context-path=/Basic_Spring_Boot
<br/>
server.session.timeout = 5000 // Session timeout in seconds
<br/>
spring.profiles.active=Dev
<br/>
spring.main.lazy-initialization=true
<br/>

<br/>
Tomcat is the embedded default server coming with Spring Boot. To change the Tomcat to Jetty server, we should change the dependencies in pom.xml like below.
<br/>
<pre>
&lt;dependency&gt;
   &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;
   &lt;artifactId&gt;spring-boot-starter-jetty&lt;/artifactId&gt;
&lt;/dependency&gt;
</pre>

<br/>

Generally we can set the Spring boot server port by using server.port property in application properties file.
But if we want to set the server port as random port (Generally used when working with micro-services) the server.port should be assigned with ‘0’ (Zero).So that spring boot will automatically pick the available ports from OS and assigns to the application.
<br/>

<b>application.properties</b>
<br/>
server.port=0
<br/>

Spring Framework has support for lazy bean initialisation since along ago. In spring by default; if the application context being refreshed, every bean in the context will be created, and its dependencies will be injected.If we enable Spring Boot Lazy Loading, the beans will not be created and it won’t be injected the dependencies while refreshing the application context.

<b>application.properties</b>
<br/>
spring.main.lazy-initialization=true
<br/>


JVM will take the default time zone as the server’s time zone. For example, if the server is running on IST, then the JVM takes the IST time as default.In Spring boot we can quickly change/set this default timezone using java.util.TimeZone class like below.
<br/>
<pre>
@SpringBootApplication
public class Application {
    @PostConstruct
     public void init(){
      // Setting Spring Boot SetTimeZone
      TimeZone.setDefault(TimeZone.getTimeZone("IST"));
    }
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
</pre>

<br/>
By default the in-memory ActiveMQ got enabled when we define ActiveMQ dependencies in pom.xml, As we are using external ActiveMQ instance we should disable it by saying <b>spring.activemq.in-memory=false</b> and <b>spring.activemq.pool.enable=false</b>
