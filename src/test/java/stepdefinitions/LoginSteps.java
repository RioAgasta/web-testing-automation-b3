package stepdefinitions;

import com.autotest.testng.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        DriverFactory.getDriver().get(ConfigReader.getProperty("app.url"));
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @When("User leaves the username field empty")
    public void userLeavesUsernameFieldEmpty() {
        loginPage.clearEmailField();
    }

    @When("User enters a valid password")
    public void userEntersValidPassword() {
        loginPage.enterPassword("masnasir");
    }

    @When("User clicks the login button")
    public void userClicksLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("System should display a validation message indicating the username is required")
    public void systemShouldDisplayValidationMessage() {
        Assert.assertTrue(loginPage.isValidationMessageDisplayed(),
            "Validation message should be displayed when username is empty");
    }

    @When("User enters email {string} and password {string}")
    public void userEntersEmailAndPassword(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @Then("User should be redirected to the home page")
    public void userShouldBeRedirectedToHomePage() {
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        Assert.assertTrue(homePage.isUserPhotoDisplayed());
    }

    @Then("Error message should be displayed")
    public void errorMessageShouldBeDisplayed() {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }
}
