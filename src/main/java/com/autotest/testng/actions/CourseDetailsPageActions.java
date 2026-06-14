package com.autotest.testng.actions;

import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;

import com.autotest.testng.locators.CourseDetailsPageLocators;
import com.autotest.testng.utils.HelperClass;

public class CourseDetailsPageActions {
  private CourseDetailsPageLocators courseDetailsPageLocators;

  public CourseDetailsPageActions() {
  }

  private CourseDetailsPageLocators getLocators() {
    if (courseDetailsPageLocators == null) {
      courseDetailsPageLocators = new CourseDetailsPageLocators();
      PageFactory.initElements(HelperClass.getDriver(), courseDetailsPageLocators);
    }
    return courseDetailsPageLocators;
  }

  public void enterEnrollmentKey(String key) {
    getLocators().enrollmentKeyInput.sendKeys(key);
  }

  public void clearEnrollmentCodeField() {
    getLocators().enrollmentKeyInput.clear();
    JavascriptExecutor js = (JavascriptExecutor) HelperClass.getDriver();
    js.executeScript(
        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }))",
        getLocators().enrollmentKeyInput);
  }

  public void clickEnrollButton() {
    getLocators().enrollButton.click();
  }

  public String getEnrollmentErrorMessage() {
    try {
      return getLocators().errorMessage.getText();
    } catch (NoSuchElementException e) {
      return "Pesan Error Tidak Ditemukan";
    }
  }

  public void closeErrorMessage() {
    try {
      getLocators().swalConfirmButton.click();
    } catch (Exception ignored) {
    }
  }

  public boolean isOnCourseOverviewPage() {
    String currentUrl = HelperClass.getDriver().getCurrentUrl();
    return currentUrl.contains("/course/");
  }
}
