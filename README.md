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

Project Structure
The framework is organized into the following key modules:
- framework: Contains the core components of the automation framework. This includes base classes for initializing WebDriver, 
    utility methods, listeners for reporting, and other common functionalities.
- BaseTest.java: The base class for all test scripts.
- utils/: Contains utility classes for file I/O, properties, etc.
- listeners/: Contains the Extent Reporting listener for generating reports.
- ParaBank: Houses the Page Object Model and test scripts for the ParaBank website.
- pages/: Contains Page Object classes for different web pages (e.g., LoginPage.java, AccountPage.java).
- tests/: Contains the actual test scripts written using TestNG.
- supportData: This is the dedicated module for managing all test data. This separation of data from the code is a key principle of data-driven testing.
- testData/: A directory to store various test data files (e.g., .csv, .json, .properties) for different test scenarios.
- dataReaders/: Contains utility classes for reading data from external files, making it easy for tests to access the data.