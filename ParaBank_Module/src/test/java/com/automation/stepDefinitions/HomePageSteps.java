package com.automation.stepDefinitions;

import com.automation.helpers.Helpers;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import services.ParaBankServices;

import java.util.HashMap;
import java.util.Map;

public class HomePageSteps extends Helpers {

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
        Map<String, String> dataMap = new HashMap<>(dataTable.asMap(String.class, String.class));
        dataMap = replaceParamWithVariable(dataMap);
        paraBankServices.enterRegistrationDetails(dataMap);
    }

    @Then("^I should see a message \"([^\"]*)\"$")
    public void i_should_see_a_message(String successMsg) throws Exception {
        paraBankServices.verifyAccountCreation(replaceParamWithVariable(successMsg));
    }
    @Given("I am on the login page")
    public void i_am_on_the_login_page() throws Exception {
        paraBankServices.navigateToLogin();
    }
    @When("I enter valid username and password")
    public void i_enter_valid_username_and_password(DataTable dataTable) throws Exception {
        Map<String, String> dataMap = new HashMap<>(dataTable.asMap(String.class, String.class));
        dataMap = replaceParamWithVariable(dataMap);
        paraBankServices.enterLoginDetails(dataMap);
    }
    @When("I click the login button")
    public void i_click_the_login_button() throws Exception {
        paraBankServices.clickLogin();
    }
    @Then("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_the_dashboard() throws Exception {
        paraBankServices.verifyRedirectToDashboard();
    }
    @Then("I should see a welcome message with my {string}")
    public void i_should_see_a_welcome_message_with_my(String firstName) throws Exception {
        paraBankServices.verifyLoginSuccess(replaceParamWithVariable(firstName));
    }

}
