package com.automation.stepDefinitions;

import com.automation.context.ScenarioContext;
import com.automation.helpers.Helpers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import services.toolShopServices;

public class HomePageSteps extends Helpers {

    private final toolShopServices toolShopServices;
    private final ScenarioContext scenarioContext;

    public HomePageSteps(toolShopServices toolShopServices,ScenarioContext scenarioContext){
        this.toolShopServices = toolShopServices;
        this.scenarioContext = scenarioContext;
    }

    @Given("^I am on the \"([^\"]*)\" page$")
    public void i_am_on_the_page(String pageName) throws Exception {
        pageName = replaceParamWithVariable(pageName);
        toolShopServices.verifyPage(pageName);
        scenarioContext.setStepDescription(String.format("Verified I am on "+pageName +" page"));
    }

    @Then("^I navigated to \"([^\"]*)\" page$")
    public void i_navigated_to_page(String pageName) {
        pageName = replaceParamWithVariable(pageName);
       toolShopServices.navigateTo(pageName);
       scenarioContext.setStepDescription(String.format("I navigated to "+pageName+" page"));
    }
}
