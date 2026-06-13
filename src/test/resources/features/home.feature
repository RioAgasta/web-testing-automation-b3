Feature: Home Page
  As a logged-in user, I want to view the home page
  so that I can see the list of courses

  Scenario: Display course list after login
    Given User is logged in
    Then User should see the course list
    And The page title should contain "Beranda"
