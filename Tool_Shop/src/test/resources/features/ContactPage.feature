

Feature: Verify contact form functionality

  Background:
    Given I set the test environment to "$env$"
    When I launch "uat.url" website
    Given I am on the "home" page
    Then I navigated to "contact" page
    Given I am on the "contact" page


  Scenario: Submit contact form with valid data - Return
    When I fill in the contact form with:
      | firstName  | "First_</Random(5)>" |
      | lastName   | "Last_</Random(5)>"  |
      | email      | test@gmail.com       |
      | subject    | Return               |
      | message    | </Comment(20)>       |
      | attachment | test.txt             |
    And I click the Submit button
    Then I should see a confirmation message "Thanks for your message! We will contact you shortly."


  Scenario Outline: Required field validation
    When I fill in the contact form with:
      | firstName  | <firstName>  |
      | lastName   | <lastName>   |
      | email      | <Email>      |
      | subject    | <subject>    |
      | message    | <message>    |
      | attachment | <attachment> |
    And I click the Submit button
    Then I should see an error "<error_message>"

    Examples:
      | firstName            | lastName            | Email          | subject  | message        | attachment | error_message                         |
      |                      | "Last_</Random(5)>" | test@gmail.com | Return   | </Comment(20)> | test.txt   | First name is required                |
      | "First_</Random(5)>" |                     | test@gmail.com | Return   | </Comment(20)> | test.txt   | Last name is required                 |
      | "First_</Random(5)>" | "Last_</Random(5)>" |                | Return   | </Comment(20)> | test.txt   | Email is required                     |
      | "First_</Random(5)>" | "Last_</Random(5)>" | test@.com      | Return   | </Comment(20)> | test.txt   | Email format is invalid               |
      | "First_</Random(5)>" | "Last_</Random(5)>" | test@gmail.com |          | </Comment(20)> | test.txt   | Subject is required                   |
      | "First_</Random(5)>" | "Last_</Random(5)>" | test@gmail.com | Payments |                | test.txt   | Message is required                   |
      | "First_</Random(5)>" | "Last_</Random(5)>" | test@gmail.com | Payments | </Comment(5)>  | test.txt   | Message must be minimal 50 characters |
