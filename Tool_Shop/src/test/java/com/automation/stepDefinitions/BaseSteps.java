package com.automation.stepDefinitions;

import com.automation.context.ScenarioContext;
import com.automation.helpers.Helpers;
import configs.PropertyManager;
import core.WebDriverHandler;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class BaseSteps extends Helpers {

    private static final Logger LOG = LoggerFactory.getLogger(BaseSteps.class);
    private WebDriverHandler webDriverHandler;
    private ScenarioContext scenarioContext;

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

    public BaseSteps(WebDriverHandler webDriverHandler,ScenarioContext scenarioContext){
        this.webDriverHandler = webDriverHandler;
        this.scenarioContext = scenarioContext;
    }
    @When("^I launch \"([^\"]*)\" website$")
    public void i_launch_website(String url) {
        String baseUrl = replaceParamWithVariable(PropertyManager.getEnvironmentVariable(url));
        LOG.info(String.format("Launching url: "+baseUrl));
        webDriverHandler.navigateToUrl(baseUrl);
        scenarioContext.setStepDescription(String.format("I navigated to "+baseUrl));
    }

    @Given("^I set the test environment to \"([^\"]*)\"$")
    public void i_set_the_test_environment_to(String env) throws Exception {
        env = replaceParamWithVariable(env);
        LOG.info(String.format("Set the test environment: "+env));
        PropertyManager.loadTestPropertiesFromYML(env);
        scenarioContext.setStepDescription(String.format("I set the environment to "+env));
    }

    @When("I save the value of {string} in variable {string}")
    public void iSaveTheValueOfInVariable(String inputVariable, String variableName) {
        LOG.info(String.format("Saving the value of : "+inputVariable+" in " +variableName));
        PropertyManager.setScenarioVariable(variableName,replaceParamWithVariable(inputVariable));
    }
}
