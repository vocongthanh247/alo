    package VneCSGT;

    import base.ValidateHelpers;
    import io.appium.java_client.android.AndroidDriver;
    import io.appium.java_client.service.local.AppiumDriverLocalService;
    import io.appium.java_client.service.local.AppiumServiceBuilder;
    import io.appium.java_client.remote.MobileCapabilityType;
    import org.junit.After;
    import org.junit.Before;
    import org.junit.Test;
    import org.openqa.selenium.By;
    import org.openqa.selenium.NoSuchElementException;
    import org.openqa.selenium.remote.DesiredCapabilities;
    import org.testng.annotations.AfterClass;

    import java.io.IOException;
    import java.net.MalformedURLException;
    import java.net.URL;

    public class LoginVneCSGT {
        public AndroidDriver driver;
        private AppiumDriverLocalService service;
        private ValidateHelpers validateHelpers;

        @Before
        public void setUp() throws MalformedURLException {
            // Khởi tạo dịch vụ Appium
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(4723));
            service.start();

            // Thiết lập DesiredCapabilities
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "RF8X50FF96M");
            capabilities.setCapability(MobileCapabilityType.UDID, "RF8X50FF96M");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            capabilities.setCapability("noReset", true);
            capabilities.setCapability("fullReset", false);
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("autoDismissAlerts", true);

            // Khởi tạo driver
            URL url = new URL("http://127.0.0.1:4723");
            driver = new AndroidDriver(url, capabilities);
            System.out.println("Driver initialized successfully.");
            validateHelpers = new ValidateHelpers(driver); // Khởi tạo validateHelpers

            // Mở ứng dụng
            openApp();
        }

        private void openApp() {
            try {
                driver.activateApp("com.ots.c08.vnecsgt.cbcs");
                Thread.sleep(5000); // Đợi một chút để ứng dụng mở
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void testAppLaunch() throws IOException {
            System.out.println("App is running.");

            // Kiểm tra xem đã đăng nhập hay chưa
            if (isLoggedIn()) {
                System.out.println("User is already logged in. Skipping login.");
            } else {
                performLogin();
            }
        }

        private boolean isLoggedIn() {
            // Kiểm tra xem người dùng đã đăng nhập hay chưa
            try {
                return driver.findElement(By.id("com.ots.c08.vnecsgt.cbcs:id/navigation_home")).isDisplayed();
            } catch (NoSuchElementException e) {
                return false; // Nếu không tìm thấy phần tử, người dùng chưa đăng nhập
            }
        }

        void performLogin() throws IOException {
            System.out.println("Performing login.");

            // Nhấp vào trường để đảm bảo nó được chọn
            validateHelpers.waitForElementToBeVisible(By.xpath("//android.widget.EditText[@content-desc=\"1111\"]"));
            validateHelpers.clearInputField(By.xpath("//android.widget.EditText[@content-desc=\"1111\"]"));
//            Thread.sleep(500);
//            validateHelpers.clickAndInput(By.id("com.ots.c08.vnecsgt.cbcs:id/edtCard"),"000012");

//             Nhập dữ liệu
            try {
                Runtime.getRuntime().exec("adb shell input text '000012'");
            } catch (Exception e) {
                System.err.println("ADB command failed: " + e.getMessage());
            }

            validateHelpers.waitForElementToBeVisible(By.xpath("//android.widget.EditText[@content-desc=\"2222\"]"));
            validateHelpers.clickElement(By.xpath("//android.widget.EditText[@content-desc=\"2222\"]"));
//            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"2222\"]"),"1234567");
            try {
                Runtime.getRuntime().exec("adb shell input text '1234567'");
            } catch (Exception e) {
                System.err.println("ADB command failed: " + e.getMessage());
            }

            validateHelpers.closeKeyboard();
            validateHelpers.waitForElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnLogin"));
            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnLogin"));

        }

        @AfterClass
        public void tearDown() {
            try {
                // Kiểm tra driver không null trước khi thực hiện các thao tác
                if (driver != null) {
                    // Terminate app
                    driver.terminateApp("com.ots.c08.vnecsgt.cbcs");
                    Thread.sleep(1000); // đợi 1 giây để đảm bảo app đã đóng hoàn toàn

                    // Quit driver
                    driver.quit();
                    driver = null; // set null để tránh memory leak
                }

                // Stop Appium service
                if (service != null && service.isRunning()) {
                    service.stop();
                    service = null; // set null để tránh memory leak
                }

                System.out.println("Cleanup completed successfully.");
            } catch (Exception e) {
                System.err.println("Error during cleanup: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
