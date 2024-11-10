package VPA_Android.Login;

import base.VPATestBase;
import org.openqa.selenium.By;
import org.testng.annotations.*;

public class ValidateLogin extends VPATestBase {

    @BeforeMethod
    public void setup() throws InterruptedException {
        try {
            // Đợi app load xong
            Thread.sleep(2000);
            // Click vào tab Cá nhân
            validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
            // Click vào nút Đăng nhập
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnRegister\"]"));
            System.out.println("Setup for login test completed successfully");
        } catch (Exception e) {
            System.err.println("Setup for login test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void sdtKhongTonTai(){
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtUserName\"]"), "213213213123");
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/password\"]"), "Tt@123456");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/tvMessage\"]"), "Tên truy cập hoặc mật khẩu không đúng, vui lòng thử lại.");
        System.out.println("sdtKhongTonTai test completed successfully");
    }
    @Test
    public void boTrongSDT(){
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/tvMessage\"]"), "Vui lòng nhập số Số điện thoại/CCCD/Mã số thuế của tổ chức");
        System.out.println("boTrongSDT test completed successfully");
    }

}
