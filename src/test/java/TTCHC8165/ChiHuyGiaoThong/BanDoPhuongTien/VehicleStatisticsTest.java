package TTCHC8165.ChiHuyGiaoThong.BanDoPhuongTien;

import TTCHC8165.Dashboard.SoLuongPhuongTien;
import TTCHC8165.Login.LoginHelper;
import base.ValidateHelpersPlayWright; // Cập nhật import cho ValidateHelpersPlayWright
import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths; // Thêm import cho ảnh chụp màn hình
import java.util.ArrayList;
import java.util.List;

public class VehicleStatisticsTest {

    private Playwright playwright; // Thay thế WebDriver bằng Playwright
    private Browser browser;
    private Page page;
    private ValidateHelpersPlayWright validateHelpers;
    private LoginHelper loginHelper;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // Khởi động trình duyệt
        page = browser.newPage();
        validateHelpers = new ValidateHelpersPlayWright(page); // Khởi tạo ValidateHelpersPlayWright
        loginHelper = new LoginHelper(page, validateHelpers); // Khởi tạo LoginHelper
        page.navigate("http://10.0.0.218/");
        validateHelpers.waitForPageLoaded(); // Đợi trang tải xong
        loginHelper.login("007007", "123456"); // Đăng nhập
        validateHelpers.waitForPageLoaded(); // Đợi trang tải xong

        // Click vào các phần tử trong giao diện
        page.click("xpath=//span[contains(text(),'Chỉ huy giao thông')]"); // Click vào menu
        page.click("xpath=//span[contains(text(),'Bản đồ phương tiện')]"); // Click vào bản đồ phương tiện

        // Thiết lập trang hiển thị
        validateHelpers.clickElement("xpath=//span[@title='50 / trang']"); // Chọn trang
        validateHelpers.clickElement("xpath=//div[contains(text(),'500 / trang')]"); // Chọn số lượng hiển thị
    }

    @Test(priority = 0)
    public void testVehicleStatistics() {
        List<Vehicle> vehicles = getVehicles(); // Lấy danh sách phương tiện
        printStatistics(vehicles); // In thống kê
    }

    public List<Vehicle> getVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        // Đợi cho bảng phương tiện hiển thị
        validateHelpers.waitForElementVisible("xpath=//div[@class='ant-pro-table pr-2 css-1fey1ps']", 60);

        List<ElementHandle> rows = page.querySelectorAll("xpath=//div[@class='ant-table-body']//tr"); // Lấy tất cả hàng trong bảng
        System.out.println("Số hàng tìm thấy: " + rows.size());

        for (int i = 1; i < rows.size(); i++) {
            ElementHandle row = rows.get(i);
            List<ElementHandle> cells = row.querySelectorAll("xpath=.//td"); // Lấy tất cả cột trong hàng
            if (cells.size() >= 4) {
                String vehicleType = cells.get(0).textContent(); // Loại phương tiện
                String licensePlate = cells.get(1).textContent(); // Biển số xe
                String status = cells.get(2).textContent(); // Trạng thái
                String recordedTime = cells.get(3).textContent(); // Thời gian ghi nhận
                vehicles.add(new Vehicle(vehicleType, licensePlate, status, recordedTime)); // Thêm phương tiện vào danh sách
            } else {
                System.out.println("Hàng không đủ số cột: " + row.textContent()); // In ra hàng không đủ cột
            }
        }
        return vehicles; // Trả về danh sách phương tiện
    }

    public int getTotalVehicleCount(List<Vehicle> vehicles) {
        return vehicles.size(); // Trả về tổng số phương tiện
    }

    public void printStatistics(List<Vehicle> vehicles) {
        int runningVehicles = 0;
        int stoppedOrParkedVehicles = 0; // Gộp cả "Đỗ" và "Dừng"
        int disconnectedVehicles = 0;

        for (Vehicle vehicle : vehicles) {
            String status = vehicle.getStatus();
            if (status.contains("Chạy")) {
                runningVehicles++;
            } else if (status.contains("Đỗ") || status.contains("Dừng")) { // Gộp hai trạng thái
                stoppedOrParkedVehicles++;
            } else if (status.contains("Mất tín hiệu")) {
                disconnectedVehicles++;
            } else {
                System.out.println("Trạng thái không xác định: " + status);
            }
        }

        // In ra thống kê phương tiện
        System.out.println("Tổng số phương tiện: " + getTotalVehicleCount(vehicles));
        System.out.println("Số phương tiện Đỗ và Dừng: " + stoppedOrParkedVehicles);
        System.out.println("Số phương tiện Mất tín hiệu: " + disconnectedVehicles);
        System.out.println("Số phương tiện Chạy: " + runningVehicles);
    }

    @AfterClass
    public void tearDown() {
        if (browser != null) {
            browser.close(); // Đóng trình duyệt
        }
        if (playwright != null) {
            playwright.close(); // Đóng Playwright
        }
    }

    public SoLuongPhuongTien.VehicleCounts getVehicleCounts() {
        List<Vehicle> vehicles = getVehicles();
        int runningVehicles = 0;
        int stoppedOrParkedVehicles = 0; // Gộp cả "Đỗ" và "Dừng"
        int disconnectedVehicles = 0;

        for (Vehicle vehicle : vehicles) {
            String status = vehicle.getStatus();
            if (status.contains("Chạy")) {
                runningVehicles++;
            } else if (status.contains("Đỗ") || status.contains("Dừng")) { // Gộp hai trạng thái
                stoppedOrParkedVehicles++;
            } else if (status.contains("Mất tín hiệu")) {
                disconnectedVehicles++;
            }
        }

        return new SoLuongPhuongTien.VehicleCounts(runningVehicles, stoppedOrParkedVehicles, disconnectedVehicles); // Trả về số lượng phương tiện
    }
}