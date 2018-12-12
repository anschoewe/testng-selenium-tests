# Overview

A simple SpringBoot application that uses TestNG and Selenium for it's test harness.  These tests represent acceptance tests and don't require the full Spring context to load when running the tests.  Really, these tests should probably be in their own project.

# Build executable JAR 
Place myproject-SNAPSHOT.jar and place in /target folder. Note that in the pom.xml file we've include a special build plugin that will produce an extra JAR with the test classes.

`mvn clean package -DskipTests`

# Optional, build JAR and execute tests using Maven
You can build the project and also run the tests through Maven.  In order to do this, you'll need to download the Selenium Chrome Driver and tell your tests where to find it.  The below example works on a Mac.  If running on Linux or Windows, substitute in the correct Chrome Driver.

```
mkdir -p testng-lib && cd testng-lib
wget -N https://chromedriver.storage.googleapis.com/2.45/chromedriver_mac64.zip
unzip -o chromedriver_mac64.zip && rm chromedriver_mac64.zip && chmod +x chromedriver
cd ..
mvn clean package -DargLine="-Dwebdriver.chrome.driver=$(pwd)/testng-lib/chromedriver"
```

# Build docker image
Run this in the main project folder 'spring-boot-http')

Look in the Dockerfile to see the contents of this image.  It's based on OpenDJK 8

`docker build -t testng-selenium-tests .`

# Run docker container in the foreground (interactive).  The tests will be run automatically.  The container is automatically deleted when finished running.

`docker run --rm -it --name testng-selenium-tests testng-selenium-tests`

# Or, Run as a script (useful for Jenkin's build step for parsing result)
This only works if you've modified the test_suite.xml file to register our CustomReporter class as a listener.

```
#!/bin/bash

docker run --rm -it --name testng-selenium-tests testng-selenium-tests > acceptance-test-results.txt
if grep -q Failed "acceptance-test-results.txt"; then
  echo "Failed"
  exit(1)
else
  echo "Passed"
  exit(0)
fi
```
