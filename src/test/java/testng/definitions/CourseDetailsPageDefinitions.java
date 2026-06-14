package testng.definitions;

import org.testng.Assert;

import com.autotest.testng.actions.CourseDetailsPageActions;
import com.autotest.testng.utils.HelperClass;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class CourseDetailsPageDefinitions {
  CourseDetailsPageActions courseDetailsPageActions = new CourseDetailsPageActions();

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
}
