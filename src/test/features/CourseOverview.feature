@CourseOverview
Feature: Course Overview

Background:
Given The user has opened the browser
And The user is on the application login page
And The user enters username "rioTesting@gmail.com" and password "admin"
And The user clicks on the login button
And The user is navigated to the dashboard page

@RegistrationFailedNoMaterialAndQuiz
Scenario: Verify that course enrollment fails when the course has no materials and quizzes
When The user navigates to the course "Odoo Course Advanced" information page
And The user enters the enrollment code "odoo12345"
And The user clicks on the enroll button
Then The system should display the error message "Pendaftaran kursus gagal, kursus setidaknya memiliki minimal satu buah materi dan kuis."
And The user should remain on the course information page
