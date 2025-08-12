This is a robust and scalable UI automation framework built with Selenium and Java. It is designed to provide an efficient and maintainable solution for testing web applications. 
The framework leverages a modular architecture and best practices like the Page Object Model (POM) to ensure test code is clean, reusable, and easy to manage.

Features

* __Page Object Model (POM)__ : Ensures a clear separation between test logic and web element locators, making tests more readable and easier to maintain.
* __Selenium WebDriver__: Utilizes Selenium for browser automation, supporting various web browsers.
* __Data-Driven Testing__: The framework supports data-driven testing by externalizing test data into a dedicated supportData module. This allows the same test script to be executed with different data sets, 
  enhancing test coverage and reusability.
* __Extent Reporting__: Integrates a comprehensive reporting mechanism to generate detailed, visually appealing test reports. Each test run produces a rich report with step-by-step details.
* __Automated Screenshots__: Captures screenshots automatically on test failures and attaches them to the test reports, providing clear evidence of the failure point.
* __Modular Design__: The framework is structured into logical modules (framework, ParaBank, supportData) to promote reusability and scalability.
* __Cross-Browser Testing__: Configurable to run tests across different browsers (e.g., Chrome, Firefox, Edge).

Project Structure
The framework is organized into the following key modules:
- __Framework__: Contains the core components of the automation framework. This includes base classes for initializing WebDriver, 
    utility methods, listeners for reporting, and other common functionalities.
- __BaseTest.java__: The base class for all test scripts.
- __utils/__: Contains utility classes for file I/O, properties, etc.
- __listeners/__: Contains the Extent Reporting listener for generating reports.
- __ParaBank__: Houses the Page Object Model, runners and test scripts for the ParaBank website.
- __Tool_shop__: Houses the Page Object Model, runner  and test scripts for the toolshop website.
- __pages/__: Contains Page Object classes for different web pages (e.g., LoginPage.java, AccountPage.java).
- __supportData__: This is the dedicated module for managing all test data. This separation of data from the code is a key principle of data-driven testing.
- __testData/__: A directory to store various test data files (e.g., .csv, .json, .properties) for different test scenarios.
- __dataReaders/__: Contains utility classes for reading data from external files, making it easy for tests to access the data.