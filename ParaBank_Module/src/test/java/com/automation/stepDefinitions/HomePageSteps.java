package com.automation.stepDefinitions;

import core.WebDriverHandler;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import services.ParaBankServices;

import java.util.HashMap;
import java.util.Map;

public class HomePageSteps {

    private final ParaBankServices paraBankServices;

    public HomePageSteps(ParaBankServices paraBankServices){
        this.paraBankServices = paraBankServices;
    }

    @Given("I am on the registration page")
    public void i_am_on_the_registration_page() {
        paraBankServices.navigateToRegister();
    }

    @When("I register with the following details")
    public void i_register_with_the_following_details(DataTable dataTable) throws Exception {
        Map<String, String> dataMap;
        dataMap = new HashMap<>(dataTable.asMap(String.class, String.class));
        paraBankServices.enterRegistrationDetails(dataMap);
    }


    @Then("^I should see a message \"([^\"]*)\"$")
    public void i_should_see_a_message(String successMsg) throws Exception {
        paraBankServices.verifyAccountCreation(successMsg);
    }

}
