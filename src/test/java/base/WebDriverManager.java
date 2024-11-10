package base;  // hoặc package phù hợp với project của bạn

import io.appium.java_client.android.AndroidDriver;

public class WebDriverManager {  // Đổi tên class để tránh xung đột
    private static AndroidDriver driver;

    public static void setDriver(AndroidDriver androidDriver) {
        driver = androidDriver;
    }

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}