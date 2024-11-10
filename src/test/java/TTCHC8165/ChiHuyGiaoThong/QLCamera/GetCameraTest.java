package TTCHC8165.ChiHuyGiaoThong.QLCamera;

import TTCHC8165.Login.LoginHelper;
import base.ValidateHelpersPlayWright; // Cập nhật import cho ValidateHelpersPlayWright
import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class GetCameraTest {
    private Playwright playwright; // Thay thế WebDriver bằng Playwright
    private Browser browser;
    private Page page;
    private LoginHelper loginHelper;
    private ValidateHelpersPlayWright validateHelpers;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // Khởi động trình duyệt
        page = browser.newPage();
        validateHelpers = new ValidateHelpersPlayWright(page); // Khởi tạo ValidateHelpersPlayWright
        loginHelper = new LoginHelper(page, validateHelpers); // Khởi tạo LoginHelper

        page.navigate("http://10.0.0.218/"); // Điều hướng đến trang
        validateHelpers.waitForPageLoaded(); // Đợi trang tải xong
        loginHelper.login("007007", "123456"); // Đăng nhập
        validateHelpers.waitForPageLoaded(); // Đợi trang tải xong

        // Click vào các phần tử trong giao diện
        page.click("xpath=//span[contains(text(),'Chỉ huy giao thông')]"); // Click vào menu
        page.click("xpath=//span[contains(text(),'QL Camera')]"); // Click vào phần quản lý camera
        page.click("xpath=//span[contains(text(),'50 / trang')]"); // Chọn số lượng hiển thị
        page.click("xpath=//div[contains(text(),'500 / trang')]"); // Chọn số lượng hiển thị
    }

    @Test(priority = 0)
    public void testCountCameras() {
        List<GetCamera> cameras = getCamerasFromAllPages(); // Lấy danh sách camera từ tất cả các trang
        printStatistics(cameras); // In thống kê camera
    }

    public List<GetCamera> getCamerasFromAllPages() {
        List<GetCamera> cameras = new ArrayList<>();

        // Lặp qua các trang để lấy dữ liệu camera
        while (true) {
            validateHelpers.waitForElementVisible("xpath=//div[@class='ant-table-container']", 60); // Đợi bảng camera hiển thị
            List<ElementHandle> rows = page.querySelectorAll("xpath=//div[@class='ant-table-body']//tr"); // Lấy tất cả hàng trong bảng
            System.out.println("Số hàng tìm thấy: " + rows.size());

            for (int i = 1; i < rows.size(); i++) {
                ElementHandle row = rows.get(i);
                List<ElementHandle> cells = row.querySelectorAll("xpath=.//td"); // Lấy tất cả cột trong hàng
                if (cells.size() >= 12) {
                    String statusCamera = cells.get(5).textContent(); // Lấy trạng thái camera
                    cameras.add(new GetCamera(statusCamera)); // Thêm camera vào danh sách
                } else {
                    System.out.println("Hàng không đủ số cột: " + row.textContent()); // In ra hàng không đủ cột
                }
            }

            // Kiểm tra xem có nút tiếp theo không
            List<ElementHandle> nextButtons = page.querySelectorAll("xpath=//a[normalize-space()='2']");
            if (nextButtons.size() > 0 && nextButtons.get(0).isVisible()) {
                nextButtons.get(0).click(); // Click vào nút tiếp theo
                validateHelpers.waitForPageLoaded(); // Đợi trang mới tải
            } else {
                break; // Không còn trang tiếp theo
            }
        }
        return cameras; // Trả về danh sách camera
    }

    public int getTotalCameraCount(List<GetCamera> cameras) {
        return cameras.size(); // Trả về tổng số camera
    }

    public void printStatistics(List<GetCamera> cameras) {
        int disconnectCamera = 0;
        int connectCamera = 0;

        // Đếm số lượng camera kết nối và mất kết nối
        for (GetCamera camera : cameras) {
            String statusCamera = camera.getStatusCamera();
            if (statusCamera.equalsIgnoreCase("Mất kết nối")) {
                disconnectCamera++;
            } else if (statusCamera.equalsIgnoreCase("Đã kết nối") || (statusCamera.equalsIgnoreCase("CAMERA_WORKING"))) {
                connectCamera++;
            } else {
                System.out.println("Trạng thái không xác định: " + statusCamera);
            }
        }

        // In ra thống kê camera
        System.out.println("Tổng số Camera: " + getTotalCameraCount(cameras));
        System.out.println("Số Camera Đã kết nối: " + connectCamera);
        System.out.println("Số Camera Mất kết nối: " + disconnectCamera);
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
}