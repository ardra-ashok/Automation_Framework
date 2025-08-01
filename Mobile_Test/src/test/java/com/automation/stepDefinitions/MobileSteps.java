package com.automation.stepDefinitions;

import com.automation.helpers.Helpers;
import core.MobileHandler;
import io.cucumber.java.en.Given;

import java.util.HashMap;
import java.util.Map;

public class MobileSteps extends Helpers {
    private MobileHandler mobileHandler;

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

    public BaseSteps(MobileHandler mobileHandler){
        this.mobileHandler = mobileHandler;
    }

    @Given("I launch the {string} app on mobile with following")
    public void i_launch_the_app_on_mobile_with_following(String string, io.cucumber.datatable.DataTable dataTable) {

    }
}
