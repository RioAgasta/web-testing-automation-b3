@TC_1_0_2
Feature: Login Functionality

  Scenario: Check login is unsuccessful when the username field is left empty
    Given User is on the login page
    When User leaves the username field empty
    And User enters a valid password
    And User clicks the login button
    Then System should display error message "Kesalahan! Gagal masuk, email harus diisi!"
