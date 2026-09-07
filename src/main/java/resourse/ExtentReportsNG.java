package resourse;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentReportsNG {

    public static ExtentReports  getReport() {
        // Implementation for generating the report
        //what is the path to add at it the report
        String path = System.getProperty("user.dir")+"//Reports//report.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Automation ofr the website");
        reporter.config().setDocumentTitle("Test Results");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Shimaa");
        return extent;

    }


}
