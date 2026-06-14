package testng.definitions;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.autotest.testng.actions.DashboardActions;
import com.autotest.testng.actions.LoginPageActions;
import com.autotest.testng.utils.ConfigReader;
import com.autotest.testng.utils.HelperClass;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageDefinitions {

    LoginPageActions objLogin = new LoginPageActions();
    DashboardActions objDashboard = new DashboardActions();

    @Given("The user has opened the browser")
    public void user_has_opened_the_browser() {
        HelperClass.setUpDriver();
    }

    @And("The user is on the application login page")
    public void user_has_navigated_to_login_page() {
        // HelperClass.openPage(url);
        String url = ConfigReader.getProperty("app.url");
        WebDriver driver = HelperClass.getDriver();
        driver.get(url);
    }

    @When("The user enters username {string} and password {string}")
    public void go_to_home_page(String userName, String passWord) {
        objLogin.login(userName, passWord);
    }

    @And("The user clicks on the login button")
    public void click_login_button() {

        // Click Login button
        objLogin.clickedLoginButton();

    }

    @And("The user is navigated to the dashboard page")
    public void user_is_navigated_to_dashboard() {
        try {
            // 1. Buat objek wait dengan durasi timeout (misalnya 10 detik)
            WebDriverWait wait = new WebDriverWait(HelperClass.getDriver(), Duration.ofSeconds(10));

            // 2. Perintahkan Selenium untuk menunggu sampai URL mengandung
            // "dashboard-pelajar"
            wait.until(ExpectedConditions.urlContains("dashboard-pelajar"));

            // 3. (Opsional) Lakukan assertion tambahan jika diperlukan
            // Jika baris di atas berhasil, berarti URL sudah benar.
            String currentUrl = HelperClass.getDriver().getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("dashboard-pelajar"),
                    "URL tidak mengandung 'dashboard-pelajar' setelah menunggu.");

        } catch (Exception e) {
            // Jika setelah 10 detik URL tidak juga berubah, tes akan gagal di sini.
            // Cetak URL terakhir untuk debugging.
            String finalUrl = HelperClass.getDriver().getCurrentUrl();
            Assert.fail("User tidak diarahkan ke halaman dashboard. URL terakhir: " + finalUrl, e);
        }
    }

    @And("The user should be able to see navigation bar for pelajar")
    public void user_should_see_all_sidebar_items() {
        List<String> expectedItems = Arrays.asList(
                "Beranda",
                "Kursus Saya",
                "Riwayat Kuis");
        List<String> actualItems = objDashboard.getSidebarItems();
        Assert.assertEquals(actualItems, expectedItems, "Sidebar items do not match!");
    }

    @And("The user should be able to see photo and username")
    public void userShouldSeePhotoAndUsername() {
        // Cek foto user
        Assert.assertTrue(objDashboard.isUserPhotoDisplayed(), "User photo is not displayed");

        // Cek username
        Assert.assertTrue(objDashboard.isUsernameDisplayed(), "Username is not displayed");
    }

    // @Then("The user should be able to see {string} notification message")
    // public void verifyErrorMessage(String notificationType) {
    // String actualErrorMessage = objLogin.getErrorMessage();
    // String expectedErrorMessage = "Kesalahan!";
    //
    // // Verifikasi pesan error untuk "un-successful login"
    // if (notificationType.equals("Kesalahan!")) {
    // Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message
    // does not match!");
    // } else {
    // Assert.fail("Unknown notification type: " + notificationType);
    // }
    // }

    @And("The user clicks on Kursus Saya navigation")
    public void click_kursus_saya_navigation() {
        objDashboard.clickedKursusSayaNav();
    }

    @And("The user clicks on Beranda navigation")
    public void click_beranda_navigation() {
        objDashboard.clickedBerandaNav();
    }

    @Then("Page title should be displayed")
    public void page_title_displayed() {
        String title = objDashboard.getCourseTitle();
        Assert.assertEquals(title, "Kursus", "The title is not match!");
    }

    @And("Course list created by the instructor should be visible")
    public void course_visible() {
        // Assert.assertTrue(objDashboard.isCourseListVisible(), "There's no course
        // displayed");

        List<String> listCourses = objDashboard.getCourses();
        Assert.assertFalse(listCourses.isEmpty(), "There's no course displayed");
    }

    @And("The user clicks on username")
    public void click_on_username() {
        objDashboard.clickOnSubMenuUsername();
    }

    @And("Sub menu keluar is displayed")
    public void keluar_displayed() {
        Assert.assertTrue(objDashboard.isKeluarDisplayed(), "Sub menu Keluar is not displayed");
    }

    @Then("The user should be redirected to the login page")
    public void The_user_should_be_redirected_to_the_login_page() {
        String url = ConfigReader.getProperty("app.url");
        WebDriver driver = HelperClass.getDriver();
        driver.get(url);
    }

    @Given("User is on the login page")
    public void user_is_on_login_page() {
        HelperClass.setUpDriver();
        String url = ConfigReader.getProperty("app.url");
        HelperClass.getDriver().get(url);
    }

    @When("User leaves the username field empty")
    public void user_leaves_username_field_empty() {
        objLogin.clearEmail();
    }

    @When("User enters a valid password")
    public void user_enters_valid_password() {
        String password = ConfigReader.getProperty("app.password");
        objLogin.enterPassword(password);
    }

    @Then("System should display error message {string}")
    public void system_should_display_error_message(String expectedMessage) {
        String actualMessage = objLogin.getErrorMessage();
        if (actualMessage.isEmpty()) {
            actualMessage = objLogin.getValidationMessage();
        }
        Assert.assertTrue(actualMessage.contains(expectedMessage),
            "Expected error message to contain '" + expectedMessage + "' but got: " + actualMessage);
    }

    @Given("User is logged in")
    public void user_is_logged_in() {
        HelperClass.setUpDriver();
        String url = ConfigReader.getProperty("app.url");
        HelperClass.getDriver().get(url);
        objLogin.login(ConfigReader.getProperty("app.username"), ConfigReader.getProperty("app.password"));
        objLogin.clickedLoginButton();
    }

    @Then("User should see the course list")
    public void user_should_see_course_list() {
        List<String> courses = objDashboard.getCourses();
        Assert.assertFalse(courses.isEmpty(), "Course list should not be empty");
    }

    @Then("The page title should contain {string}")
    public void page_title_should_contain(String title) {
        String pageTitle = objDashboard.getPageTitle();
        Assert.assertTrue(pageTitle.toLowerCase().contains(title.toLowerCase()),
            "Page title should contain '" + title + "'. Actual: " + pageTitle);
    }
}
