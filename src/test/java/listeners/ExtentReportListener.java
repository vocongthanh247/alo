package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class ExtentReportListener implements ITestListener {
    private static Map<String, ExtentTest> testMap = new HashMap<>();
    private ByteArrayOutputStream consoleOutput;
    private PrintStream originalSystemOut;
    private PrintStream originalSystemErr;

    @Override
    public void onStart(ITestContext context) {
        // Khởi tạo ExtentReports thông qua ExtentManager
        ExtentManager.setExtentVneCSGT(); // hoặc setExtentVPA tùy project

        // Capture console output
        consoleOutput = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        originalSystemErr = System.err;
        PrintStream ps = new PrintStream(consoleOutput);
        System.setOut(ps);
        System.setErr(ps);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.extent.createTest(result.getMethod().getMethodName());
        testMap.put(result.getMethod().getMethodName(), test);
        test.info(MarkupHelper.createLabel("Test Started", ExtentColor.BLUE));
        consoleOutput.reset(); // Reset console capture for new test
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = testMap.get(result.getMethod().getMethodName());
        test.log(Status.PASS, MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));

        // Add console logs with custom styling
        String consoleLog = consoleOutput.toString();
        if (!consoleLog.isEmpty()) {
            test.info(MarkupHelper.createCodeBlock(consoleLog, "text"));
        }

        // Add execution time with badge
        long duration = result.getEndMillis() - result.getStartMillis();
        test.info(MarkupHelper.createLabel(
                "Test Duration: " + duration/1000.0 + " seconds",
                ExtentColor.BLUE
        ));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = testMap.get(result.getMethod().getMethodName());
        test.log(Status.FAIL, MarkupHelper.createLabel("Test Failed", ExtentColor.RED));

        // Add error details
        if (result.getThrowable() != null) {
            test.fail(result.getThrowable());
        }

        // Add console logs with custom styling
        String consoleLog = consoleOutput.toString();
        if (!consoleLog.isEmpty()) {
            test.info(MarkupHelper.createCodeBlock(consoleLog, "text"));
        }

        // Add execution time with badge
        long duration = result.getEndMillis() - result.getStartMillis();
        test.info(MarkupHelper.createLabel(
                "Test Duration: " + duration/1000.0 + " seconds",
                ExtentColor.BLUE
        ));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = testMap.get(result.getMethod().getMethodName());
        test.log(Status.SKIP, MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE));

        String consoleLog = consoleOutput.toString();
        if (!consoleLog.isEmpty()) {
            test.info(MarkupHelper.createCodeBlock(consoleLog, "text"));
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        // Restore original console output
        System.setOut(originalSystemOut);
        System.setErr(originalSystemErr);

        // Add test suite summary
        ExtentTest summary = ExtentManager.extent.createTest("Test Suite Summary");
        summary.info(MarkupHelper.createCodeBlock(
                String.format("""
                Total Tests: %d
                Passed: %d
                Failed: %d
                Skipped: %d
                """,
                        context.getAllTestMethods().length,
                        context.getPassedTests().size(),
                        context.getFailedTests().size(),
                        context.getSkippedTests().size()
                )
        ));

        // Add total execution time
        long duration = context.getEndDate().getTime() - context.getStartDate().getTime();
        summary.info(MarkupHelper.createLabel(
                "Total Execution Time: " + duration/1000.0 + " seconds",
                ExtentColor.BLUE
        ));

        ExtentManager.endReport();
    }

    // Helper method to log messages to report
    public static void logToReport(String message) {
        System.out.println(message); // This will be captured in console output
        ExtentTest currentTest = getCurrentTest();
        if (currentTest != null) {
            currentTest.info(message);
        }
    }

    private static ExtentTest getCurrentTest() {
        String testName = Thread.currentThread().getStackTrace()[3].getMethodName();
        return testMap.get(testName);
    }
}