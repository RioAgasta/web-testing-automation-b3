package com.autotest.testng.actions;

import java.util.NoSuchElementException;

import org.openqa.selenium.support.PageFactory;

import com.autotest.testng.locators.CourseDetailsPageLocators;
import com.autotest.testng.utils.HelperClass;

public class CourseDetailsPageActions {
  CourseDetailsPageLocators courseDetailsPageLocators = null;

  public CourseDetailsPageActions() {
    this.courseDetailsPageLocators = new CourseDetailsPageLocators();
    PageFactory.initElements(HelperClass.getDriver(), this.courseDetailsPageLocators);
  }

  public void enterEnrollmentKey(String key) {
    courseDetailsPageLocators.enrollmentKeyInput.sendKeys(key);
  }

  public void clickEnrollButton() {
    courseDetailsPageLocators.enrollButton.click();
  }

  public String getEnrollmentErrorMessage() {
    try {
      return courseDetailsPageLocators.errorMessage.getText();
    } catch (NoSuchElementException e) {
      return "Pesan Error Tidak Ditemukan";
    }
  }
}
