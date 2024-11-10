package base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static class LoginHelper {
        private AndroidDriver driver;
        private ValidateHelpers validateHelpers;

        public LoginHelper(AndroidDriver driver) {
            this.driver = driver;
            this.validateHelpers = new ValidateHelpers(driver);
        }

        public void performLogin() throws IOException {
            System.out.println("Performing login.");

            // Xóa nội dung trường tài khoản nếu có

            validateHelpers.clearInputField(By.id("com.ots.c08.vnecsgt.cbcs:id/edtCard"));
            validateHelpers.waitForElementToBeVisible(By.id("com.ots.c08.vnecsgt.cbcs:id/edtCard"));
            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/edtCard"));
            // Nhập dữ liệu cho trường tài khoản
            Runtime.getRuntime().exec("adb shell input text '000012'");
            validateHelpers.waitForElementToBeVisible(By.id("com.ots.c08.vnecsgt.cbcs:id/edtPassword"));
            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/edtPassword"));
            // Nhập dữ liệu cho trường mật khẩu
            Runtime.getRuntime().exec("adb shell input text '1234567'");

            validateHelpers.closeKeyboard();
            validateHelpers.waitForElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnLogin"));
            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnLogin"));
        }

        public boolean isLoggedIn() {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("com.ots.c08.vnecsgt.cbcs:id/navigation_home")));
                return element.isDisplayed();
            } catch (TimeoutException | NoSuchElementException e) {
                return false; // Người dùng chưa đăng nhập hoặc element không xuất hiện sau 10s
            }
        }
    }
}
