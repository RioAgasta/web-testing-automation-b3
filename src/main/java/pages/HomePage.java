package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[contains(@class,'nav-item')]//*[contains(@class,'active')]")
    private WebElement pageTitle;

    @FindBy(className = "nav-item")
    private List<WebElement> navItems;

    @FindBy(className = "profile-img")
    private WebElement userPhoto;

    @FindBy(className = "courses-title")
    private WebElement courseSectionTitle;

    @FindBy(css = ".col-12.col-sm-6.col-lg-3")
    private List<WebElement> courses;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public int getNavItemCount() {
        return navItems.size();
    }

    public boolean isUserPhotoDisplayed() {
        return userPhoto.isDisplayed();
    }

    public String getCourseSectionTitle() {
        return courseSectionTitle.getText();
    }

    public int getCourseCount() {
        return courses.size();
    }

    public boolean isCourseDisplayed() {
        return !courses.isEmpty();
    }

    public void selectCourseByIndex(int index) {
        courses.get(index).click();
    }
}
