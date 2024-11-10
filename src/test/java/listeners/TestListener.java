package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private static ExtentReports extent;
    private static ExtentTest test;
    private static final String REPORT_PATH = "test-output/reports/";

    @Override
    public void onStart(ITestContext context) {
        // Tạo timestamp cho tên folder
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        // Tạo thư mục reports nếu chưa tồn tại
        new File(REPORT_PATH + timestamp).mkdirs();

        // Khởi tạo ExtentReports
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_PATH + timestamp + "/extent-report.html");
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("Test Execution Report - " + timestamp);
        extent.attachReporter(spark);

        System.out.println("=== Test Suite bắt đầu ===");
        System.out.println("Thời gian bắt đầu: " + new Date());
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.log(Status.INFO, "Test bắt đầu: " + result.getName());
        System.out.println("\n=== Test case bắt đầu: " + result.getName() + " ===");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test thành công");
        System.out.println("✓ Test thành công: " + result.getName());
        System.out.println("Thời gian thực thi: " + (result.getEndMillis() - result.getStartMillis()) + "ms");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test thất bại");
        test.fail(result.getThrowable());

        System.out.println("✗ Test thất bại: " + result.getName());
        System.out.println("Chi tiết lỗi: " + result.getThrowable().getMessage());

        // Chụp ảnh khi test fail
        try {
            Object testClass = result.getInstance();
            if (testClass instanceof PlaywrightTest) {
                page = ((PlaywrightTest) testClass).getPage();

                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String screenshotPath = REPORT_PATH + "screenshots/" + result.getName() + "_" + timestamp + ".png";

                // Tạo thư mục screenshots
                new File(REPORT_PATH + "screenshots").mkdirs();

                // Chụp ảnh
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));

                // Thêm ảnh vào report
                test.addScreenCaptureFromPath(screenshotPath);
                System.out.println("Đã chụp ảnh lỗi: " + screenshotPath);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi chụp ảnh: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test bị bỏ qua");
        System.out.println("⚠ Test bị bỏ qua: " + result.getName());
    }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush();

        System.out.println("\n=== Kết quả test suite ===");
        System.out.println("Tổng số test: " + context.getAllTestMethods().length);
        System.out.println("Passed: " + context.getPassedTests().size());
        System.out.println("Failed: " + context.getFailedTests().size());
        System.out.println("Skipped: " + context.getSkippedTests().size());
        System.out.println("Thời gian kết thúc: " + new Date());

        if (playwright != null) {
            playwright.close();
        }
    }

    public void setPlaywright(Playwright playwright, Browser browser) {
        this.playwright = playwright;
        this.browser = browser;
    }
}