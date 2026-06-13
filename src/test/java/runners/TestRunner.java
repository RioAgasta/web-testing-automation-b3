package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "classpath:features",
    glue = {"stepdefinitions", "hooks"},
    plugin = {
        "html:target/cucumber-reports.html",
        "json:target/cucumber-reports.json"
    }
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
