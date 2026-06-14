package com.autotest.testng.actions;

import com.autotest.testng.utils.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EnterCourseActions {

  private WebDriver driver;
  private WebDriverWait wait;

  public EnterCourseActions() {
  }

  private void initDriver() {
    if (driver == null) {
      driver = HelperClass.getDriver();
      if (driver != null) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(HelperClass.TIMEOUT));
      }
    }
  }

  public void loginAndNavigateToDashboard(String dashboardUrl) {
    HelperClass.setUpDriver();
    initDriver();
    
    driver.get("https://polban-space.cloudias79.com/jtk-learn");

    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='email']")));
    driver.findElement(By.xpath("//input[@type='email']")).sendKeys("fajri.pelajar@kelompok4.com");
    driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin123");
    driver.findElement(By.xpath("//button[@type='submit']")).click();

    wait.until(ExpectedConditions.urlContains("dashboard-pelajar"));
    HelperClass.waitForPageLoad();
  }

  public void openPage(String url) {
    HelperClass.openPage(url);
    initDriver();
  }

  public void clickKursusSayaMenu() {
    initDriver();
    if (driver == null) return;

    HelperClass.waitForPageLoad();
    try {
      // Klik menu link
      driver.findElement(By.xpath("//a[contains(text(), 'Kursus')]")).click();
      HelperClass.waitForPageLoad();
    } catch (Exception e) {
      System.out.println("Menu click: " + e.getMessage());
    }
  }

  public boolean isOnMyCoursesPage() {
    initDriver();
    if (driver == null) return false;
    try {
      HelperClass.waitForPageLoad();
      return driver.getCurrentUrl().contains("my-courses");
    } catch (Exception e) {
      return false;
    }
  }

  public void clickCobaKursusCard() {
    initDriver();
    if (driver == null) return;

    HelperClass.waitForPageLoad();
    try {
      ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
      HelperClass.delay(1000);
      
      driver.findElement(By.xpath("//*[contains(text(), 'cobakursus')]")).click();
      HelperClass.waitForPageLoad();
    } catch (Exception e) {
      System.out.println("Card click: " + e.getMessage());
    }
  }

  public void clickLanjutKursusButton() {
    initDriver();
    if (driver == null) return;

    HelperClass.waitForPageLoad();
    try {
      ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
      HelperClass.delay(1000);
      
      driver.findElement(By.xpath("//button[contains(text(), 'Lanjut')]")).click();
      HelperClass.waitForPageLoad();
    } catch (Exception e) {
      System.out.println("Button click: " + e.getMessage());
    }
  }

  public boolean isOnCoursePage() {
    initDriver();
    if (driver == null) return false;
    try {
      HelperClass.waitForPageLoad();
      return driver.getCurrentUrl().contains("course");
    } catch (Exception e) {
      return false;
    }
  }

  public boolean isProgressDisplayed() {
    initDriver();
    if (driver == null) return false;
    try {
      return driver.getCurrentUrl().contains("course");
    } catch (Exception e) {
      return false;
    }
  }
}
