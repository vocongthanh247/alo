package TTCHC8165.Login;

import base.ValidateHelpersPlayWright;
import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class ValidateLogin {
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private ValidateHelpersPlayWright validateHelpers;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        validateHelpers = new ValidateHelpersPlayWright(page);
        page.navigate("http://10.0.0.218/");
        page.setViewportSize(1280, 800); // Tùy chọn kích thước cửa sổ
    }

    @Test
    public void testInputValidation() {
        // Click đăng nhập để kiểm tra xác thực
        validateHelpers.clickElement("div#ext-element-46");
        validateHelpers.waitForPageLoaded();

        // Nhập vào ô tài khoản với ít hơn 3 ký tự
        validateHelpers.setText("input#ext-element-5", "ab"); // Nhập 2 ký tự

        // Kiểm tra màu sắc của ô tài khoản
        String actualColor = validateHelpers.getCssValue("input#ext-element-5", "border-color");
        String expectedColor = "rgb(255, 255, 255)"; // Màu đỏ
        Assert.assertEquals(actualColor, expectedColor, "Màu viền không khớp với màu đỏ khi nhập dưới 3 ký tự.");
        String expectedColor1 = "rgb(56, 146, 212)"; // Màu trắng
        validateHelpers.setText("input#ext-element-5", "ab12"); // Nhập 4 ký tự
        String actualColor1 = validateHelpers.getCssValue("//div[@id='ext-element-10']", "border-color");
        Assert.assertEquals(actualColor1, expectedColor1, "Màu viền không khớp với màu đỏ khi nhập trên 4 ký tự.");



        // Nhập vào ô mật khẩu với ít hơn 3 ký tự
        validateHelpers.setText("input#ext-element-21", "12"); // Nhập 2 ký tự

        // Kiểm tra màu sắc của ô mật khẩu
        String actualColorPW = validateHelpers.getCssValue("input#ext-element-21", "border-color");
        Assert.assertEquals(actualColorPW, expectedColor, "Màu viền không khớp với màu đỏ khi nhập dưới 3 ký tự.");
        String expectedColor2 = "rgb(56, 146, 212)"; // Màu trắng
        validateHelpers.setText("input#ext-element-21", "ab125"); // Nhập 5 ký tự
        String actualColor2 = validateHelpers.getCssValue("//div[@id='ext-element-26']", "border-color");
        Assert.assertEquals(actualColor2, expectedColor2, "Màu viền không khớp với màu đỏ khi nhập trên 4 ký tự.");
    }

    @Test
    public void testValidInputAndLogin() {
        // Nhập vào ô tài khoản với 4 ký tự hợp lệ
        validateHelpers.setText("input#ext-element-5", "validUser");

        // Nhập vào ô mật khẩu với 4 ký tự hợp lệ
        validateHelpers.setText("input#ext-element-21", "wrongPass");

        // Nhấn nút đăng nhập
        validateHelpers.clickElement("div#ext-element-46");

        // Kiểm tra thông báo hiển thị khi tài khoản hoặc mật khẩu sai
        String actualErrorMessage = validateHelpers.getElementTextWhenVisible("//div[@id='ext-component-1']"); // Thay đổi selector cho thông báo lỗi nếu cần

        // Kiểm tra thông báo
        String expectedErrorMessage = "Tài khoản hoặc mật khẩu không đúng !"; // Thay đổi thông điệp cho phù hợp
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Thông báo lỗi không khớp với mong đợi.");
    }

    @AfterMethod
    public void tearDown() {
        browser.close(); // Đóng trình duyệt
        playwright.close(); // Đóng Playwright
    }
}