//package utils;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import io.cucumber.java.Scenario;
//import io.cucumber.plugin.EventListener;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.net.URI;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Stream;
//
//
//
//public class ReportHandler {
//
//    private static ReportHandler instance;
//    private static boolean reportStarted = false;
//
//
//    private String featureName;
//    private String date;
//    private String environment;
//    private List<TestCase> testCases;
//
//    private String currentTestCaseId;
//    private String currentTestCaseTitle;
//    private String currentTestCaseDescription;
//    private List<Step> currentSteps;
//
//
//    public record Step(String status, String description, String screenshot) {}
//    public record TestCase(String id, String title, String status, String description, String bugId, String failureDetails, List<Step> steps) {}
//
//    private ReportHandler() {
//        this.testCases = new ArrayList<>();
//    }
//
//    public static ReportHandler getInstance() {
//        if (instance == null) {
//            instance = new ReportHandler();
//        }
//        return instance;
//    }
//
//    public static ExtentReports createExtentReport(String scenarioName) {
//
//        String updatedScenarioName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_");
//        String baseFolder = "target/HTML Reports/" + updatedScenarioName;
//        String timestamp = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date());
//        String runFolder = baseFolder + "/" + timestamp;
//
//        new File(runFolder).mkdirs();
//        String reportPath = runFolder + "/Report.html";
//
//        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
//        spark.config().setDocumentTitle("Automation Report");
//        spark.config().setReportName("Execution Report for " + scenarioName);
//
//        ExtentReports extent = new ExtentReports();
//        extent.attachReporter(spark);
//        extent.setSystemInfo("Environment", "QA");
//        extent.setSystemInfo("User", System.getProperty("user.name"));
//        return extent;
//    }
//
//    public static String getFeatureName(Scenario scenario) {
//        try {
//            String uriString = scenario.getUri().toString();
//            Path featureFilePath = Paths.get(new URI(uriString));
//            try (Stream<String> lines = Files.lines(featureFilePath)) {
//                return lines.filter(line -> line.trim().startsWith("Feature:")).findFirst()
//                        .map(line -> line.substring("Feature:".length()).trim())
//                        .orElse("Unknown Feature");
//            }
//        } catch (Exception e) {
//            System.err.println("Error extracting feature name: " + e.getMessage());
//            return "Unknown Feature";
//        }
//    }
//
//    public void startReport(String featureName, String date, String environment) {
//        if (!reportStarted) {
//            this.featureName = featureName;
//            this.date = date;
//            this.environment = environment;
//            this.testCases.clear();
//            reportStarted = true;
//        }
//    }
//
//    public void startTestCase(String id, String title, String description) {
//        this.currentTestCaseId = id;
//        this.currentTestCaseTitle = title;
//        this.currentTestCaseDescription = description;
//        this.currentSteps = new ArrayList<>();
//    }
//
//    public void addStep(String status, String description, String screenshot) {
//        if (this.currentSteps == null) {
//            throw new IllegalStateException("No test case is active. Call startTestCase() first.");
//        }
//        this.currentSteps.add(new Step(status, description, screenshot));
//    }
//
//    public void endTestCase(String status, String bugId, String failureDetails) {
//        if (this.currentTestCaseId == null) {
//            throw new IllegalStateException("No test case is active. Call startTestCase() first.");
//        }
//        TestCase newTestCase = new TestCase(
//                this.currentTestCaseId,
//                this.currentTestCaseTitle,
//                status,
//                this.currentTestCaseDescription,
//                bugId,
//                failureDetails,
//                this.currentSteps
//        );
//        this.testCases.add(newTestCase);
//
//        this.currentTestCaseId = null;
//        this.currentTestCaseTitle = null;
//        this.currentTestCaseDescription = null;
//        this.currentSteps = null;
//    }
//
//    public String generateReport() {
//        if (!reportStarted) {
//            throw new IllegalStateException("Report has not been started. Call startReport() first.");
//        }
//
//        StringBuilder htmlBuilder = new StringBuilder();
//
//
//        long passedCount = this.testCases.stream().filter(tc -> "pass".equals(tc.status())).count();
//        long failedCount = this.testCases.stream().filter(tc -> "fail".equals(tc.status())).count();
//        long skippedCount = this.testCases.stream().filter(tc -> "skipped".equals(tc.status())).count();
//
//        htmlBuilder.append("<!DOCTYPE html>\n")
//                .append("<html lang=\"en\">\n")
//                .append("<head>\n")
//                .append("    <meta charset=\"UTF-8\">\n")
//                .append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
//                .append("    <title>Detailed QA Test Report - ").append(this.featureName).append("</title>\n")
//                .append("    <style>\n")
//                .append("        body { font-family: Arial, sans-serif; line-height: 1.6; margin: 20px; background-color: #f4f4f4; }\n")
//                .append("        .container { max-width: 900px; margin: auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }\n")
//                .append("        h1, h2, h3 { color: #333; }\n")
//                .append("        .summary-table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }\n")
//                .append("        .summary-table th, .summary-table td { border: 1px solid #ddd; padding: 10px; text-align: left; }\n")
//                .append("        .summary-table th { background-color: #f2f2f2; }\n")
//                .append("        .test-case { border: 1px solid #ccc; margin-bottom: 15px; border-radius: 5px; background-color: #fafafa; }\n")
//                .append("        .test-case-header { padding: 15px; background-color: #e9e9e9; cursor: pointer; display: flex; justify-content: space-between; align-items: center; }\n")
//                .append("        .test-case-header h3 { margin: 0; font-size: 1.2em; }\n")
//                .append("        .test-case-status { font-weight: bold; }\n")
//                .append("        .pass { color: green; }\n")
//                .append("        .fail { color: red; }\n")
//                .append("        .skipped { color: orange; }\n")
//                .append("        .toggle-icon { font-size: 1.5em; }\n")
//                .append("        .test-case-content { padding: 15px; border-top: 1px solid #ccc; display: none; }\n")
//                .append("        .step-list { list-style-type: none; padding-left: 0; }\n")
//                .append("        .step-list li { margin-bottom: 10px; padding: 5px 0; border-bottom: 1px dotted #ccc; }\n")
//                .append("        .screenshot-link { display: inline-block; margin-left: 10px; color: #007BFF; text-decoration: none; }\n")
//                .append("        .screenshot-link:hover { text-decoration: underline; }\n")
//                .append("    </style>\n")
//                .append("</head>\n")
//                .append("<body>\n")
//                .append("    <div class=\"container\">\n")
//                .append("        <h1>QA Test Report</h1>\n")
//                .append("        <h3>Test Feature: ").append(this.featureName).append("</h3>\n")
//                .append("        <p><strong>Date:</strong> ").append(this.date).append("</p>\n")
//                .append("        <p><strong>Environment:</strong> ").append(this.environment).append("</p>\n")
////                .append("        <p><strong>Tester:</strong> ").append(this.tester).append("</p>\n")
//                .append("        <hr>\n")
//                .append("        <h2>Summary</h2>\n")
//                .append("        <table class=\"summary-table\">\n")
//                .append("            <tr><th>Total Test Cases</th><td>").append(this.testCases.size()).append("</td></tr>\n")
//                .append("            <tr><th>Passed</th><td class=\"pass\">").append(passedCount).append("</td></tr>\n")
//                .append("            <tr><th>Failed</th><td class=\"fail\">").append(failedCount).append("</td></tr>\n")
//                .append("            <tr><th>Skipped</th><td class=\"skipped\">").append(skippedCount).append("</td></tr>\n")
//                .append("        </table>\n")
//                .append("        <hr>\n")
//                .append("        <h2>Detailed Test Cases</h2>\n");
//
//        for (TestCase tc : this.testCases) {
//            htmlBuilder.append("        <div class=\"test-case\">\n")
//                    .append("            <div class=\"test-case-header\">\n")
//                    .append("                <h3>").append(tc.title()).append("</h3>\n")
//                    .append("                <span class=\"test-case-status ").append(tc.status()).append("\">").append(tc.status().toUpperCase()).append("</span>\n")
//                    .append("                <span class=\"toggle-icon\">+</span>\n")
//                    .append("            </div>\n")
//                    .append("            <div class=\"test-case-content\">\n")
//                    .append("                <p><strong>Description:</strong> ").append(tc.description()).append("</p>\n");
//            if (tc.failureDetails() != null) {
//                htmlBuilder.append("                <p><strong>Failure Details:</strong> ").append(tc.failureDetails()).append("</p>\n");
//            }
//            if (tc.bugId() != null) {
//                htmlBuilder.append("                <p><strong>Bug ID:</strong> ").append(tc.bugId()).append("</p>\n");
//            }
//            htmlBuilder.append("                <ul class=\"step-list\">\n");
//
//            for (Step step : tc.steps()) {
//                String statusClass = step.status().toLowerCase();
//                String statusText = "pass".equals(statusClass) ? "Passed" : "Failed";
//                htmlBuilder.append("                    <li>\n")
//                        .append("                        <strong>").append(step.description()).append("</strong>\n")
//                        .append("                        <span class=\"").append(statusClass).append("\">(").append(statusText).append(")</span>\n")
//                        .append("                        <a href=\"").append(step.screenshot()).append("\" target=\"_blank\" class=\"screenshot-link\">View Screenshot</a>\n")
//                        .append("                    </li>\n");
//            }
//            htmlBuilder.append("                </ul>\n")
//                    .append("            </div>\n")
//                    .append("        </div>\n");
//        }
//
//
//        htmlBuilder.append("    </div>\n")
//                .append("    <script>\n")
//                .append("        document.addEventListener('DOMContentLoaded', (event) => {\n")
//                .append("            const headers = document.querySelectorAll('.test-case-header');\n")
//                .append("            headers.forEach(header => {\n")
//                .append("                header.addEventListener('click', () => {\n")
//                .append("                    const content = header.nextElementSibling;\n")
//                .append("                    const icon = header.querySelector('.toggle-icon');\n")
//                .append("                    if (content.style.display === 'block') {\n")
//                .append("                        content.style.display = 'none';\n")
//                .append("                        icon.textContent = '+';\n")
//                .append("                    } else {\n")
//                .append("                        content.style.display = 'block';\n")
//                .append("                        icon.textContent = '-';\n")
//                .append("                    }\n")
//                .append("                });\n")
//                .append("            });\n")
//                .append("        });\n")
//                .append("    </script>\n")
//                .append("</body>\n")
//                .append("</html>");
//
//        return htmlBuilder.toString();
//    }
//
//    public void writeHtmlToFile(String filename) {
//        System.out.println(filename);
//        String htmlContent = generateReport();
//        try {
//            createParentDirectories(filename);
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
//                writer.write(htmlContent);
//            }
//        } catch (IOException e) {
//            System.err.println("Failed to write report file: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    private void createParentDirectories(String filePath) throws IOException {
//        Path path = Paths.get(filePath);
//        Path parentDir = path.getParent();
//        if (parentDir != null && !Files.exists(parentDir)) {
//            Files.createDirectories(parentDir);
//        }
//    }
//
//}