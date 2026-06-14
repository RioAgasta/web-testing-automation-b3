package com.autotest.testng.actions;

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

    // Fill username
    getLocators().email.sendKeys(strUserName);

    // Fill password
    getLocators().password.sendKeys(strPassword);

  }

  public void clickedLoginButton() {
    // Click Login button
    getLocators().login.click();
  }

  // Get the error message of Login Page
  public String getErrorMessage() {
    return getLocators().errorMessage.getText();
  }

}