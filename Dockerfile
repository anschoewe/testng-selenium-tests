FROM openjdk:8-jdk-alpine AS JAVA

WORKDIR /testng-selenium-tests/testng-lib
RUN wget http://repo.spring.io/plugins-release/org/seleniumhq/selenium/selenium-server-standalone/2.53.0/selenium-server-standalone-2.53.0.jar
RUN wget http://central.maven.org/maven2/org/testng/testng/6.14.3/testng-6.14.3.jar
RUN wget http://central.maven.org/maven2/org/seleniumhq/selenium/selenium-java/3.141.59/selenium-java-3.141.59.jar

WORKDIR /testng-selenium-tests
# Copy in the project jar that includes the test dependencies
# Also include the TestNG XML file that lists the test classeses to run
COPY target/myproject-SNAPSHOT-tests.jar myproject-SNAPSHOT-tests.jar
COPY src/test/resources/test_suite.xml test_suite.xml

# When running the tests, include our test-classses jar in the classpath, in addition to the TestNG and Selenium dependencies
CMD ["/usr/bin/java", "-cp", "myproject-SNAPSHOT-tests.jar:testng-lib/testng-6.14.3.jar:testng-lib/selenium-server-standalone-2.53.0.jar:testng-lib/selenium-java-3.141.59.jar",  "org.testng.TestNG", "test_suite.xml"]





