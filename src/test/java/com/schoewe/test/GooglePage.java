package com.schoewe.test;

import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {

  private final WebDriver driver;
  private final WebDriverWait wait;

  @FindBy(name = "q")
  private WebElement searchBox;

  @FindBy(name = "btnK")
  private WebElement searchButton;

  @FindBy(className = "rc")
  private List<WebElement> searchResults;

  @FindBy(id = "foot")
  private WebElement footer;

  public GooglePage(final WebDriver driver) {
      this.driver = driver;
      PageFactory.initElements(driver, this);
      this.wait = new WebDriverWait(driver, 30);
  }

  public void goTo() {
      this.driver.get("https://www.google.com");
//      this.driver.findElement(By.name("q")).sendKeys("webdriver");
//      this.driver.findElement(By.name("btnG")).click();
//      this.driver.wait(webdriver.until.titleIs("webdriver - Google Search"), 1000);
  }

  public void searchFor(String text) throws InterruptedException {
     this.searchBox.sendKeys(text);
     wait.until(ExpectedConditions.elementToBeClickable(this.searchButton));
     this.searchButton.click();
     wait.until(ExpectedConditions.titleIs(text + " - Google Search"));
  }

  public List<WebElement> getResults() {
      return this.searchResults;
  }

}
