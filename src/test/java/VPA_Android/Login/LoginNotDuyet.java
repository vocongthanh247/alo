package VPA_Android.Login;

import base.VPATestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginNotDuyet extends VPATestBase {
    @Test
    public void loginSDTNotDuyetCaNhan() {
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnRegister\"]"));
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtUserName\"]"),"0354008521");
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/password\"]"),"Tt@123456");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnCancel\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"Chờ duyệt tài khoản\"]"),"Chờ duyệt tài khoản");
        System.out.println("Pass tài khoản SDT Cá Nhân chưa được xác minh");
    }
    @Test
    public void loginSDTNotDuyetToChuc() {
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnRegister\"]"));
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtUserName\"]"),"0939727887");
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/password\"]"),"Tt@123456");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
//        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnCancel\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"Chờ duyệt tài khoản\"]"),"Chờ duyệt tài khoản");
        System.out.println("Pass tài khoản SDT Tổ chức chưa được xác minh");
    }
    @Test
    public void loginCCCDNotDuyetCaNhan(){
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnRegister\"]"));
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtUserName\"]"),"046200004524");
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/password\"]"),"Tt@123456");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
//        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnCancel\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"Chờ duyệt tài khoản\"]"),"Chờ duyệt tài khoản");
        System.out.println("Pass tài khoản CCCD Cá nhân chưa được xác minh");

    }
    @Test
    public void loginMSTNotDuyetToChuc(){
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnRegister\"]"));
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtUserName\"]"),"046200004524");
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/password\"]"),"Tt@123456");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
//        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnCancel\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"Chờ duyệt tài khoản\"]"),"Chờ duyệt tài khoản");
        System.out.println("Pass tài khoản MST chưa được xác minh");

    }
}
