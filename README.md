This is a robust and scalable UI automation framework built with Selenium and Java. It is designed to provide an efficient and maintainable solution for testing web applications. 
The framework leverages a modular architecture and best practices like the Page Object Model (POM) to ensure test code is clean, reusable, and easy to manage.

Features
* Page Object Model (POM): Ensures a clear separation between test logic and web element locators, making tests more readable and easier to maintain.
* Selenium WebDriver: Utilizes Selenium for browser automation, supporting various web browsers.
* Data-Driven Testing: The framework supports data-driven testing by externalizing test data into a dedicated supportData module. This allows the same test script to be executed with different data sets, 
  enhancing test coverage and reusability.
* Extent Reporting: Integrates a comprehensive reporting mechanism to generate detailed, visually appealing test reports. Each test run produces a rich report with step-by-step details.
* Automated Screenshots: Captures screenshots automatically on test failures and attaches them to the test reports, providing clear evidence of the failure point.
* Modular Design: The framework is structured into logical modules (framework, ParaBank, supportData) to promote reusability and scalability.
* TestNG Framework: Uses TestNG for test execution, providing features like annotations, test groups, and parallel execution. 
* Cross-Browser Testing: Configurable to run tests across different browsers (e.g., Chrome, Firefox, Edge).