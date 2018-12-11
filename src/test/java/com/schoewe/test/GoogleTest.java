package com.schoewe.test;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleTest
{

	private WebDriver driver;
	private GooglePage google;

	@BeforeTest
	public void setUp() throws MalformedURLException
	{
		// Don't hard cord this... pass in the Chrome driver location as a system property
		// System.setProperty("webdriver.chrome.driver", "/Users/anschoewe/Code/repositories/testng-selenium-tests/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--no-sandbox"); //need '--no-sandbox' when running in a Docker container as root user.
		driver = new ChromeDriver(options);
		google = new GooglePage(driver);
	}

	@Test
	public void googleTest() throws InterruptedException
	{
		google.goTo();
		google.searchFor("whatever");
		Assert.assertTrue(google.getResults().size() >= 10);
	}

	@AfterTest
	public void tearDown() throws InterruptedException
	{
		driver.quit();
	}

}
