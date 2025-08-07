

Feature: Verify contact form functionality

  Background:
    Given I set the test environment to "$env$"
    When I launch "uat.url" website

  @test1
  Scenario: Submit contact form with valid data - Return
    Given I am on the "home" page
    Then I navigated to "contact" page
    Given I am on the "contact" page
    When I fill in the contact form with:
      | firstName  | "First_</Random(5)>" |
      | lastName   | "Last_</Random(5)>"  |
      | email      | test@gmail.com       |
      | subject    | Return               |
      | message    | </Comment(20)>       |
      | attachment | test.txt             |
    And I click the Submit button
    Then I should see a confirmation message "Thanks for your message! We will contact you shortly."