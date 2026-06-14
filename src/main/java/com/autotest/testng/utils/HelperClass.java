// In: utils/HelperClass.java
package com.autotest.testng.utils;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.autotest.testng.locators.MyCourseLocators;

import java.time.Duration;

public class HelperClass {

  private static WebDriver driver = null;
  public final static int TIMEOUT = 30;
  private static int loggedInUserId;

  public static void setUpDriver() {
    if (driver == null) {
      WebDriverManager.chromedriver().setup();

      ChromeOptions options = new ChromeOptions();
      options.addArguments("--start-maximized");
      options.addArguments("--disable-blink-features=AutomationControlled");
      options.addArguments("--disable-password-manager-reauthentication");
      options.addArguments("--disable-save-password-bubble");
      options.addArguments("--disable-password-leak-detection");
      options.addArguments("--disable-password-generation");
      options.addArguments("--disable-password-import");
      options.setExperimentalOption("excludeSwitches", new String[]{
          "enable-automation", "save-password", "password-manager-redirect",
          "enable-password-change"
      });
      options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
        put("credentials_enable_service", false);
        put("profile.password_manager_enabled", false);
        put("profile.default_content_setting_values.notifications", 2);
        put("safebrowsing.enabled", false);
        put("safebrowsing.esb.enabled", false);
        put("profile.password_manager_leak_detection", false);
      }});

      driver = new ChromeDriver(options);
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));

      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
      driver.manage().window().maximize();

      // Get the application URL from our new ConfigReader
      String url = ConfigReader.getProperty("app.url");
      System.out.println("Navigating to URL: " + url);
      driver.get(url);
    }
  }

  public static void openPage(String url) {
    driver.get(url);

    // Tunggu hingga halaman sepenuhnya dimuat
    new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(
        webDriver -> ((JavascriptExecutor) webDriver)
            .executeScript("return document.readyState").equals("complete"));

    // Tunggu tambahan untuk framework JavaScript
    new WebDriverWait(driver, Duration.ofSeconds(5)).until(d -> {
      try {
        return (Boolean) ((JavascriptExecutor) d)
            .executeScript("return window.jQuery !== undefined ? jQuery.active === 0 : true");
      } catch (Exception e) {
        return true;
      }
    });
  }

  public static WebDriver getDriver() {
    return driver;
  }

  public static void tearDown() {
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }

  public static void setLoggedInUserId(int id) {
    loggedInUserId = id;
  }

  public static int getLoggedInUserId() {
    return loggedInUserId;
  }

  /**
   * Ambil nama user yang tampil di navbar.
   */
  public static String getLoggedInUserName() {
    WebDriver driver = getDriver();
    return driver.findElement(MyCourseLocators.LOGGED_IN_USERNAME)
        .getText()
        .trim();
  }

  public static void waitForPageLoad() {
    WebDriver driver = getDriver();
    new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(
        webDriver -> ((JavascriptExecutor) webDriver)
            .executeScript("return document.readyState").equals("complete"));

    // Tunggu tambahan untuk framework JavaScript
    new WebDriverWait(driver, Duration.ofSeconds(5)).until(d -> {
      try {
        return (Boolean) ((JavascriptExecutor) d)
            .executeScript("return window.jQuery !== undefined ? jQuery.active === 0 : true");
      } catch (Exception e) {
        return true;
      }
    });
  }

  public static By byFromElement(WebElement element) {
    String locatorString = element.toString();

    // Ekstrak locator dari string representasi WebElement
    // Contoh: [[ChromeDriver: chrome on WINDOWS (c9d0a5b6d6f1e3e5b6d0c7a9b8d)] ->
    // css selector: #completed .custom-card]
    if (locatorString.contains("->")) {
      String[] parts = locatorString.split("->")[1].trim().split(": ", 2);
      String locatorType = parts[0].trim();
      String selector = parts[1].trim();

      switch (locatorType.toLowerCase()) {
        case "css selector":
          return By.cssSelector(selector);
        case "xpath":
          return By.xpath(selector);
        case "id":
          return By.id(selector);
        case "class name":
          return By.className(selector);
        case "name":
          return By.name(selector);
        default:
          throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
      }
    }
    throw new IllegalArgumentException("Cannot convert WebElement to By: " + locatorString);
  }

  public static void delay(int i) {
    try {
      Thread.sleep(i);
    } catch (InterruptedException e) {
      System.err.println("HelperClass: Interrupted during delay: " + e.getMessage());
      Thread.currentThread().interrupt(); // Restore interrupted status
    }
  }
}
