package com.automation.stepDefinitions;

import configs.PropertyManager;
import core.WebDriverHandler;
import io.cucumber.java.en.*;

public class HomePageSteps {

    private WebDriverHandler webDriverHandler;


    public HomePageSteps(WebDriverHandler webDriverHandler){
        this.webDriverHandler = webDriverHandler;
    }
    public HomePageSteps(){}

    @Given("^I set the test environment to \"([^\"]*)\"$")
    public void i_set_the_test_environment_to(String env) throws Exception {
        PropertyManager.loadTestPropertiesFromYML(System.getProperty(env,"parabank"));

    }

    @When("^I launch \"([^\"]*)\" website$")
    public void i_launch_website(String url) {
        String baseUrl = PropertyManager.getEnvironmentVariable(url);
        webDriverHandler.navigateToUrl(baseUrl);

    }

    @Given("I am on the registration page")
    public void i_am_on_the_registration_page() {
        System.out.println("hello 1");
    }
    @When("I register with the following details")
    public void i_register_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        System.out.println("hello 2");
    }
    @Then("I should see a message {string}")
    public void i_should_see_a_message(String string) {
        System.out.println("hello 3");
    }

}
