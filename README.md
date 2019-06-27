*Spring Boot gives us a property called <b>server.context-path</b> which is used to change springboot context path. <br/>
*Spring Boot gives us a property called <b>server.port</b> which is used to change tomcat port number.<br/>
*Spring Boot gives us a property called <b>server.session.timeout</b> which is used to set tomcat session timeout. <br/>
*Spring Boot gives us a property called <b>spring.profiles.active</b> which is used to read environment properties based on active-profile under Spring Boot Environment <br/>

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
