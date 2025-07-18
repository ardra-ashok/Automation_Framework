package com.automation.stepDefinitions;

import com.automation.helpers.Helpers;
import configs.PropertyManager;
import core.WebDriverHandler;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class BaseSteps extends Helpers {
    private WebDriverHandler webDriverHandler;


    public BaseSteps(WebDriverHandler webDriverHandler){
        this.webDriverHandler = webDriverHandler;
    }

    @When("^I launch \"([^\"]*)\" website$")
    public void i_launch_website(String url) {
        String baseUrl = PropertyManager.getEnvironmentVariable(url);
        webDriverHandler.navigateToUrl(baseUrl);
    }

    @Given("^I set the test environment to \"([^\"]*)\"$")
    public void i_set_the_test_environment_to(String env) throws Exception {
        PropertyManager.loadTestPropertiesFromYML(System.getProperty(env,"parabank"));
    }

    @When("I save the value of {string} in variable {string}")
    public void iSaveTheValueOfInVariable(String arg0, String str) {
//        PropertyManager.setScenarioVariable();
    }
}
