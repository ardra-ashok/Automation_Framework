

Feature: Verify contact form functionality

  Background:
    Given I set the test environment to "tool-shop"
    When I launch "uat.url" website

  Scenario: Contact Form Validation - Return
    When I save the value of "First_</Random(5)>" in variable "$firstName$"
    When I save the value of "Last_</Random(5)>" in variable "$lastName$"
    Given I am on the "home" page
    Then I navigated to "contact" page
    Given I am on the "contact" page
    When I entered the following details contact form
      | firstName    | testName                                                                                                                                                                                                                                                                            |
      | lastName     | lastName                                                                                                                                                                                                                                                                            |
      | emailAddress | test@gmail.com                                                                                                                                                                                                                                                                      |
      | Subject      | Sydney                                                                                                                                                                                                                                                                              |
      | Message      | I would like to return a product I purchased recently as it does not meet my expectations. The item is unused, in its original packaging, and I have included the bill with the return. Please guide me through the return process and let me know if any further steps are needed. |
      | attachment   | file.txt                                                                                                                                                                                                                                                                            |
    Then I submit for a return for the product purchased