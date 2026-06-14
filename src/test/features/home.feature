Feature: Home Page

  Scenario: Display course list after login
    Given User is logged in
    Then User should see the course list
    And The page title should contain "Kursus"
