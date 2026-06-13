package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CourseOverviewPage extends BasePage {

    @FindBy(className = "course-info")
    private WebElement courseInfoSection;

    @FindBy(xpath = "//input[contains(@placeholder,'Kode') or contains(@placeholder,'kode') or contains(@name,'code')]")
    private WebElement enrollmentCodeField;

    @FindBy(xpath = "//button[contains(text(),'Daftar') or contains(text(),'daftar')]")
    private WebElement daftarButton;

    @FindBy(css = ".swal2-html-container")
    private WebElement swalErrorText;

    @FindBy(css = ".swal2-confirm")
    private WebElement swalConfirmButton;

    @FindBy(className = "back-button-text")
    private WebElement backButton;

    public CourseOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void clearEnrollmentCodeField() {
        enrollmentCodeField.clear();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }))",
            enrollmentCodeField
        );
    }

    public void enterEnrollmentCode(String code) {
        enrollmentCodeField.sendKeys(code);
    }

    public void clickDaftarButton() {
        daftarButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return swalErrorText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return swalErrorText.getText();
    }

    public void closeErrorMessage() {
        try {
            swalConfirmButton.click();
        } catch (Exception ignored) {
        }
    }

    public boolean isOnCourseOverviewPage() {
        try {
            return courseInfoSection.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnrollmentFormPresent() {
        try {
            return enrollmentCodeField.isDisplayed() && daftarButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void backToDashboard() {
        String url = com.autotest.testng.utils.ConfigReader.getProperty("app.url");
        driver.get(url + "dashboard-pelajar");
    }
}
