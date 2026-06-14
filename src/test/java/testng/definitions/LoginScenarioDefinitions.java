package testng.definitions;

import com.autotest.testng.actions.LoginScenarioActions;
import com.autotest.testng.utils.HelperClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginScenarioDefinitions {

  private final LoginScenarioActions actions;

  public LoginScenarioDefinitions() {
    this.actions = new LoginScenarioActions();
  }

  @Given("User berada di halaman login {string}")
  public void userBeradaDiHalamanLogin(String url) {
    HelperClass.setUpDriver();
    actions.openLoginPage(url);
  }

  @When("User mengisi email field dengan {string}")
  public void userMengisiEmailField(String email) {
    actions.inputEmail(email);
  }

  @And("User mengisi password field dengan {string}")
  public void userMengisiPasswordField(String password) {
    actions.inputPassword(password);
  }

  @And("User klik tombol submit")
  public void userKlikTombolSubmit() {
    actions.clickSubmitButton();
  }

  @Then("User berhasil login dan diarahkan ke dashboard")
  public void userBerhasilLoginDanDiarahkanKeDashboard() {
    Assert.assertTrue(actions.isOnDashboard(), "User tidak diarahkan ke dashboard");
  }

  @And("User melihat greeting {string}")
  public void userMelihatGreeting(String expectedGreeting) {
    Assert.assertTrue(actions.isGreetingDisplayed(expectedGreeting),
        "Greeting tidak sesuai. Expected: " + expectedGreeting);
  }
}
