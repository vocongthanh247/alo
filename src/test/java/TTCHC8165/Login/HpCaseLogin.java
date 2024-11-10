package TTCHC8165.Login;

import base.ValidateHelpersPlayWright;
import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;

public class HpCaseLogin {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected ValidateHelpersPlayWright validateHelpers;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        validateHelpers = new ValidateHelpersPlayWright(page);
        page.navigate("http://10.0.0.218/");

        // Không đặt kích thước viewport hoặc sử dụng kích thước lớn
        }

    public void login(String username, String password) {
        validateHelpers.setText("input#ext-element-5", username);
        validateHelpers.setText("input#ext-element-21", password);
        validateHelpers.clickElement("div#ext-element-46");
        validateHelpers.waitForPageLoaded();
    }

    public void verifyLoginSuccess() {
        validateHelpers.clickElement("div#ext-element-76");

        // Kiểm tra URL
        String currentUrl = page.url();
        String expectedUrl = "http://10.0.0.218/dashboard";
        Assert.assertEquals(currentUrl, expectedUrl, "URL không khớp, người dùng chưa được chuyển đến trang chính.");

        // Kiểm tra thông báo xác thực
        validateHelpers.assertElementText("//div[contains(text(),'TRUNG TÂM THÔNG TIN CHỈ HUY GIAO THÔNG - CỤC CẢNH ')]", // Cập nhật selector chính xác
                "TRUNG TÂM THÔNG TIN CHỈ HUY GIAO THÔNG - CỤC CẢNH SÁT GIAO THÔNG");
        System.out.println("Pass HpCase Login");
    }

    @Test(priority = 0)
    public void testLoginAndAccessDashboard() throws InterruptedException {
        login("007007", "123456"); // Đăng nhập
        Thread.sleep(3000); // Chờ để trang load
        verifyLoginSuccess(); // Xác minh đăng nhập thành công
    }

    @AfterMethod
    public void tearDown() {
        browser.close(); // Đóng trình duyệt
        playwright.close(); // Đóng Playwright
    }
}