package TTCHC8165.Dashboard;

import TTCHC8165.Login.LoginHelper;
import base.ValidateHelpersPlayWright;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Camera {
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private LoginHelper loginHelper;
    private ValidateHelpersPlayWright validateHelpers;
    private int disconnectedCamera;
    private int connectedCamera;

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
    public void getCameraDashboard() {
        try {
            // Chờ và lấy số camera đã kết nối
            String connectedCameraText = page.waitForSelector("div:has-text('Đã kết nối :') + div").innerText();
            connectedCamera = retryFetchCount(connectedCameraText, true);

            // Chờ và lấy số camera chưa kết nối
            String disconnectedCameraText = page.waitForSelector("div:has-text('Chưa kết nối :') + div").innerText();
            disconnectedCamera = retryFetchCount(disconnectedCameraText, false);

            // In ra kết quả
            printCameraStatistics();
        } catch (Exception e) {
            System.err.println("Error retrieving camera counts: " + e.getMessage());
            disconnectedCamera = 0;
            connectedCamera = 0; // Đặt lại về 0 trong trường hợp lỗi
        }
    }

    private void printCameraStatistics() {
        System.out.println("Số camera đã kết nối: " + connectedCamera);
        System.out.println("Số camera chưa kết nối: " + disconnectedCamera);
        System.out.println("Tổng số camera: " + getTotalCameraDashboardCount());
    }

    private int retryFetchCount(String elementText, boolean isConnected) {
        int count = 0;
        int attempts = 0;

        while (attempts < 3) { // Thử lại tối đa 3 lần
            try {
                // Cố gắng lấy giá trị
                count = Integer.parseInt(elementText.replaceAll("[^0-9]", "").trim());
                if (count > 0) break; // Nếu lấy được giá trị, thoát khỏi vòng lặp
            } catch (NumberFormatException e) {
                System.err.println("Error parsing count: " + e.getMessage());
            }

            try {
                // Chờ phần tử hiển thị lại
                elementText = page.waitForSelector(isConnected ? "div:has-text('Đã kết nối :') + div" : "div:has-text('Chưa kết nối :') + div").innerText();
            } catch (Exception e) {
                System.err.println("Error waiting for element: " + e.getMessage());
            }
            attempts++;
        }
        return count;
    }

    public int getTotalCameraDashboardCount() {
        int total = disconnectedCamera + connectedCamera; // Tính tổng số camera
        System.out.println("Tổng số camera: " + total); // In ra kết quả
        return total; // Trả về tổng số camera
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