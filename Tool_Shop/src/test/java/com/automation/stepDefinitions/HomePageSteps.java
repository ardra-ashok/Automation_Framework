package com.automation.stepDefinitions;

import com.automation.helpers.Helpers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import services.toolShopServices;

public class HomePageSteps extends Helpers {

    private final toolShopServices toolShopServices;

    public HomePageSteps(toolShopServices toolShopServices){
        this.toolShopServices = toolShopServices;
    }

    @Given("^I am on the \"([^\"]*)\" page$")
    public void i_am_on_the_page(String pageName) throws Exception {
        toolShopServices.verifyPage(replaceParamWithVariable(pageName));
    }

    @Then("^I navigated to \"([^\"]*)\" page$")
    public void i_navigated_to_page(String pageName) {
       toolShopServices.navigateTo(replaceParamWithVariable(pageName));
    }


}
