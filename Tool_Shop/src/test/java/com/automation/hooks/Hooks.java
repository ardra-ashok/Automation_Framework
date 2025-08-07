package com.automation.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import configs.CoreParams;
import configs.PropertyManager;
import core.WebDriverHandler;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ReportHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Hooks {

    private ReportHandler report = ReportHandler.getInstance();
    private static boolean shutdownHookRegistered = false;

    private ExtentReports extent;
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private String runTimestamp;
    private String scenarioName;
    private String currentDate;
    private String featureFileName;
    private String reportPath;
    private String screenshotsDir;


    @Before
    public void setUp(Scenario scenario) throws Exception {
        CoreParams.loadCoreParams("");
        if (!shutdownHookRegistered) {
            registerShutdownHook();
            shutdownHookRegistered = true;
        }

        this.scenarioName = scenario.getName();
        this.runTimestamp = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date());
        currentDate = new SimpleDateFormat("MMMM d, yyyy").format(new Date());

        this.reportPath = CoreParams.REPORTS_DIR + this.scenarioName + "/" + this.runTimestamp + "/QA_Test_Report.html";
        this.screenshotsDir = CoreParams.SCREENSHOTS_DIR + this.scenarioName + "/" + this.runTimestamp + "/";
        featureFileName = ReportHandler.getFeatureName(scenario);

        report.startReport(
                featureFileName,
                currentDate,
                System.getProperty("env")
        );

        report.startTestCase(
                scenario.getId(),
                scenario.getName(),
                "Scenario from feature file: " + this.scenarioName
        );



        String browserStr = PropertyManager.getSystemProperty("browser",null) != null ? PropertyManager.getSystemProperty("browser") : "chrome";
        WebDriverHandler.setBrowser(WebDriverHandler.Browsers.valueOf(browserStr.toUpperCase()));
        WebDriverHandler.closeDriver();
        WebDriverHandler.initializeDriver();
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) {
        try {

            String currentUrl = WebDriverHandler.getDriver().getCurrentUrl();
            if (currentUrl == null || currentUrl.startsWith("data:") || currentUrl.isEmpty()) {
                return;
            }

            File screenshotFile = WebDriverHandler.takeScreenshot(scenario.getName());
            String relativePathForReport = (screenshotFile != null) ?  "../../../screenshots/" + screenshotFile.getName() : "";

            String stepDescription = "Step executed successfully.";
            report.addStep(
                    "pass",
                    stepDescription,
                    relativePathForReport
            );
        } catch (Exception e) {
            report.addStep("fail", "Failed to get screenshot or report step: " + e.getMessage(), "path/to/error_screenshot.png");
        }
    }


    @After
    public void tearDown(Scenario scenario) {

        String status = scenario.isFailed() ? "fail" : "pass";
        String failureDetails = scenario.isFailed() ? scenario.getStatus().toString() : null;
        report.endTestCase(
                status,
                null,
                failureDetails
        );

        if(WebDriverHandler.getDriver()!=null)
            WebDriverHandler.closeDriver();
    }

    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            report.writeHtmlToFile(this.reportPath);
            System.out.println("Final report generation triggered by shutdown hook.");
        }));
    }

}
