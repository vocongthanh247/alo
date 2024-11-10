package VPA_Android.Login;

import base.VPATestBase;
import listeners.ExtentReportListener;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class LoginDuocDuyet extends VPATestBase {

    @Test
    public void loginSDTDuocDuyetCaNhan() throws Exception {
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnRegister\"]"));
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtUserName\"]"),"0988713885");
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/password\"]"),"Tt@1234567");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
//        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnCancel\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"Tài khoản đã xác minh\"]"),"Tài khoản đã xác minh");
        System.out.println("Pass tài khoản SDT Cá Nhân được xác minh");


    }
    @Test
    public void loginSDTDuocDuyetToChuc() throws Exception {
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnRegister\"]"));
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtUserName\"]"),"0354078921");
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/password\"]"),"Tt@123456");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
//        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnCancel\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"Tài khoản đã xác minh\"]"),"Tài khoản đã xác minh");
        System.out.println("Pass tài khoản SDT Tổ chức được xác minh");


    }
    @Test
    public void loginCCCDDuocDuyetCaNhan() throws Exception {
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnRegister\"]"));
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtUserName\"]"),"030198007822");
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/password\"]"),"Tt@1234567");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
//        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnCancel\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"Tài khoản đã xác minh\"]"),"Tài khoản đã xác minh");
        System.out.println("Pass tài khoản cá nhân CCCD được xác minh");


    }
    @Test
    public void loginMSTDuocDuyetToChuc() throws Exception {
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnRegister\"]"));
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtUserName\"]"),"0213498794");
        validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/password\"]"),"Tt@123456");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
//        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/btnCancel\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Cá nhân\"]"));
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"Tài khoản đã xác minh\"]"),"Tài khoản đã xác minh");
        System.out.println("Pass tài khoản MST được xác minh");

    }


}
