# mondial-se
This is a simple project to demonstrate JPA usage within a Java SE project. It does include some sample entity classes that demonstrate access to [Mondial](https://www.dbis.informatik.uni-goettingen.de/Mondial/) database.
## Configuration
In order to set-up this project you need to configure the database connection. All relevant connection parameters are included in [persistence.xml](./src/main/resources/META-INF/persistence.xml). You need to change `$host`, `$user` and `$password` in the properties section to match your setup:
```xml
<properties>
      <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://$host:5432/mondial"/>
      <property name="javax.persistence.jdbc.user" value="$user"/>
      <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
      <property name="javax.persistence.jdbc.password" value="$password"/>
</properties>
```
## Execute Tests
There is a single controller ([CountryJpaController.java](./src/main/java/de/hspf/swt/mondial/jpa/CountryJpaController.java)) and an associated test class ([CountryJpaControllerTest.java](./src/test/java/de/hspf/swt/mondial/jpa/CountryJpaControllerTest.java)) to demonstrate database access.

This sample project does only include tests to read data from the database. Feel free top add write tests (however, if you are utilizing the database connection at our university, please keep in mind that you do not have write access).

To run the tests just execute:
```djangotemplate
mvn test
```
or simply invoke tests from your IDE. The tests are configured to be executed with maven surefire plugin on JUnit 5 plattform - for further details see [surefire documentation](https://maven.apache.org/surefire/maven-surefire-plugin/examples/junit-platform.html).  