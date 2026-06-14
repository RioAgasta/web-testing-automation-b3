@LoginScenario
Feature: Login Functionality
  Scenario: Login berhasil sebagai pelajar
    Given User berada di halaman login "https://polban-space.cloudias79.com/jtk-learn"
    When User mengisi email field dengan "fajri.pelajar@kelompok4.com"
    And User mengisi password field dengan "admin123"
    And User klik tombol submit
    Then User berhasil login dan diarahkan ke dashboard
    And User melihat greeting "Hai, Fajri Pelajar!"
