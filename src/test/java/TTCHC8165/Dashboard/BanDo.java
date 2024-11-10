package TTCHC8165.Dashboard;

import TTCHC8165.Login.LoginHelper;
import base.ValidateHelpersPlayWright;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BanDo {
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private LoginHelper loginHelper;
    private ValidateHelpersPlayWright validateHelpers;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        validateHelpers = new ValidateHelpersPlayWright(page);
        loginHelper = new LoginHelper(page, validateHelpers);

        page.navigate("http://10.0.0.218/");
        validateHelpers.waitForPageLoaded();
        loginHelper.login("007007", "123456");
        validateHelpers.waitForPageLoaded();
    }

    @Test(priority = 0)
    public void onScreen() {
        try {
            // Chờ và mở full screen
            page.waitForSelector("div:has-text('Đã kết nối :') + div");
            validateHelpers.clickElement("button:has-text('Mở Full Screen')"); // Cập nhật selector cho button chính xác
        } catch (Exception e) {
            System.err.println("Không nhấn vào button được: " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void offScreen() {
        try {
            page.waitForSelector("div:has-text('Đã kết nối :') + div");
            validateHelpers.clickElement("button:has-text('Tắt Full Screen')"); // Cập nhật selector cho button chính xác
        } catch (Exception e) {
            System.err.println("Không nhấn vào button được: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void onMenu() {
        try {
            page.waitForSelector("div:has-text('Đã kết nối :') + div");
            validateHelpers.clickElement("button:has-text('Mở Menu')"); // Cập nhật selector cho button chính xác
        } catch (Exception e) {
            System.err.println("Không nhấn vào button được: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void offMenu() {
        try {
            page.waitForSelector("div:has-text('Đã kết nối :') + div");
            validateHelpers.clickElement("button:has-text('Tắt Menu')"); // Cập nhật selector cho button chính xác
        } catch (Exception e) {
            System.err.println("Không nhấn vào button được: " + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void inDashboard() {
        try {
            page.waitForSelector("div:has-text('Đã kết nối :') + div");
            validateHelpers.clickElement("button:has-text('Vào Biểu Đồ')"); // Cập nhật selector cho button chính xác
        } catch (Exception e) {
            System.err.println("Không nhấn vào button được: " + e.getMessage());
        }
    }

    @Test(priority = 5)
    public void outDashboard() {
        try {
            page.waitForSelector("div:has-text('Đã kết nối :') + div");
            validateHelpers.clickElement("button:has-text('Ra Biểu Đồ')"); // Cập nhật selector cho button chính xác
        } catch (Exception e) {
            System.err.println("Không nhấn vào button được: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (browser != null) {
            browser.close(); // Đóng trình duyệt
        }
        if (playwright != null) {
            playwright.close(); // Đóng Playwright
        }
    }
}