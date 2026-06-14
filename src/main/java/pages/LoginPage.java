package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//form//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(id = "swal2-title")
    private WebElement errorTitle;

    @FindBy(id = "swal2-html-container")
    private WebElement errorMessageBody;

    @FindBy(xpath = "//*[contains(@class,'error') or contains(@class,'validation') or contains(@class,'invalid-feedback')]")
    private WebElement validationMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void clearEmailField() {
        emailField.clear();
    }

    public void enterEmail(String email) {
        sendKeys(emailField, email);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public String getErrorMessage() {
        try {
            String title = isDisplayed(errorTitle) ? errorTitle.getText() : "";
            String body = isDisplayed(errorMessageBody) ? errorMessageBody.getText() : "";
            if (!title.isEmpty() && !body.isEmpty()) {
                return title + " " + body;
            }
            return title + body;
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return isDisplayed(errorTitle) || isDisplayed(errorMessageBody);
        } catch (Exception e) {
            return false;
        }
    }

    public String getValidationMessage() {
        try {
            if (isDisplayed(validationMessage)) {
                return validationMessage.getText();
            }
        } catch (Exception e) {
        }
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String msg = (String) js.executeScript(
                "return arguments[0].validationMessage;", emailField
            );
            if (msg != null && !msg.isEmpty()) {
                return msg;
            }
        } catch (Exception e) {
        }
        return "";
    }

    public boolean isValidationMessageDisplayed() {
        return !getValidationMessage().isEmpty();
    }
}
