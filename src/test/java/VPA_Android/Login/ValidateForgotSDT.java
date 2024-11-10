package VPA_Android.Login;

import base.TestBase;
import base.VPATestBase;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ValidateForgotSDT extends VPATestBase {
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
            validateHelpers.clickElement(By.xpath("//android.widget.RadioButton[@resource-id=\"com.vpa.daugia:id/radioButton2\"]"));
            System.out.println("Setup for login test completed successfully");
        } catch (Exception e) {
            System.err.println("Setup for login test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void sdtHopLe() throws InterruptedException {
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"), "012345678");
        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"), "Số điện thoại không đúng, vui lòng nhập lại");
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"), "0123456789");
        validateHelpers.verifyElementNotPresent(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"));
    }
    @Test
    public void itHoacNhieuhon10So() throws InterruptedException {
        try {
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"), "012345678");
            validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"), "Số điện thoại không đúng, vui lòng nhập lại");
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"), "01234567899");
            validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"), "Số điện thoại không đúng, vui lòng nhập lại");
            System.out.println("Pass case itHoacNhieuhon10So");
        }catch (Exception e) {
            System.err.println("Fail Case itHoacNhieuhon10So: " + e.getMessage());
        }
    }
    @Test
    public void nhapKhongPhaiLaSo() {
        try {
            // Test chặn chữ thường
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"), "abc");
            String valueAfterLowerCase = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]")).getText();
            if (valueAfterLowerCase.isEmpty()) {
                System.out.println("PASSED: nhapKhongPhaiLaSo");
            }
            validateHelpers.clearInputField(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"));

            // Test chặn chữ in hoa
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"), "ABC");
            String valueAfterUpperCase = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]")).getText();
            if (valueAfterUpperCase.isEmpty()) {
                System.out.println("PASSED: nhapKhongPhaiLaSo");
            }
            validateHelpers.clearInputField(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"));

            // Test chặn ký tự đặc biệt
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"), "@#$%");
            String valueAfterSpecial = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]")).getText();
            if (valueAfterSpecial.isEmpty()) {
                System.out.println("PASSED: nhapKhongPhaiLaSo");
            }
            validateHelpers.clearInputField(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"));

            // Test chặn khoảng trắng
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"), "123 456");
            String valueAfterSpace = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]")).getText();
            if (!valueAfterSpace.contains(" ")) {
                System.out.println("PASSED: nhapKhongPhaiLaSo");
            }
            validateHelpers.clearInputField(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"));

        } catch (Exception e) {
            System.err.println("Test nhapKhongPhaiLaSo failed: " + e.getMessage());

            throw e;
        }
    }
    @Test
    public void buttonBack(){
       try {
           validateHelpers.clickElement(By.xpath("//android.widget.ImageButton[@resource-id=\"com.vpa.daugia:id/btnBack\"]"));
           validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@text=\"ĐĂNG NHẬP\"]"), "ĐĂNG NHẬP");
           System.out.println("Pass case ButtonBack");
       }catch (Exception e) {
           System.err.println("Test buttonBack failed: " + e.getMessage());
       }
    }
    @Test
    public void khongNhapSo0DauTien(){
        try {
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"), "1112345678");
            validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"), "Số điện thoại không đúng, vui lòng nhập lại");
            System.out.println("Pass case khongNhapSo0DauTien");
        }catch (Exception e) {
            System.err.println("Fail Case itHoacNhieuhon10So: " + e.getMessage());
        }

    }
    @Test
    public void boTrongSDT(){
        try {
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"), "1112345678");
            validateHelpers.clearInputField(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"));
            validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"), "Vui lòng nhập số điện thoại");
            validateHelpers.verifyElementDisabled(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
            System.out.println("Pass case boTrongSDT");
        }catch (Exception e) {
            System.err.println("Fail Case boTrongSDT: " + e.getMessage());
        }

    }
    @Test
    public void haiPhutOTP(){
        try {
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"), "0354078921");
            validateHelpers.verifyElementDisabled(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
            validateHelpers.clickElement(By.xpath("//android.widget.ImageButton[@resource-id=\"com.vpa.daugia:id/btnBack\"]"));
            validateHelpers.verifyElementDisabled(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
            validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"), "Vui lòng gửi yêu cầu nhận mã xác thực sau 2 phút");

            System.out.println("Pass case boTrongSDT");
        }catch (Exception e) {
            System.err.println("Fail Case boTrongSDT: " + e.getMessage());
        }

    }
    @Test
    public void khongNhapKiTuOTP() {
        try {
            // Test chặn chữ thường
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText"), "abc");
            String valueAfterLowerCase = driver.findElement(By.xpath("//android.widget.EditText")).getText();
            if (valueAfterLowerCase.isEmpty()) {
                System.out.println("PASSED: khongNhapKiTuOTP");
            }
            validateHelpers.clearInputField(By.xpath("//android.widget.EditText"));

            // Test chặn chữ in hoa
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText"), "ABC");
            String valueAfterUpperCase = driver.findElement(By.xpath("//android.widget.EditText")).getText();
            if (valueAfterUpperCase.isEmpty()) {
                System.out.println("PASSED: khongNhapKiTuOTP");
            }
            validateHelpers.clearInputField(By.xpath("//android.widget.EditText"));

            // Test chặn ký tự đặc biệt
            validateHelpers.clickAndInput(By.xpath("///android.widget.EditText"), "@#$%");
            String valueAfterSpecial = driver.findElement(By.xpath("//android.widget.EditText")).getText();
            if (valueAfterSpecial.isEmpty()) {
                System.out.println("PASSED: khongNhapKiTuOTP");
            }
            validateHelpers.clearInputField(By.xpath("//android.widget.EditText"));

            // Test chặn khoảng trắng
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText"), "    ");
            String valueAfterSpace = driver.findElement(By.xpath("//android.widget.EditText")).getText();
            if (!valueAfterSpace.contains(" ")) {
                System.out.println("PASSED: khongNhapKiTuOTP");
            }
            validateHelpers.clearInputField(By.xpath("//android.widget.EditText"));

        } catch (Exception e) {
            System.err.println("Test khongNhapKiTuOTP failed: " + e.getMessage());

            throw e;
        }

    }
    @Test
    public void nhapSaiMaOTP(){
        try {
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"), "0988713885");
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
            validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText"),"000000");
            validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"), "Bạn đã nhập sai mã xác thực 1 lần, tài khoản sẽ bị khóa khi nhập sai 5 lần");
            System.out.println("nhapSaiMaOTP");
        }catch (Exception e) {
            System.err.println("nhapSaiMaOTP: " + e.getMessage());
        }

    }
    @Test
    public void khongNhapOTP(){
        try {
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPhone\"]"), "0988713885");
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
//            validateHelpers.clickAndInputVPA(By.xpath("//android.widget.EditText"),"000000");
            validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"), "Vui lòng nhập mã OTP");
            System.out.println("khongNhapOTP");
        }catch (Exception e) {
            System.err.println("khongNhapOTP: " + e.getMessage());
        }


    }
    //Những case dưới này bắt buộc phải có manual là nhập OTP
//    @Test
//    public void happyCaseMK(){
//        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPassword\"]"), "123123Asdasdassaffsagafgdsfgsdfgrwerwererrwqeqweqweqweqweqweqweczxczxczxczxczczxczxasdas2131231231322312312312312312@@");
//        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtConfirmPassword\"]"),"123123Asdasdassaffsagafgdsfgsdfgrwerwererrwqeqweqweqweqweqweqweczxczxczxczxczczxczxasdas2131231231322312312312312312@@");
//        validateHelpers.verifyElementNotPresent(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/tvErrorPassword\"]"));
//    }
//    @Test
//    public void duoi8Tren50KiTu(){
//        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPassword\"]"), "1");
//        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/tvErrorPassword\"]"),"Mật khẩu phải có độ dài từ 8 đến 50 ký tự và chứa ít nhất 1 ký tự chữ in hoa, chữ thường, ký tự số, ký tự đặc biệt");
//        try {
//            // Tạo chuỗi 200 ký tự
//            StringBuilder email = new StringBuilder();
//            for (int i = 0; i < 50; i++) {
//                email.append("a");
//            }
//            email.append("@FASDA"); // Thêm đuôi email
//            // Nhập chuỗi vào trường email
//            validateHelpers.clickAndInput(
//                    By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPassword\"]"),
//                    email.toString()
//            );
//            // Thử nhập thêm ký tự
//            validateHelpers.clickAndInput(
//                    By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPassword\"]"),
//                    email.toString() + "extra"
//            );
//            // Lấy giá trị hiện tại của trường email
//            String actualValue = driver.findElement(
//                    By.xpath("///android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPassword\"]")
//            ).getText();
//            // Kiểm tra độ dài
//            if (actualValue.length() > 200) {
//                System.err.println("FAILED: Email field accepts more than 50 characters. Current length: " + actualValue.length());
//                captureScreenshot("validate200KiTuEmail_failed");
//                throw new AssertionError("Email field should not accept more than 50 characters");
//            } else {
//                System.out.println("PASSED: Email field correctly limits to 50 characters");
//            }
//        } catch (Exception e) {
//            System.err.println("Test duoi8Tren50KiTu failed: " + e.getMessage());
//            captureScreenshot("duoi8Tren50KiTu_failed");
//            throw e;
//        }
//
//
//    }
//    @Test
//    public void khongNhapMK() {
//        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
//        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"), "Vui lòng nhập mật khẩu mới");
//
//
//    }
//    @Test
//    public void khongNhapXacNhanMK(){
//        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPassword\"]"), "123123Asdasdassaffsagafgdsfgsdfgrwerwererrwqeqweqweqweqweqweqweczxczxczxczxczczxczxasdas2131231231322312312312312312@@");
//        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
//        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"), "Vui lòng nhập xác nhận mật khẩu");
//
//
//
//    }
//    @Test
//    public void nhapXacNhanMKKhongChinhXac(){
//        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtPassword\"]"), "123123Asdasdassaffsagafgdsfgsdfgrwerwererrwqeqweqweqweqweqweqweczxczxczxczxczczxczxasdas2131231231322312312312312312@@");
//        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtConfirmPassword\"]"),"123");
//        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
//        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/tvErrorConfirmPassword\"]"), "Nhập lại mật khẩu không chính xác");
//    }
//    @Test
//    public void nhapXacNhanMKKhongNhapMKMoi(){
//        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.vpa.daugia:id/edtConfirmPassword\"]"),"123");
//        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.vpa.daugia:id/button\"]"));
//        validateHelpers.assertElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.vpa.daugia:id/baseEditTextError\"]"), "Vui lòng nhập mật khẩu mới");
//
//
//
//    }


}