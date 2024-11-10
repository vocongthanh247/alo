package VPA_Android.Login;

import base.VPATestBase;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class UIUXFirstLogin extends VPATestBase {
    @Test
    public void testFirstLogin() throws InterruptedException {
        //Vào màn hình đăng nhập để check
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnRegister\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"ĐĂNG NHẬP\"]"),"ĐĂNG NHẬP");
        validateHelpers.isElementDisplayed(By.xpath("//android.widget.ImageView[@resource-id=\"com.vpa.daugia:id/imvLogo\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/url\"]"),"Nhập mật khẩu");
        validateHelpers.assertElementText(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtUserName\"]"),"Số điện thoại/CCCD/mã số thuế *");
        validateHelpers.assertElementText(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/password\"]"),"Nhập mật khẩu *");
        validateHelpers.assertElementText(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"),"Đăng nhập");
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"Đăng nhập bằng tài khoản VNeID\"]"),"Đăng nhập bằng tài khoản VNeID");
        validateHelpers.isElementDisplayed(By.xpath("//android.widget.ImageButton[@resource-id=\"com.vpa.daugia:id/btnVneId\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/tvForgotPassword\"]"),"Quên mật khẩu ?");
        validateHelpers.scrollDownSmall();
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"Bạn chưa có tài khoản?\"]"),"Bạn chưa có tài khoản?");
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/tvRegister\"]"),"Đăng ký");
        validateHelpers.isElementDisplayed(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/txt_version\"]"));

    }


}
