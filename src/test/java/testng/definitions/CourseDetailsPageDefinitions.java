package testng.definitions;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.autotest.testng.actions.CourseDetailsPageActions;
import com.autotest.testng.actions.DashboardActions;
import com.autotest.testng.actions.LoginPageActions;
import com.autotest.testng.utils.ConfigReader;
import com.autotest.testng.utils.HelperClass;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CourseDetailsPageDefinitions {
  CourseDetailsPageActions courseDetailsPageActions = new CourseDetailsPageActions();
  LoginPageActions objLogin = new LoginPageActions();
  DashboardActions objDashboard = new DashboardActions();

  @And("The user enters the enrollment code {string}")
  public void user_enters_enrollment_key(String s) {
    courseDetailsPageActions.enterEnrollmentKey(s);
  }

  @And("The user clicks on the enroll button")
  public void The_user_clicks_on_the_enroll_button() {
    courseDetailsPageActions.clickEnrollButton();
  }

  @Then("The system should display the error message {string}")
  public void The_system_should_display_the_error_message(String expectedMessage) {
    String actualMessage = courseDetailsPageActions.getEnrollmentErrorMessage();
    Assert.assertEquals(actualMessage, expectedMessage, "Pesan tidak sesuai ekspektasi");
  }

  @And("The user should remain on the course information page")
  public void the_user_should_remain_on_the_course_information_page() {
    String currentUrl = HelperClass.getDriver().getCurrentUrl();
    Assert.assertTrue(currentUrl.contains("/course/"), "User tidak berada di halaman informasi kursus.");
  }

  @Given("Student is logged in and on the Course Overview page")
  public void student_is_logged_in_and_on_course_overview_page() {
    HelperClass.setUpDriver();
    objLogin.login("rioTesting@gmail.com", "admin");
    objLogin.clickedLoginButton();

    WebDriverWait wait = new WebDriverWait(HelperClass.getDriver(), Duration.ofSeconds(10));
    wait.until(ExpectedConditions.urlContains("dashboard-pelajar"));

    objDashboard.selectCourseByName("Odoo Course Advanced");
    wait.until(d -> courseDetailsPageActions.isOnCourseOverviewPage());
  }

  @When("Student leaves the enrollment code input field empty")
  public void student_leaves_enrollment_code_empty() {
    WebDriverWait wait = new WebDriverWait(HelperClass.getDriver(), Duration.ofSeconds(10));
    wait.until(d -> {
      courseDetailsPageActions.clearEnrollmentCodeField();
      return true;
    });
  }

  @And("Student clicks the {string} button")
  public void student_clicks_button(String buttonName) {
    courseDetailsPageActions.clickEnrollButton();
  }

  @Then("System displays error message {string}")
  public void system_displays_error_message(String expectedMessage) {
    String actualMessage = courseDetailsPageActions.getEnrollmentErrorMessage();
    Assert.assertEquals(actualMessage, expectedMessage, "Error message does not match");
  }

  @And("Student remains on the Course Overview page")
  public void student_remains_on_course_overview_page() {
    Assert.assertTrue(courseDetailsPageActions.isOnCourseOverviewPage(),
        "Student should remain on the Course Overview page");
  }
}
