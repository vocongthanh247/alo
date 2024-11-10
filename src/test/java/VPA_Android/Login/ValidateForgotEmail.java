package VPA_Android.Login;

import base.VPATestBase;
import org.openqa.selenium.By;
import org.testng.annotations.*;

public class ValidateForgotEmail extends VPATestBase {
    @BeforeMethod
    public void setup() throws InterruptedException {
        try {
            // Đợi app load xong
            Thread.sleep(2000);
            // Click vào tab Cá nhân
            validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
            // Click vào nút Đăng nhập
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnRegister\"]"));
            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/tvForgotPassword\"]"));
            System.out.println("Setup for login test completed successfully");
        } catch (Exception e) {
            System.err.println("Setup for login test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void validateForgotEmail() throws InterruptedException {
        try {
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtEmail\"]"), "123");
            validateHelpers.clearInputField(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtEmail\"]"));
            validateHelpers.assertElementText(
                    By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"),
                    "Vui lòng nhập email"
            );
            System.out.println("Hiển thị Thông báo 'Vui lòng nhập email' khi bỏ trống => Pass");
        } catch (Exception e) {
            System.err.println("Test validateForgotEmail failed: " + e.getMessage());
            captureScreenshot("validateForgotEmail_failed");
            throw e;
        }
    }

    @Test
    public void validate200KiTuEmail() throws InterruptedException {
        try {
            // Tạo chuỗi 200 ký tự
            StringBuilder email = new StringBuilder();
            for (int i = 0; i < 200; i++) {
                email.append("a");
            }
            email.append("@gmail.com"); // Thêm đuôi email
            // Nhập chuỗi vào trường email
            validateHelpers.clickAndInput(
                    By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtEmail\"]"),
                    email.toString()
            );
            // Thử nhập thêm ký tự
            validateHelpers.clickAndInput(
                    By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtEmail\"]"),
                    email.toString() + "extra"
            );
            // Lấy giá trị hiện tại của trường email
            String actualValue = driver.findElement(
                    By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtEmail\"]")
            ).getText();
            // Kiểm tra độ dài
            if (actualValue.length() > 200) {
                System.err.println("FAILED: Email field accepts more than 200 characters. Current length: " + actualValue.length());
                captureScreenshot("validate200KiTuEmail_failed");
                throw new AssertionError("Email field should not accept more than 200 characters");
            } else {
                System.out.println("PASSED: Email field correctly limits to 200 characters");
            }
        } catch (Exception e) {
            System.err.println("Test validate200KiTuEmail failed: " + e.getMessage());
            captureScreenshot("validate200KiTuEmail_failed");
            throw e;
        }
    }

    @Test
    public void nhapKhoangTrangEmail() throws InterruptedException {
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtEmail\"]"), "  ");
        validateHelpers.verifyElementDisabled(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
    }

    @Test
    public void dinhDangEmail() throws InterruptedException {
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtEmail\"]"), "123");
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"), "Email không đúng định dạng");
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtEmail\"]"), "aaaaaaabbbb@gmail.com");
        validateHelpers.verifyElementNotPresent(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"));
    }
    @Test
    public void choPhepNhapKiTuEmail() throws InterruptedException {
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtEmail\"]"), "sadfgsdfsdh23123@!!@df3_*@@gmail.com");
        validateHelpers.verifyElementNotPresent(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"));
    }

}
