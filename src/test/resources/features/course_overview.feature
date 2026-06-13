@TC_6_0_1
Feature: Course Overview Functionality

  Scenario: Check that submitting an empty enrollment code shows a validation error
    Given Student is logged in and on the Course Overview page
    When Student leaves the enrollment code input field empty
    And Student clicks the "Daftar" button
    Then System displays error message "Silakan masukkan kode pendaftaran."
    And Student remains on the Course Overview page
