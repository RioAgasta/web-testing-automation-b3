package utils;

import com.autotest.testng.utils.ConfigReader;
import pages.HomePage;
import pages.LoginPage;

public class LoginUtil {

    private LoginUtil() {
    }

    public static void loginAsDefaultUser() {
        DriverFactory.getDriver().get(ConfigReader.getProperty("app.url"));
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.enterEmail(ConfigReader.getProperty("app.username"));
        loginPage.enterPassword(ConfigReader.getProperty("app.password"));
        loginPage.clickLoginButton();
    }

    public static HomePage loginAndGoToHome() {
        loginAsDefaultUser();
        return new HomePage(DriverFactory.getDriver());
    }
}
