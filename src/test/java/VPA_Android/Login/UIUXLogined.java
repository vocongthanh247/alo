package VPA_Android.Login;

import base.VPATestBase;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
public class UIUXLogined extends VPATestBase {
    @Test
    public void loginUIUX() {
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnRegister\"]"));
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtUserName\"]"),"0354078921");
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/password\"]"),"Tt@123456");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.scrollDownOnce();
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/btnLogout\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnConfirm\"]"));
        validateHelpers.isElementDisplayed(By.xpath("//android.widget.ImageButton[@resource-id=\"com.vpa.daugia:id/btnBack\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"ĐĂNG NHẬP\"]"),"ĐĂNG NHẬP");
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"0354078921\"]"),"0354078921");
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"Công ty TNHH MTV\"]"),"Công ty TNHH MTV");
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/tvEdittextTitle\"]"),"Nhập mật khẩu *");
        validateHelpers.assertElementText(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"),"Đăng nhập");
        validateHelpers.isElementDisplayed(By.xpath("//android.widget.ImageButton[@resource-id=\"com.vpa.daugia:id/btnFaceId\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"Đăng nhập bằng tài khoản VNeID\"]"),"Đăng nhập bằng tài khoản VNeID");
        validateHelpers.isElementDisplayed(By.xpath("//android.widget.ImageButton[@resource-id=\"com.vpa.daugia:id/btnVneId\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/tvForgotPassword\"]"),"Quên mật khẩu ?");
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"Bạn chưa có tài khoản?\"]"),"Bạn chưa có tài khoản?");
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/tvRegister\"]"),"Đăng Ký");
        validateHelpers.isElementDisplayed(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/txt_version\"]"));

    }
}
