package testng.definitions;

import com.autotest.testng.actions.EnterCourseActions;
import com.autotest.testng.utils.HelperClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class EnterCourseScenarioDefinitions {

  private final EnterCourseActions actions;

  public EnterCourseScenarioDefinitions() {
    this.actions = new EnterCourseActions();
  }

  @Given("User berada di halaman dashboard {string}")
  public void userBeradaDiHalamanDashboard(String url) {
    // Cek apakah driver sudah ada session, jika belum login ulang
    if (HelperClass.getDriver() == null) {
      HelperClass.setUpDriver();
      actions.loginAndNavigateToDashboard(url);
    } else {
      actions.openPage(url);
    }
  }

  @When("User klik menu {string}")
  public void userKlikMenu(String menuName) {
    if (menuName.equals("Kursus Saya")) {
      actions.clickKursusSayaMenu();
    }
  }

  @Then("User diarahkan ke halaman my courses")
  public void userDiarahkanKeHalamanMyCourses() {
    Assert.assertTrue(actions.isOnMyCoursesPage(), "User tidak diarahkan ke halaman my courses");
  }

  @When("User memilih kursus dengan nama {string}")
  public void userMemilihKursusDenganNama(String courseName) {
    if (courseName.equals("cobakursus")) {
      actions.clickCobaKursusCard();
    }
  }

  @And("User klik tombol {string}")
  public void userKlikTombol(String buttonName) {
    if (buttonName.equals("Lanjut Kursus")) {
      actions.clickLanjutKursusButton();
    }
  }

  @Then("User diarahkan ke halaman course {string}")
  public void userDiarahkanKeHalamanCourse(String courseName) {
    Assert.assertTrue(actions.isOnCoursePage(), "User tidak diarahkan ke halaman course");
  }

  @And("User melihat progress kursus")
  public void userMelihatProgressKursus() {
    Assert.assertTrue(actions.isProgressDisplayed(), "Progress kursus tidak ditemukan");
  }
}
