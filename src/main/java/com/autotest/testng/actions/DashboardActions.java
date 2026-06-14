package com.autotest.testng.actions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import com.autotest.testng.locators.DashboardLocators;
import com.autotest.testng.model.Course;
import com.autotest.testng.utils.HelperClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DashboardActions {
  DashboardLocators dashboardLocators = null;

  public DashboardActions() {

    this.dashboardLocators = new DashboardLocators();

    PageFactory.initElements(HelperClass.getDriver(), dashboardLocators);
  }

  // Get the Username from Home Page
  public String getHomePageText() {
    return dashboardLocators.homePageUserName.getText();
  }

  public String getPageTitle() {
    return dashboardLocators.homePageTitle.getText();
  }

  public List<String> getSidebarItems() {
    try {
      // Jika locator tidak menemukan elemen, dashboardLocators.courses akan menjadi
      // list kosong.
      if (dashboardLocators.navBarItems.isEmpty()) {
        return new ArrayList<>(); // Kembalikan list kosong secara eksplisit.
      }

      List<String> navBarTexts = new ArrayList<>();
      for (WebElement item : dashboardLocators.navBarItems) {
        navBarTexts.add(item.getText());
      }
      return navBarTexts;
    } catch (NoSuchElementException e) {
      // Sebagai pengaman tambahan, jika terjadi error saat mengakses list,
      // kembalikan list kosong.
      return new ArrayList<>();
    }
  }

  public boolean isUserPhotoDisplayed() {
    try {
      return dashboardLocators.userPhoto.isDisplayed();
    } catch (NoSuchElementException e) {
      // Jika elemen tidak ada di DOM, maka ia tidak ditampilkan.
      return false;
    }
  }

  public boolean isUsernameDisplayed() {
    try {
      return dashboardLocators.homePageUserName.isDisplayed();
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public void clickedKursusSayaNav() {
    // Click Login button
    dashboardLocators.kursusSayaNav.click();
  }

  public void clickedBerandaNav() {
    // Click Login button
    dashboardLocators.berandaNav.click();
  }

  public String getCourseTitle() {
    try {
      return dashboardLocators.courseTitle.getText();
    } catch (NoSuchElementException e) {
      // Jika elemen tidak ditemukan, kembalikan "kosong"
      // agar bisa di-assert di step definition.
      return "kosong";
    }
  }

  public List<String> getCourses() {
    try {
      // Jika locator tidak menemukan elemen, dashboardLocators.courses akan menjadi
      // list kosong.
      if (dashboardLocators.courses.isEmpty()) {
        return new ArrayList<>(); // Kembalikan list kosong secara eksplisit.
      }

      List<String> courses = new ArrayList<>();
      for (WebElement item : dashboardLocators.courses) {
        courses.add(item.getText());
      }
      return courses;
    } catch (NoSuchElementException e) {
      // Sebagai pengaman tambahan, jika terjadi error saat mengakses list,
      // kembalikan list kosong.
      return new ArrayList<>();
    }
  }

  // public boolean isCourseListVisible() {
  // return !dashboardLocators.courses.isEmpty();
  // }

  public void clickOnSubMenuUsername() {
    try {
      dashboardLocators.subMenuUsername.click();
    } catch (NoSuchElementException e) {
      // Jika elemen tidak ditemukan, abaikan aksi klik.
      // Bisa ditambahkan log untuk debugging.
      System.out.println("Element 'subMenuUsername' tidak ditemukan, aksi klik dilewati.");
    }
  }

  public boolean isKeluarDisplayed() {
    try {
      return dashboardLocators.subMenuKeluar.isDisplayed();
    } catch (NoSuchElementException e) {
      // Jika elemen tidak ada di DOM, maka ia tidak ditampilkan.
      return false;
    }
  }

  // public void selectCourseByName(String courseName) {
  // try {
  // // XPath ini mencari <h6> yang berisi nama kursus,
  // // lalu memilih "induk"-nya yaitu <div> utama dari card tersebut.
  // String dynamicXpath =
  // String.format("//h6[normalize-space()='%s']/ancestor::div[@class='card
  // custom-card']", courseName);

  // WebElement courseCard =
  // HelperClass.getDriver().findElement(By.xpath(dynamicXpath));

  // courseCard.click();

  // } catch (NoSuchElementException e) {
  // System.err.println("Course card dengan nama '" + courseName + "' tidak
  // ditemukan.");
  // throw new AssertionError("Gagal menemukan kursus: " + courseName, e);
  // }
  // }

  public void selectCourseByName(String courseName) {
    try {
      // This dynamic XPath is more robust. It finds an <h6> with the exact course
      // name,
      // then finds the main clickable card container for that title.
      // Using contains(@class, 'custom-card') is slightly more reliable than an exact
      // match.
      String dynamicXpath = String.format(
          "//h6[normalize-space()='%s']/ancestor::div[contains(@class, 'custom-card')]",
          courseName);

      System.out.println("Attempting to find course card with XPath: " + dynamicXpath);
      WebElement courseCard = HelperClass.getDriver().findElement(By.xpath(dynamicXpath));

      Actions actions = new Actions(HelperClass.getDriver());
      actions.moveToElement(courseCard).click().perform();

      System.out.println("Successfully clicked on course: " + courseName);

    } catch (NoSuchElementException e) {
      System.err.println("Course card with name '" + courseName + "' was not found on the page.");
      // Fail the test with a clear message if the course isn't found
      throw new AssertionError("Failed to find course: " + courseName, e);
    }
  }

  public List<Course> getAllDisplayedCourses() {
    List<Course> uiCourses = new ArrayList<>();
    List<WebElement> courseCardElements = dashboardLocators.courses;

    // System.out.println("Found " + courseCardElements.size() + " course card
    // containers on the UI.");

    for (WebElement cardElement : courseCardElements) {
      try {
        WebElement courseImageElement = cardElement.findElement(dashboardLocators.courseImage);
        WebElement courseNameElement = cardElement.findElement(dashboardLocators.courseName);
        WebElement instructorNameElement = cardElement.findElement(dashboardLocators.instructorName);

        String imageUrl = courseImageElement.getDomProperty("src");
        String namaCourse = courseNameElement.getText().trim();
        String instructorName = instructorNameElement.getText().trim();

        int idCourse = 0; // Not available from current UI card HTML
        int idPengajar = 0; // Not available as ID from current UI card HTML
        String enrollmentKey = ""; // Not available from current UI card HTML
        String deskripsi = ""; // Not available from current UI card HTML

        Course uiCourse = new Course(
            idCourse,
            idPengajar,
            namaCourse,
            enrollmentKey,
            imageUrl,
            deskripsi,
            instructorName);
        uiCourses.add(uiCourse);

      } catch (Exception e) {
        System.err.println("Error extracting course data from a UI card: " + e.getMessage());
      }
    }
    return uiCourses;
  }

  public void clickOnPemantauan() {
    dashboardLocators.pemantauanNav.click();
  }

  public void clickNavigationMenu(String menuName) {
    try {
      // Find the navigation item by its text
      WebElement navItem = dashboardLocators.navBarItems.stream()
          .filter(item -> item.getText().equalsIgnoreCase(menuName))
          .findFirst()
          .orElseThrow(() -> new NoSuchElementException("Navigation item '" + menuName + "' not found"));

      // Click the found navigation item
      navItem.click();
    } catch (NoSuchElementException e) {
      System.err.println("Navigation item '" + menuName + "' not found: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error clicking navigation item '" + menuName + "': " + e.getMessage());
    }
  }

  public void clickLogout() {
    try {
      clickOnSubMenuUsername(); // Klik untuk membuka dropdown
      if (isKeluarDisplayed()) {
        dashboardLocators.subMenuKeluar.click(); // Klik opsi "Keluar"
      } else {
        System.err.println("Sub menu 'Keluar' tidak ditemukan setelah membuka dropdown.");
      }
    } catch (NoSuchElementException e) {
      System.err.println("Gagal menemukan elemen untuk logout: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error saat mencoba logout: " + e.getMessage());
    }
  }
}