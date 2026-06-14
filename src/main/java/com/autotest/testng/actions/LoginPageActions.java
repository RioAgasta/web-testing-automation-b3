package com.autotest.testng.actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import com.autotest.testng.locators.LoginPageLocators;
import com.autotest.testng.utils.HelperClass;

public class LoginPageActions {

  private LoginPageLocators loginPageLocators;

  public LoginPageActions() {
  }

  private LoginPageLocators getLocators() {
    if (loginPageLocators == null) {
      loginPageLocators = new LoginPageLocators();
      PageFactory.initElements(
          HelperClass.getDriver(),
          loginPageLocators);
    }
    return loginPageLocators;
  }

  public void login(String strUserName, String strPassword) {
    getLocators().email.sendKeys(strUserName);
    getLocators().password.sendKeys(strPassword);
  }

  public void enterEmail(String email) {
    getLocators().email.sendKeys(email);
  }

  public void enterPassword(String password) {
    getLocators().password.sendKeys(password);
  }

  public void clearEmail() {
    getLocators().email.clear();
  }

  public void clickedLoginButton() {
    getLocators().login.click();
  }

  public String getErrorMessage() {
    try {
      String title = getLocators().swalErrorTitle.getText();
      String body = getLocators().swalErrorMessageBody.getText();
      if (!title.isEmpty() && !body.isEmpty()) {
        return title + " " + body;
      }
      return title + body;
    } catch (Exception e) {
      try {
        return getLocators().errorMessage.getText();
      } catch (NoSuchElementException ex) {
        return "";
      }
    }
  }

  public String getValidationMessage() {
    try {
      JavascriptExecutor js = (JavascriptExecutor) HelperClass.getDriver();
      String msg = (String) js.executeScript(
          "return arguments[0].validationMessage;", getLocators().email);
      if (msg != null && !msg.isEmpty()) {
        return msg;
      }
    } catch (Exception e) {
    }
    return "";
  }
}