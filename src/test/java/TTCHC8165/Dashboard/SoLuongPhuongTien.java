package TTCHC8165.Dashboard;

import TTCHC8165.Login.LoginHelper;
import base.ValidateHelpersPlayWright;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SoLuongPhuongTien {
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

    @Test(priority = 1)
    public VehicleCounts getVehicleCounts() {
        try {
            String runningText = page.waitForSelector("div:has-text('Đang chạy :') + div").innerText();
            String stoppedText = page.waitForSelector("div:has-text('Đang dừng :') + div").innerText();
            String disconnectedText = page.waitForSelector("div:has-text('Mất kết nối :') + div").innerText();

            int runningVehicles = Integer.parseInt(runningText.replaceAll("[^0-9]", "").trim());
            int stoppedVehicles = Integer.parseInt(stoppedText.replaceAll("[^0-9]", "").trim());
            int disconnectedVehicles = Integer.parseInt(disconnectedText.replaceAll("[^0-9]", "").trim());

            return new VehicleCounts(runningVehicles, stoppedVehicles, disconnectedVehicles);
        } catch (Exception e) {
            System.err.println("Error retrieving vehicle counts: " + e.getMessage());
            return new VehicleCounts(0, 0, 0); // Trả về giá trị mặc định nếu có lỗi
        }
    }

    public int getTotalVehicleCount() {
        VehicleCounts counts = getVehicleCounts();
        return counts.getTotalVehicles();
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

    public static class VehicleCounts {
        private int runningVehicles;
        private int stoppedVehicles;
        private int disconnectedVehicles;

        public VehicleCounts(int runningVehicles, int stoppedVehicles, int disconnectedVehicles) {
            this.runningVehicles = runningVehicles;
            this.stoppedVehicles = stoppedVehicles;
            this.disconnectedVehicles = disconnectedVehicles;
        }

        public int getRunningVehicles() {
            return runningVehicles;
        }

        public int getStoppedVehicles() {
            return stoppedVehicles;
        }

        public int getDisconnectedVehicles() {
            return disconnectedVehicles;
        }

        public int getTotalVehicles() {
            return runningVehicles + stoppedVehicles + disconnectedVehicles;
        }
    }
}