A single spring-boot-starter-data-jpa brings all the dependencies for Spring boot and data jpa.
Since we are working with Mysql database, we should declare the dependency for MySQL connector too.
<br/>
<br/>
CrudRepository is an interface given by the spring data, which gives us the basic crud operations on a repository for a specific type.
<br/>
<br/>
In any typical hibernate using applications like spring with hibernate integration, we need to write all the CRUD operations of our own. 
Whereas in Spring Data JPA, CrudRepository provides in-built CRUD operations for the entity class.
<br/>
<br/>
@SpringBootApplication annotation tells to Spring Boot, this is a starting point of our Spring Boot Application.
<br/>
<br/>
CommandLineRunner is an interface provided by the spring boot. It is used to indicate that a bean should run when it is contained within a spring application.