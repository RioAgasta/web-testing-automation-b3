package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;
import utils.DriverFactory;
import utils.LoginUtil;

import java.time.Duration;

public class HomeSteps {

    private HomePage homePage;

    @Given("User is logged in")
    public void userIsLoggedIn() {
        homePage = LoginUtil.loginAndGoToHome();
    }

    @Then("User should see the course list")
    public void userShouldSeeCourseList() {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        wait.until(d -> homePage.isCourseDisplayed());
        Assert.assertTrue(homePage.isCourseDisplayed());
    }

    @Then("The page title should contain {string}")
    public void pageTitleShouldContain(String title) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        wait.until(d -> homePage.getPageTitle().toLowerCase().contains(title.toLowerCase()));
        Assert.assertTrue(homePage.getPageTitle().toLowerCase().contains(title.toLowerCase()),
            "Page title does not contain '" + title + "'. Actual: " + homePage.getPageTitle());
    }
}
