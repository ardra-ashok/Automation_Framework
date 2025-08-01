package com.automation.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import configs.CoreParams;
import core.WebDriverHandler;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ReportHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

    private ExtentReports extent;
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private String runTimestamp;
    private String scenarioName;

    @Before
    public void setUp(Scenario scenario) throws IOException {
        this.scenarioName = scenario.getName();
        this.runTimestamp = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date());

        extent = ReportHandler.createExtentReport(scenarioName);
        ExtentTest scenarioTest = extent.createTest(scenarioName);
        test.set(scenarioTest);

        CoreParams.loadCoreParams("");
        WebDriverHandler.initializeDriver();
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) {
        try {
            String currentUrl = WebDriverHandler.getDriver().getCurrentUrl();
            if (currentUrl != null && !currentUrl.equals("data:,")) {
                File screenshotFile = WebDriverHandler.takeScreenshotFile(scenarioName, runTimestamp);
                if (screenshotFile != null) {
                    String relativePath = "screenshots/" + screenshotFile.getName();
                    test.get().info("Step Screenshot",
                            MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
                }
            } else {
                test.get().info("Initial Set up step");
            }
        } catch (Exception e) {
            test.get().warning("Screenshot failed: " + e.getMessage());
        }
    }


    @After
    public void tearDown(Scenario scenario) {
        try {
            File screenshotFile = WebDriverHandler.takeScreenshotFile(scenarioName, runTimestamp);

            if (screenshotFile != null) {
                String relativePath = "screenshots/" + screenshotFile.getName();
                test.get().info("Final Scenario Screenshot",
                        MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
            }

            if (scenario.isFailed()) {
                test.get().fail("Scenario failed: " + scenarioName);
            } else {
                test.get().pass("Scenario passed: " + scenarioName);
            }
        } catch (Exception e) {
            test.get().warning("Screenshot in @After failed: " + e.getMessage());
        }

        extent.flush();
        if(WebDriverHandler.getDriver()!=null)
            WebDriverHandler.closeDriver();
    }

}
