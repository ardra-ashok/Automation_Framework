package com.automation.stepDefinitions;

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
    private ReportHandler report = ReportHandler.getInstance();

    public ContactPageSteps(toolShopServices toolShopServices){
        this.toolShopServices = toolShopServices;
    }

    @When("I fill in the contact form with:")
    public void i_fill_in_the_contact_form_with(DataTable dataTable) throws Exception {
        Map<String, String> dataMap = new HashMap<>(dataTable.asMap(String.class, String.class));
        dataMap = replaceParamWithVariable(dataMap);
        toolShopServices.fillContactForm(dataMap);
//        report.addStep("pass", "I save the value of \"" + value + "\" in variable \"" + variable + "\"", "path/to/screenshot.png");
    }

    @And("I click the Submit button")
    public void iClickTheSubmitButton() throws Exception {
        toolShopServices.clickOnSubmit();
    }

    @Then("^I should see a confirmation message \"([^\"]*)\"$")
    public void iShouldSeeAConfirmationMessage(String successMsg) throws Exception {
        toolShopServices.verifyConfirmMessage(successMsg);
    }
}
