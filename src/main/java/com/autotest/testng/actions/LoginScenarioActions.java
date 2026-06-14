package com.autotest.testng.actions;

import com.autotest.testng.utils.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginScenarioActions {

  private WebDriver driver;
  private WebDriverWait wait;

  public LoginScenarioActions() {
    // Jangan inisialisasi di constructor - Driver belum tentu sudah dibuat
  }

  private void initDriver() {
    if (driver == null) {
      driver = HelperClass.getDriver();
      if (driver != null) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(HelperClass.TIMEOUT));
      }
    }
  }

  public void openLoginPage(String url) {
    HelperClass.openPage(url);
    initDriver();
  }

  public void inputEmail(String email) {
    initDriver();
    if (wait != null) {
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='email']")));
      WebElement emailInput = driver.findElement(By.xpath("//input[@type='email']"));
      emailInput.clear();
      emailInput.sendKeys(email);
    }
  }

  public void inputPassword(String password) {
    initDriver();
    if (driver != null) {
      WebElement passwordInput = driver.findElement(By.xpath("//input[@type='password']"));
      passwordInput.clear();
      passwordInput.sendKeys(password);
    }
  }

  public void clickSubmitButton() {
    initDriver();
    if (driver != null) {
      WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
      submitButton.click();
      HelperClass.waitForPageLoad();
    }
  }

  public boolean isOnDashboard() {
    initDriver();
    if (driver == null) return false;
    try {
      wait.until(ExpectedConditions.urlContains("dashboard-pelajar"));
      HelperClass.waitForPageLoad();
      return driver.getCurrentUrl().contains("dashboard-pelajar");
    } catch (Exception e) {
      System.out.println("Dashboard check: " + e.getMessage());
      return false;
    }
  }

  public boolean isGreetingDisplayed(String expectedGreeting) {
    initDriver();
    if (driver == null) return false;
    try {
      HelperClass.delay(1000);
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Hai')]")));
      WebElement greeting = driver.findElement(By.xpath("//*[contains(text(), 'Hai')]"));
      String actualGreeting = greeting.getText();
      System.out.println("Greeting found: " + actualGreeting);
      return actualGreeting.contains("Hai");
    } catch (Exception e) {
      System.out.println("Greeting check: " + e.getMessage());
      return false;
    }
  }
}
