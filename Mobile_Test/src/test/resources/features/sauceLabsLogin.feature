Feature: Login Feature Validation

  Background: User open the Saucelabs Login Page
    Given I launch the "SauceLabs-demo" app on mobile with following
      | platform     | $platform$   |
      | deviceType   | $deviceType$ |
      | deviceName   | $deviceName$ |
      | appiumServer | $server$     |
      | installApp   | $installApp$ |

    Scenario: Successful Login with Standard User
      Given I am on the Sauce Labs login page
      When I enter "standard_user" into the username field
      And I enter "secret_sauce" into the password field
      And I click the "Login" button
      Then I should be redirected to the "Products" page
      And I should see the "Products" title