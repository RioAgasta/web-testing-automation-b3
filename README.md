JTK Learn — Web Automation Testing
=================================

Automated web UI tests for the JTK Learn demo application using Java, TestNG and Cucumber.

What this project contains
--------------------------

- Maven-based Java test suite using TestNG and Cucumber.
- Example page actions under `src/main/java/com/autotest/testng/actions`.
- Cucumber feature files under `src/test/features`.
- Browser drivers in the `drivers/` folder (Chrome and Edge).

Prerequisites
-------------

- Java 17 or newer installed and `JAVA_HOME` set.
- Maven 3.6+ installed and available on `PATH`.
- A supported browser installed (Chrome or Edge).

Note: This repo includes local browser driver binaries in the `drivers/` folder. Ensure the drivers are executable on your OS.

Setup
-----

1. Open the project in your IDE (IntelliJ IDEA, VS Code, etc.).
2. Make sure the driver compatible with your OS exists in the `drivers/` folder.
3. Optionally configure any application properties in `src/main/resources/application.properties`.

Running the tests
-----------------

Profiles
--------

| Profile | Command | Target URL |
|---|---|---|
| `fix` (default) | `mvn test` | `/jtk-learn/` |
| `buggy` | `mvn test -P buggy` | `/jtk-learn-buggy/` |

Run the full test suite with Maven:

```bash
mvn clean test
```

Or use the convenience PowerShell script (sets Java & Maven automatically):

```powershell
.\run-tests.ps1
```

Or run TestNG via your IDE using the `testng.xml` suite file at the project root.

To run a single Cucumber feature or scenario, use your IDE test runner or run the TestNG/Cucumber runner class at `src/test/java/testng/runner/CucumberTestRunner.java`.

Reports
-------

- HTML & TestNG reports are generated under `target/` and `target/cucumber-report-html/`.
- There are example reports in the workspace `reports/` and `target/cucumber-html-reports/`.

Common report files:

- `target/test_report.html` — project test report
- `target/surefire-reports/` — raw TestNG results
- `target/cucumber-report-html/` — Cucumber HTML report

Project structure (key folders)
------------------------------

- `src/main/java` — helper classes, page actions, utils
- `src/test/features` — Cucumber feature files
- `src/test/java` — step definitions and test runners
- `drivers/` — browser drivers for local execution
