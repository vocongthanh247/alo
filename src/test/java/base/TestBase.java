// src/test/java/base/TestBase.java
package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.net.URL;

public class TestBase {
    protected AndroidDriver driver;
    protected AppiumDriverLocalService service;
    protected User.LoginHelper loginHelper;
    protected ValidateHelpers validateHelpers;

    protected static final String APP_PACKAGE = "com.ots.c08.vnecsgt.cbcs";

    @BeforeTest
    public void setUp() throws IOException, InterruptedException {
        initializeAppium();
        initializeHelpers();
        launchApp();
        handleLogin();
    }

    private void initializeAppium() throws IOException {
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(4723));
        service.start();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "RF8X50FF96M");
        capabilities.setCapability("udid", "RF8X50FF96M");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "14");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("autoDismissAlerts", true);

        URL url = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(url, capabilities);
    }

    private void initializeHelpers() {
        loginHelper = new User.LoginHelper(driver);
        validateHelpers = new ValidateHelpers(driver);
    }

    private void launchApp() {
        driver.activateApp(APP_PACKAGE);
    }

    private void handleLogin() throws InterruptedException, IOException {
        if (!loginHelper.isLoggedIn()) {
            loginHelper.performLogin();
            Thread.sleep(2000);
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            closeApp();
            quitDriver();
            stopAppiumService();
            System.out.println("Cleanup completed successfully.");
        } catch (Exception e) {
            String screenshotPath = validateHelpers.captureScreenshot("teardown_error");
            System.err.println("Error during cleanup: " + e.getMessage());
            System.err.println("Screenshot saved at: " + screenshotPath);
            e.printStackTrace();
        }
    }

    private void closeApp() throws InterruptedException {
        if (driver != null) {
            driver.terminateApp(APP_PACKAGE);
            Thread.sleep(1000);
        }
    }

    private void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void stopAppiumService() {
        if (service != null && service.isRunning()) {
            service.stop();
            service = null;
        }
    }
}