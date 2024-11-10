//package listeners;
//
//import base.User;
//import base.ValidateHelpers;
//import com.aventstack.extentreports.Status;
//import listeners.ExtentManager;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.service.local.AppiumDriverLocalService;
//import io.appium.java_client.service.local.AppiumServiceBuilder;
//import org.junit.Before;
//import org.junit.After;
//import java.net.URL;
//import java.net.MalformedURLException;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//public class VneCSGTBaseTest {
//    protected AndroidDriver driver;
//    protected AppiumDriverLocalService service;
//    protected User.LoginHelper loginHelper;
//    protected ValidateHelpers validateHelpers;
//
//    @Before
//    public void setUp() throws MalformedURLException {
//        ExtentManager.setExtentVneCSGT();
//
//        // Khởi tạo dịch vụ Appium
//        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(4723));
//        service.start();
//
//        // Thiết lập DesiredCapabilities
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("deviceName", "RF8X50FF96M");
//        capabilities.setCapability("udid", "RF8X50FF96M");
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("platformVersion", "14");
//        capabilities.setCapability("automationName", "UiAutomator2");
//        capabilities.setCapability("noReset", true);
//        capabilities.setCapability("fullReset", false);
//        capabilities.setCapability("autoGrantPermissions", true);
//        capabilities.setCapability("autoDismissAlerts", true);
//
//        // Khởi tạo driver
//        URL url = new URL("http://127.0.0.1:4723");
//        driver = new AndroidDriver(url, capabilities);
//
//        // Khởi tạo các helper
//        loginHelper = new User.LoginHelper(driver);
//        validateHelpers = new ValidateHelpers(driver);
//    }
//
//    @After
//    public void tearDown() {
//        try {
//            if (driver != null) {
//                driver.quit();
//            }
//            if (service != null) {
//                service.stop();
//            }
//            ExtentManager.endReport();
//        } catch (Exception e) {
//            System.err.println("Error in tearDown: " + e.getMessage());
//        }
//    }
//
//    // Helper methods for test logging
//    protected void logTestStart(String testName) {
//        ExtentManager.test = ExtentManager.extent.createTest(testName);
//    }
//
//    protected void logTestPass(String message) {
//        ExtentManager.test.log(Status.PASS, message);
//    }
//
//    protected void logTestFail(String message, Exception e) {
//        ExtentManager.test.log(Status.FAIL, message + ": " + e.getMessage());
//    }
//
//    protected void logInfo(String message) {
//        ExtentManager.test.log(Status.INFO, message);
//    }
//}