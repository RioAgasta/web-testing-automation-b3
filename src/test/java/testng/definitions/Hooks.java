package testng.definitions;

import com.autotest.testng.utils.HelperClass;
import io.cucumber.java.AfterAll;

public class Hooks {

  @AfterAll
  public static void tearDown() {
    HelperClass.tearDown();
  }
}
