@Logout
Feature: Logout

Background:
Given The user has opened the browser
And The user is on the application login page

@SuccessfulLogout
Scenario: Verify that user can successfully log out
When The user clicks on the logout button
Then The user should be redirected to the login page