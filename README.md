# Overview

A simple SpringBoot application that uses TestNG and Selenium for it's test harness.  These tests represent acceptance tests and don't require the full Spring context to load when running the tests.  Really, these tests should probably be in their own project.

# Build executable JAR called spring-boot-http and place in /target folder.  Note that in the pom.xml file we've include a special build plugin that will produce an extra JAR with the test classes.

`mvn clean && mvn package`

# Build docker image
Run this in the main project folder 'spring-boot-http')

Look in the Dockerfile to see the contents of this image.  It's based on OpenDJK 8

`docker build -t testng-selenium-tests .`

# Run docker container in the background (interactive).  The tests will be run automatically.  The container is automatically deleted when finished running.

`docker run --rm -it --name testng-selenium-tests testng-selenium-tests`
