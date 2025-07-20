
Feature: Verify the Login/Register Page functionality

  Background:
    Given I set the test environment to "env"
    When I launch "uat.url" website

  Scenario: Verify the Register page functionality
    When I save the value of "</Random(12)>" in variable "$userName$"
    Given I am on the registration page
    When I register with the following details
      | firstName   | $userName$  |
      | firstName   | testFName   |
      | lastName    | lastName    |
      | address     | 123 Street  |
      | city        | Sydney      |
      | state       | NSW         |
      | zipCode     | 2000        |
      | phoneNumber | 0400000000  |
      | ssn         | 123-45-6789 |
      | username    | $userName$  |
      | password    | secret123   |
    Then I should see a message "Your account was created successfully. You are now logged in."

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I save the value of "testFName" in variable "$userName$"
    When I enter username and password
      | username | $userName$ |
      | password | secret123 |
    And I click the login button
    Then I should be redirected to the dashboard
    And I should see a welcome message with my "$userName$"

  Scenario: Login with invalid credentials
    Given I am on the login page
    When I enter username and password
      | username | wronguser@example.com |
      | password | WrongPassword         |
    And I click the login button
    Then I should see an error message "The username and password could not be verified."

