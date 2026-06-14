package testng.definitions;

import org.openqa.selenium.WebDriver;

import com.autotest.testng.actions.DashboardActions;
import com.autotest.testng.utils.HelperClass;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class DashboardPageDefinitions {

  DashboardActions dashboardActions = new DashboardActions();

  @And("The user is on the student dashboard page")
  public void The_user_is_on_the_dashboard_page() {
    String url = "https://polban-space.cloudias79.com/jtk-learn/dashboard-pelajar";
    WebDriver driver = HelperClass.getDriver();
    driver.get(url);
  }

  @When("The user clicks on the logout button")
  public void The_user_clicks_on_the_logout_button() {
    dashboardActions.clickLogout();
  }

  @When("The user navigates to the course {string} information page")
  public void The_user_navigates_to_the_course_information_page(String s) {
    dashboardActions.selectCourseByName(s);
  }
}
