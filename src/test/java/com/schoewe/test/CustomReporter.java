package com.schoewe.test;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

public class CustomReporter implements IReporter{
  @Override
  public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
     String outputDirectory) {
     
     //Iterating over each suite included in the test
     for (ISuite suite : suites) {
        //Getting the results for the said suite
        Map<String, ISuiteResult> suiteResults = suite.getResults();
        for (ISuiteResult sr : suiteResults.values()) {
           ITestContext tc = sr.getTestContext();
           if(!tc.getFailedTests().getAllResults().isEmpty())
           System.out.println("Failed");
        }
     }
  }
}
