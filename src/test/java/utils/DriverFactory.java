package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--disable-password-manager-reauthentication");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-password-leak-detection");
            options.addArguments("--disable-password-generation");
            options.addArguments("--disable-password-import");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation", "save-password", "password-manager-redirect", "enable-password-change"});
            options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
                put("credentials_enable_service", false);
                put("profile.password_manager_enabled", false);
                put("profile.default_content_setting_values.notifications", 2);
                put("safebrowsing.enabled", false);
                put("safebrowsing.esb.enabled", false);
                put("profile.password_manager_leak_detection", false);
            }});
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
