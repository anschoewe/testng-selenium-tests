FROM openjdk:8-jdk-alpine AS JAVA

# Include the TestNG and Selenium dependencies we'll need to run the tests from the command-line
WORKDIR /testng-selenium-tests/testng-lib
RUN wget http://central.maven.org/maven2/org/testng/testng/6.14.3/testng-6.14.3.jar
RUN wget https://selenium-release.storage.googleapis.com/3.141/selenium-server-standalone-3.141.59.jar
RUN wget http://central.maven.org/maven2/org/seleniumhq/selenium/selenium-chrome-driver/3.141.59/selenium-chrome-driver-3.141.59.jar

# You'll need both Chrome and the Chrome driver installed in your container
RUN apk add --update chromium-chromedriver
RUN apk add chromium
RUN rm -rf /var/cache/apk/*

WORKDIR /testng-selenium-tests
# Copy in the project jar that includes the test dependencies
# Also include the TestNG XML file that lists the test classeses to run
COPY target/myproject-SNAPSHOT-tests.jar myproject-SNAPSHOT-tests.jar
COPY src/test/resources/test_suite.xml test_suite.xml

# When running the tests, include our test-classses jar in the classpath, in addition to the TestNG and Selenium dependencies
# If you're running the tests as the root user, you'll need to ensure that Chromium is invoked with the '--no-sandbox' argument.  This is currently set in the GoogleTest class.
CMD ["/usr/bin/java", "-cp", "myproject-SNAPSHOT-tests.jar:testng-lib/*", "org.testng.TestNG", "test_suite.xml"]






