

Feature: Verify contact form functionality

  Background:
    Given I set the test environment to "tool-shop"
    When I launch "uat.url" website

  @test1
  Scenario: Submit contact form with valid data - Return
    When I save the value of "First_</Random(5)>" in variable "$firstName$"
    When I save the value of "Last_</Random(5)>" in variable "$lastName$"
    When I save the value of "</Comment(20)>" in variable "$comment$"
    Given I am on the "home" page
    Then I navigated to "contact" page
    Given I am on the "contact" page
    When I fill in the contact form with:
      | firstName  | testName       |
      | lastName   | lastName       |
      | email      | test@gmail.com |
      | subject    | Return         |
      | message    | $comment$      |
      | attachment | test.txt       |
    And I click the Submit button
    Then I should see a confirmation message "Thanks for your message! We will contact you shortly."