package com.automation.stepDefinitions;

import com.automation.helpers.Helpers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import services.HerokuServices;

public class herokuAppSteps extends Helpers {

    private final HerokuServices herokuServices;

    public herokuAppSteps(HerokuServices herokuServices){
        this.herokuServices = herokuServices;
    }

    @Given("^I am on \"([^\"]*)\" page$")
    public void i_am_on_homePage(String pageName) {
        herokuServices.navigateTo(replaceParamWithVariable(pageName));
    }

    @When("^I navigated to \"([^\"]*)\"$")
    public void i_navigated_to(String pageName) {
        herokuServices.navigateTo(replaceParamWithVariable(pageName));
    }

    @Then("^I verified I am on the \"([^\"]*)\" page$")
    public void i_verified_i_am_on_the_page(String pageName) throws Exception {
        herokuServices.verifyPageContent(replaceParamWithVariable(pageName));
    }


}
