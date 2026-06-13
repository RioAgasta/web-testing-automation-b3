package stepdefinitions;

import com.autotest.testng.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.CourseOverviewPage;
import pages.HomePage;
import utils.DriverFactory;
import utils.LoginUtil;

import java.time.Duration;

public class CourseOverviewSteps {

    private CourseOverviewPage courseOverviewPage;

    @Given("Student is logged in and on the Course Overview page")
    public void studentIsLoggedInAndOnCourseOverviewPage() {
        HomePage homePage = LoginUtil.loginAndGoToHome();

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard-pelajar"));
        wait.until(d -> homePage.isCourseDisplayed());

        String baseUrl = ConfigReader.getProperty("app.url");
        String dashboardUrl = baseUrl + "dashboard-pelajar";

        int max = homePage.getCourseCount();
        for (int i = 0; i < max; i++) {
            HomePage hp = new HomePage(DriverFactory.getDriver());
            wait.until(d -> hp.isCourseDisplayed());
            hp.selectCourseByIndex(i);

            try {
                courseOverviewPage = new CourseOverviewPage(DriverFactory.getDriver());
                wait.until(d -> courseOverviewPage.isOnCourseOverviewPage());
                return;
            } catch (Exception e) {
                DriverFactory.getDriver().get(dashboardUrl);
                wait.until(ExpectedConditions.urlContains("dashboard-pelajar"));
            }
        }
        Assert.fail("Could not navigate to any course overview page");
    }

    @When("Student leaves the enrollment code input field empty")
    public void studentLeavesEnrollmentCodeFieldEmpty() {
        courseOverviewPage.clearEnrollmentCodeField();
    }

    @When("Student clicks the {string} button")
    public void studentClicksButton(String buttonName) {
        courseOverviewPage.clickDaftarButton();
    }

    @Then("System displays error message {string}")
    public void systemDisplaysErrorMessage(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        wait.until(d -> courseOverviewPage.isErrorMessageDisplayed());
        Assert.assertTrue(courseOverviewPage.isErrorMessageDisplayed(),
            "Error message should be displayed");
        String actualMessage = courseOverviewPage.getErrorMessage();
        String expectedNormalized = expectedMessage.replaceAll("[.!]+$", "");
        String actualNormalized = actualMessage.replaceAll("[.!]+$", "");
        Assert.assertEquals(actualNormalized, expectedNormalized,
            "Error message does not match. Expected: " + expectedMessage
                + ", Actual: " + actualMessage);
    }

    @Then("Student remains on the Course Overview page")
    public void studentRemainsOnCourseOverviewPage() {
        Assert.assertTrue(courseOverviewPage.isOnCourseOverviewPage(),
            "Student should remain on the Course Overview page");
    }
}
