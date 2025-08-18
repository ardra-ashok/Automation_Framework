package com.automation.stepDefinitions;

import com.automation.context.ScenarioContext;
import com.automation.helpers.Helpers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.toolShopServices;

public class HomePageSteps extends Helpers {

    private static final Logger LOG = LoggerFactory.getLogger(HomePageSteps.class);
    private final toolShopServices toolShopServices;
    private final ScenarioContext scenarioContext;

    public HomePageSteps(toolShopServices toolShopServices,ScenarioContext scenarioContext){
        this.toolShopServices = toolShopServices;
        this.scenarioContext = scenarioContext;
    }

    @Given("^I am on the \"([^\"]*)\" page$")
    public void i_am_on_the_page(String pageName) throws Exception {
        pageName = replaceParamWithVariable(pageName);
        LOG.info(String.format("Verify I am on page "+pageName));
        toolShopServices.verifyPage(pageName);
        scenarioContext.setStepDescription(String.format("Verified I am on "+pageName +" page"));
    }

    @Then("^I navigated to \"([^\"]*)\" page$")
    public void i_navigated_to_page(String pageName) {
        pageName = replaceParamWithVariable(pageName);
        LOG.info(String.format("I navigate to page "+pageName));
       toolShopServices.navigateTo(pageName);
       scenarioContext.setStepDescription(String.format("I navigated to "+pageName+" page"));
    }
}
