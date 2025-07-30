Feature: Verify different functionality on Internet-HerokuApp

  Background:
    Given I set the test environment to "env"
    When I launch "uat.url" website

    Scenario:Verify A/B Testing
      Given I am on "Home" page
      When I navigated to "AB-Testing"
      Then I verified I am on the "AB-Testing" page

    Scenario:
