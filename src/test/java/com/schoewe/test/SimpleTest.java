package com.schoewe.test;

//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SimpleTest // extends AbstractTestNGSpringContextTests
{
  @Test
  public void testMethodOne() {
     Assert.assertTrue(true);
  }
	  
  @Test
  public void testMethodTwo() {
     Assert.assertTrue(true);
  }
	  
  @Test(dependsOnMethods = {"testMethodTwo"})
  public void testMethodThree() {
     Assert.assertTrue(true);
  }
}
