package testng.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;

/**
 * Test Runner untuk Software Testing JTK Learn
 *
 * Cara menjalankan:
 * - Semua scenario: mvn test
 * - Scenario Login saja: mvn test -Dcucumber.filter.tags="@LoginScenario"
 * - Scenario Enter Course saja: mvn test -Dcucumber.filter.tags="@EnterCourseScenario"
 */
@CucumberOptions(
    tags = "@LoginScenario or @EnterCourseScenario", // Run semua scenario
    features = "src/test/features",
    glue = "testng.definitions",
    plugin = {
        "pretty",
        "html:reports/test_report.html",
        "json:target/cucumber/test_report.json",
        "html:target/test_report.html",
    },
    snippets = SnippetType.CAMELCASE
)

public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
