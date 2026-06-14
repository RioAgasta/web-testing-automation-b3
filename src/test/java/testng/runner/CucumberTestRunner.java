package testng.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;

@CucumberOptions(tags = "", features = "src/test/features", glue = "testng.definitions", plugin = {
    "pretty",
    "html:reports/test_report.html",
    "json:target/cucumber/test_report.json",
    "html:target/test_report.html",
}, snippets = SnippetType.CAMELCASE)

public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
