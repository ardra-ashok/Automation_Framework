

Feature: Verify the Login/Register Page functionality

  Background:
    Given I set the test environment to "env"

  Scenario: Verify the Register page functionality
    When I launch "uat.url" website
    Given I am on the registration page
    When I register with the following details
      | firstName     | Ardra       |
      | lastName      | Ashok       |
      | address       | 123 Street  |
      | city          | Sydney      |
      | state         | NSW         |
      | zipCode       | 2000        |
      | phoneNumber   | 0400000000  |
      | ssn           | 123-45-6789 |
      | username      | ardrauser   |
      | password      | secret123   |
    Then I should see a message "Your account was created successfully. You are now logged in."


