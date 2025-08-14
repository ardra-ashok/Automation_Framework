package com.automation.stepDefinitions;

import com.automation.context.ScenarioContext;
import com.automation.helpers.Helpers;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import services.toolShopServices;
import utils.ReportHandler;

import java.util.HashMap;
import java.util.Map;

public class ContactPageSteps extends Helpers {
    private final toolShopServices toolShopServices;
    private final ScenarioContext scenarioContext;

    public ContactPageSteps(toolShopServices toolShopServices, ScenarioContext scenarioContext){
        this.toolShopServices = toolShopServices;
        this.scenarioContext = scenarioContext;
    }

    @When("I fill in the contact form with:")
    public void i_fill_in_the_contact_form_with(DataTable dataTable) throws Exception {
        Map<String, String> dataMap = new HashMap<>(dataTable.asMap(String.class, String.class));
        dataMap = replaceParamWithVariable(dataMap);
        toolShopServices.fillContactForm(dataMap);
        scenarioContext.setStepDescription("Filled the contact form");
    }

    @And("I click the Submit button")
    public void iClickTheSubmitButton() throws Exception {
        toolShopServices.clickOnSubmit();
        scenarioContext.setStepDescription("Clicked on the Submit Button");
    }

    @Then("^I should see a confirmation message \"([^\"]*)\"$")
    public void iShouldSeeAConfirmationMessage(String successMsg) throws Exception {
        toolShopServices.verifyConfirmMessage(successMsg);
        scenarioContext.setStepDescription("Verified the confirmation message");
    }

    @Then("^I should see an error \"([^\"]*)\"$")
    public void i_should_see_an_error(String errorMessage) throws Exception {
        toolShopServices.verifyErrorMessage(errorMessage);
        scenarioContext.setStepDescription("Verified the error message");
    }

}
