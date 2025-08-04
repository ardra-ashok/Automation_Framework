package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportHandler {

    public static ExtentReports createExtentReport(String scenarioName) {

        String updatedScenarioName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_");

        // Base folder path for scenario reports
        String baseFolder = "target/HTML Reports/" + updatedScenarioName;
        String timestamp = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date());
        String runFolder = baseFolder + "/" + timestamp;

        new File(runFolder).mkdirs();
        String reportPath = runFolder + "/Report.html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Execution Report for " + scenarioName);

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", System.getProperty("user.name"));
        return extent;
    }

}