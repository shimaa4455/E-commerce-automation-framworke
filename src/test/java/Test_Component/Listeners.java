package Test_Component;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestListener;
import resourse.ExtentReportsNG;

import java.io.IOException;

public class Listeners extends Browser implements ITestListener {

    //hold the entry for me report
    ExtentTest test;
    ExtentReports extent = ExtentReportsNG.getReport();
    @Override
    public void onTestStart(org.testng.ITestResult result) {
        // Code to execute when a test starts
        //set entry in the report for every methode got execute
        test =extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(org.testng.ITestResult result) {
        // Code to execute when a test succeeds
        test.log(Status.PASS,"Test "+result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(org.testng.ITestResult result) {
        // Code to execute when a test fails
        test.log(Status.FAIL, result.getThrowable());
        String filePath;
        try {
            filePath = getScreenShot(result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(org.testng.ITestResult result) {
        // Code to execute when a test is skipped
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(org.testng.ITestResult result) {
        // Code to execute when a test fails but is within success percentage
    }

    @Override
    public void onStart(org.testng.ITestContext context) {
        // Code to execute before any test starts
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        // Code to execute after all tests have finished
    }
}
