@EnterCourseScenario
Feature: Enter Existing Course
  Scenario: User pelajar melanjutkan kursus yang sudah ada
    Given User berada di halaman dashboard "https://polban-space.cloudias79.com/jtk-learn/dashboard-pelajar"
    When User klik menu "Kursus Saya"
    Then User diarahkan ke halaman my courses
    When User memilih kursus dengan nama "cobakursus"
    And User klik tombol "Lanjut Kursus"
    Then User diarahkan ke halaman course "cobakursus"
    And User melihat progress kursus
