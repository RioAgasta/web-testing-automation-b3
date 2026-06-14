package com.autotest.testng.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginScenarioLocators {

  // Login Page Elements
  @FindBy(xpath = "//input[@type='email']")
  public WebElement emailInput;

  @FindBy(xpath = "//input[@type='password']")
  public WebElement passwordInput;

  @FindBy(xpath = "//button[@type='submit']")
  public WebElement submitButton;

  // Alternative locators using CSS
  public static final By EMAIL_INPUT = By.xpath("//input[@type='email']");
  public static final By PASSWORD_INPUT = By.xpath("//input[@type='password']");
  public static final By SUBMIT_BUTTON = By.xpath("//button[@type='submit']");

  // Dashboard Elements
  @FindBy(xpath = "//h3[contains(@class, 'greeting-title')]")
  public WebElement greetingTitle;

  @FindBy(xpath = "//a[contains(@class, 'nav-link') and text()='Kursus Saya']")
  public WebElement kursusSayaMenu;

  // Alternative locators
  public static final By GREETING_TITLE = By.xpath("//h3[contains(@class, 'greeting-title')]");
  public static final By KURSUS_SAYA_MENU = By.xpath("//a[contains(@class, 'nav-link') and text()='Kursus Saya']");

  // My Course Page Elements
  @FindBy(xpath = "//h6[contains(@class, 'card-title') and text()='cobakursus']")
  public WebElement cobakursusCard;

  public static final By COBAKURSUS_CARD = By.xpath("//h6[contains(@class, 'card-title') and text()='cobakursus']");

  // Detail Course Page Elements
  @FindBy(xpath = "//button[contains(@class, 'button-overview')]")
  public WebElement lanjutkanKursusButton;

  @FindBy(xpath = "//span[contains(@class, 'progress-percentage-text')]")
  public WebElement progressPercentage;

  public static final By LANJUTKAN_KURSUS_BUTTON = By.xpath("//button[contains(@class, 'button-overview')]");
  public static final By PROGRESS_PERCENTAGE = By.xpath("//span[contains(@class, 'progress-percentage-text')]");
}
