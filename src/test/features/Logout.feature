@Logout
Feature: Logout

Background:
Given The user has opened the browser
And The user is on the application login page
And The user enters username "rioTesting@gmail.com" and password "admin"
And The user clicks on the login button
And The user is navigated to the dashboard page

@SuccessfulLogout
Scenario: Verify that user can successfully log out
When The user clicks on the logout button
Then The user should be redirected to the login page