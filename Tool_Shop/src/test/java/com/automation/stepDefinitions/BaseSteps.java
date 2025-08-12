package com.automation.stepDefinitions;

import com.automation.helpers.Helpers;
import configs.PropertyManager;
import core.WebDriverHandler;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utils.ReportHandler;

import java.util.HashMap;
import java.util.Map;

public class BaseSteps extends Helpers {
    private WebDriverHandler webDriverHandler;
    ReportHandler reportHandler;

    private static Map<String, Object> scenarioDataMap = new HashMap<>();
    public static Map<String, Object> getScenarioDataMap() {
        if (scenarioDataMap == null) {
            scenarioDataMap = new HashMap<>();
        }
        return scenarioDataMap;
    }

    public static void setScenarioVariable(String scenarioVariable, Object dataObject){
        if (getScenarioDataMap().containsKey(scenarioVariable)){
          return;
        }
        getScenarioDataMap().put(scenarioVariable,dataObject );
    }

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
        PropertyManager.loadTestPropertiesFromYML(replaceParamWithVariable(env));
    }

    @When("I save the value of {string} in variable {string}")
    public void iSaveTheValueOfInVariable(String inputVariable, String variableName) {
        PropertyManager.setScenarioVariable(variableName,replaceParamWithVariable(inputVariable));
    }
}
