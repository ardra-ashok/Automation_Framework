This project is a modular Selenium Test Automation Framework built with Java, Maven, and TestNG, following best practices for scalability and maintainability. 
It uses a multi-module structure, with a `common` module that houses reusable components such as WebDriver management (`DriverManager`), configuration handling (`ConfigLoader`), 
and a base test setup (`BaseTest`). Each test module contains its own Page Object classes, test cases, and configuration files, 
while the `runners` module is dedicated to executing test suites using TestNG. The `parent-pom.xml` manages all modules and shared dependencies, enabling clean separation of concerns, easy maintenance, and consistent setup across different test modules.
