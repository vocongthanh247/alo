package base;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VPATestBase {
    protected AndroidDriver driver;
    protected AppiumDriverLocalService service;
    protected ValidateHelpers validateHelpers;

    // Constants
    protected static final String SCREENSHOT_PATH = "test-output/screenshots/VPA/";
    protected static final String APP_PACKAGE = "com.vpa.daugia";

    private void createScreenshotDirectory() {
        try {
            File directory = new File(SCREENSHOT_PATH);
            if (!directory.exists()) {
                boolean created = directory.mkdirs();
                if (created) {
                    System.out.println("Created screenshot directory at: " + directory.getAbsolutePath());
                } else {
                    System.err.println("Failed to create screenshot directory");
                }
            }
        } catch (Exception e) {
            System.err.println("Error creating screenshot directory: " + e.getMessage());
        }
    }

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        try {
            createScreenshotDirectory();
            initializeAppium();
            clearAppAndPermissions();
            initializeHelpers();
            launchApp();
            grantAllPermissions();
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnGoTo\"]"));
            System.out.println("Setup completed successfully");
        } catch (Exception e) {
            System.err.println("Setup failed: " + e.getMessage());
            throw e;
        }
    }

    private void clearAppAndPermissions() {
        try {
            // Force stop app
            driver.executeScript("mobile: shell", ImmutableMap.of(
                    "command", "am force-stop " + APP_PACKAGE
            ));
            System.out.println("Force stopped app");

            // Clear app data
            driver.executeScript("mobile: shell", ImmutableMap.of(
                    "command", "pm clear " + APP_PACKAGE
            ));
            System.out.println("Cleared app data");

            // Reset app permissions
            driver.executeScript("mobile: shell", ImmutableMap.of(
                    "command", "pm reset-permissions " + APP_PACKAGE
            ));
            System.out.println("Reset app permissions");

            Thread.sleep(2000); // Wait for clearing to complete
        } catch (Exception e) {
            System.err.println("Error during app clearing: " + e.getMessage());
        }
    }

    private void grantAllPermissions() {
        try {
            String[] permissions = {
                    "android.permission.WRITE_EXTERNAL_STORAGE",
                    "android.permission.READ_EXTERNAL_STORAGE",
                    "android.permission.CAMERA",
                    "android.permission.ACCESS_FINE_LOCATION",
                    "android.permission.ACCESS_COARSE_LOCATION",
                    "android.permission.READ_PHONE_STATE",
                    "android.permission.POST_NOTIFICATIONS"  // Thêm quyền thông báo
            };

            for (String permission : permissions) {
                try {
                    driver.executeScript("mobile: shell", ImmutableMap.of(
                            "command", "pm grant " + APP_PACKAGE + " " + permission
                    ));
                    System.out.println("Granted permission: " + permission);
                } catch (Exception e) {
                    System.err.println("Failed to grant permission " + permission + ": " + e.getMessage());
                }
            }

            Thread.sleep(1000); // Wait for permissions to be applied
            System.out.println("All permissions granted successfully");
        } catch (Exception e) {
            System.err.println("Error during permission granting: " + e.getMessage());
        }
    }

    private void initializeAppium() throws IOException {
        try {
            // Khởi tạo Appium Service với cấu hình bảo mật
            AppiumServiceBuilder builder = new AppiumServiceBuilder()
                    .usingPort(4723)
                    .withArgument(() -> "--allow-insecure", "adb_shell"); // Thêm dòng này

            service = AppiumDriverLocalService.buildService(builder);
            service.start();
            System.out.println("Appium service started successfully");

            // Phần còn lại giữ nguyên
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "RF8X50FF96M");
            capabilities.setCapability("udid", "RF8X50FF96M");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "14");
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("appPackage", APP_PACKAGE);
            capabilities.setCapability("appActivity", "com.vpa.daugia.ui.splash.SplashActivity");
            capabilities.setCapability("noReset", true);
            capabilities.setCapability("fullReset", false);
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("autoDismissAlerts", true);

            URL url = new URL("http://127.0.0.1:4723");
            driver = new AndroidDriver(url, capabilities);
            System.out.println("Android driver initialized successfully");

            Thread.sleep(2000);

        } catch (Exception e) {
            System.err.println("Failed to initialize Appium: " + e.getMessage());
            throw new RuntimeException("Appium initialization failed", e);
        }
    }

    private void initializeHelpers() {
        if (driver != null) {
            validateHelpers = new ValidateHelpers(driver);
            System.out.println("Helpers initialized successfully");
        } else {
            throw new IllegalStateException("Driver is null, cannot initialize helpers");
        }
    }

    protected void launchApp() {
        if (driver != null) {
            driver.activateApp(APP_PACKAGE);
            System.out.println("App launched successfully: " + APP_PACKAGE);
        } else {
            throw new IllegalStateException("Driver is null, cannot launch app");
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            if (driver != null) {
                // Capture final screenshot
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String screenshotPath = captureScreenshot("teardown_" + timestamp);
                System.out.println("Final screenshot saved at: " + screenshotPath);

                try {
                    // Force stop app
                    driver.executeScript("mobile: shell", ImmutableMap.of(
                            "command", "am force-stop " + APP_PACKAGE
                    ));
                    System.out.println("Force stopped app");

                    // Clear app data
                    driver.executeScript("mobile: shell", ImmutableMap.of(
                            "command", "pm clear " + APP_PACKAGE
                    ));
                    System.out.println("Cleared app data");

                    // Reset permissions
                    driver.executeScript("mobile: shell", ImmutableMap.of(
                            "command", "pm reset-permissions " + APP_PACKAGE
                    ));
                    System.out.println("Reset app permissions");

                    Thread.sleep(1000); // Wait for clearing to complete
                } catch (Exception e) {
                    System.err.println("Error during app clearing: " + e.getMessage());
                }

                // Close driver
                driver.quit();
                driver = null;
                System.out.println("Driver quit successfully");
            }

            if (service != null && service.isRunning()) {
                service.stop();
                service = null;
                System.out.println("Appium service stopped successfully");
            }

            System.out.println("VPA cleanup completed successfully");
        } catch (Exception e) {
            System.err.println("Error during cleanup: " + e.getMessage());
            e.printStackTrace();
        }
    }

    protected String captureScreenshot(String name) {
        if (driver == null) {
            System.err.println("Cannot capture screenshot: driver is null");
            return null;
        }

        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = SCREENSHOT_PATH + name + "_" + timestamp + ".png";
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(fileName);
            FileUtils.copyFile(screenshot, destFile);

            System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());
            return destFile.getAbsolutePath();
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}