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

    @Given("^I am on the \"([^\"]*)\" page$")
    public void i_am_on_the_page(String pageName) throws Exception {
        paraBankServices.navigateTo(replaceParamWithVariable(pageName));
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

    @When("I enter username and password")
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
    @Then("^I should see a welcome message with my \"([^\"]*)\"$")
    public void i_should_see_a_welcome_message_with_my(String firstName) throws Exception {
        paraBankServices.verifyLoginSuccess(replaceParamWithVariable(firstName));
    }

    @Then("^I should see an error message \"([^\"]*)\"$")
    public void i_should_see_an_error_message(String errorMsg) throws Exception {
        paraBankServices.verifyErrorMessage(errorMsg);
    }

    @When("I click the login button without entering credentials")
    public void i_click_the_login_button_without_entering_credentials() throws Exception {
        paraBankServices.clickLogin();
    }

    @Then("^the password field should be of type \"([^\"]*)\"$")
    public void the_password_field_should_be_of_type(String expectedType) throws Exception {
        paraBankServices.checkType(expectedType);
    }

    @When("^I click on the \"([^\"]*)\" link$")
    public void i_click_on_the_link(String clickOnLink) throws InterruptedException {
        paraBankServices.clickOnLink(clickOnLink);
    }

    @Then("^I should be redirected to the \"([^\"]*)\" page$")
    public void i_should_be_redirected_to_the_password_recovery_page(String pageName) {
        paraBankServices.verifyNavigation(replaceParamWithVariable(pageName));
    }

    @Then("I verify all the links on the page are working")
    public void i_verify_all_the_links_on_the_page_are_working() throws Exception {
        paraBankServices.verifyLinkStatus();
    }

}
