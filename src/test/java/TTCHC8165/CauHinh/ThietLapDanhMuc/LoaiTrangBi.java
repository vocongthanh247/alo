package TTCHC8165.CauHinh.ThietLapDanhMuc;

import TTCHC8165.Login.LoginHelper;
import base.ValidateHelpersPlayWright;
import com.microsoft.playwright.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class LoaiTrangBi {
    private Playwright playwright; // Thay thế WebDriver bằng Playwright
    private Browser browser;
    private Page page;
    private ValidateHelpersPlayWright validateHelpersPlayWright;
    private LoginHelper loginHelper;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // Khởi động trình duyệt
        page = browser.newPage();
        validateHelpersPlayWright = new ValidateHelpersPlayWright(page); // Khởi tạo ValidateHelpersPlayWright
        loginHelper = new LoginHelper(page, validateHelpersPlayWright); // Khởi tạo LoginHelper
        page.navigate("http://10.0.0.218/");
        validateHelpersPlayWright.waitForPageLoaded(); // Đợi trang tải xong
        loginHelper.login("007007", "123456"); // Đăng nhập
        validateHelpersPlayWright.waitForPageLoaded(); // Đợi trang tải xong

        // Click vào các phần tử trong giao diện
        validateHelpersPlayWright.clickElement("//body/div[@id='root']/div[1]/div[1]/div[2]/div[2]/aside[1]/div[1]/div[1]/ul[1]/li[11]/div[1]/span[1]"); // Click vào menu
        validateHelpersPlayWright.clickElement("//span[contains(text(),'Thiết lập danh mục')]"); // Click vào bản đồ phương tiện
        validateHelpersPlayWright.clickElement("//span[contains(text(),'Loại trang bị')]");
        validateHelpersPlayWright.isElementPresent("//div[@class='ant-space-item']//span[contains(text(),'Loại trang bị')]");
        System.out.println("Đã vào tới trang loại trang bị");

        validateHelpersPlayWright.waitForElementVisible(".ant-table-body", 60);
        Thread.sleep(2000);
// Lấy tất cả hàng trong bảng
        List<ElementHandle> rows = page.querySelectorAll(".ant-table-body tr");
        System.out.println("Số hàng tìm thấy: " + rows.size());
// Duyệt qua các hàng bắt đầu từ hàng thứ hai (bỏ qua hàng tiêu đề)
        for (int i = 1; i < rows.size(); i++) { // Bắt đầu từ 1 để bỏ qua hàng đầu tiên
            ElementHandle row = rows.get(i); // Lấy hàng thứ i
            // Lấy tất cả các ô trong hàng
            List<ElementHandle> cells = row.querySelectorAll("td");
            // Kiểm tra xem hàng có ô nào không
            if (cells.isEmpty()) {
                System.out.println("Hàng trống hoặc không có ô: " + row.innerHTML());
            } else {
                // Duyệt qua từng ô trong hàng
                for (ElementHandle cell : cells) {
                    String cellData = cell.innerText(); // Lấy văn bản trong ô
                    System.out.println("Dữ liệu ô: " + cellData);
                }
            }
        }
    }
    @Test
    public void fullLoaiTrangbi(){
        //nhan vao them moi
        validateHelpersPlayWright.clickElement("//span[contains(text(),'Thêm mới')]");
        //get title
        validateHelpersPlayWright.assertElementText("//div[@class='text-[16px]']","Tạo loại trang bị mới");
        //kiem tra nut "X" hoat dong
        validateHelpersPlayWright.clickElement("//span[@aria-label='close']//*[name()='svg']");
        validateHelpersPlayWright.clickElement("//span[contains(text(),'Thêm mới')]");
        //kiem tra nut "Huy" hoat dong
        validateHelpersPlayWright.clickElement("//span[contains(text(),'Hủy')]");
        //click them moi de check validate khi khong nhap truong nao ca
        validateHelpersPlayWright.clickElement("//span[contains(text(),'Thêm mới')]");
        //check validate
        validateHelpersPlayWright.clickElement("//button[contains(text(),'Thêm mới')]");
        //validate phan loai
        validateHelpersPlayWright.assertElementText("//div[@id='equipmentGroupTypeCode_help']//div[1]","Vui lòng chọn một trong hai!");
        validateHelpersPlayWright.assertElementText("//div[@id='equipmentGroupCode_help']//div[1]","Vui lòng nhập mã loại trang bị!");
        validateHelpersPlayWright.assertElementText("//div[@id='equipmentGroupName_help']//div[1]","Vui lòng nhập tên loại trang bị!");
        validateHelpersPlayWright.assertElementText("//div[@id='equipmentGroupStatusCode_help']//div[1]","Vui lòng chọn trạng thái!");
        // kiem tra khi click vao phuong tien hoac cong cu thi validate bien mat
        validateHelpersPlayWright.clickElement("//input[@value='VEHICLE']");
        validateHelpersPlayWright.clickElement("//input[@value='EQUIPMENT']");
        validateHelpersPlayWright.checkElementVisibility(page,"//div[@id='equipmentGroupTypeCode_help']//div[1]");
        //kiem tra khi nhap Ma loai trang bi thi validate bien mat
        validateHelpersPlayWright.setText("//input[@id='equipmentGroupCode']","24710");
        validateHelpersPlayWright.checkElementVisibility(page,"//div[@id='equipmentGroupCode_help']//div[1]");
        //kiem tra khi nhap Ten loai trang bi thi validate bien mat
        validateHelpersPlayWright.setText("//input[@id='equipmentGroupName']","SungMay2410");
        validateHelpersPlayWright.checkElementVisibility(page,"//div[@id='equipmentGroupName_help']//div[1]");
        //kiem tra khi chon trang thai thi validate bien mat
        validateHelpersPlayWright.clickElement("//input[@id='equipmentGroupStatusCode']");
        validateHelpersPlayWright.clickElement("//div[@title='Kích hoạt']//div[1]");
        validateHelpersPlayWright.checkElementVisibility(page,"//div[@id='equipmentGroupStatusCode_help']//div[1]");
        //click them moi trang thai kich hoat
        validateHelpersPlayWright.clickElement("//button[contains(text(),'Thêm mới')]");
        //nhan lam moi
        validateHelpersPlayWright.clickElement("//span[@aria-label='reload']//*[name()='svg']");
        // so luong hang sau khi them moi
        // Đợi phần tử hiển thị
        validateHelpersPlayWright.waitForElementVisible("//div[@id='equipment-group']", 60);
        List<ElementHandle> rows = page.querySelectorAll("//div[@id='equipment-group']//tr"); // Lấy tất cả hàng trong bảng
        System.out.println("Số hàng tìm thấy: " + rows.size());
// Duyệt qua các hàng bắt đầu từ hàng thứ hai (bỏ qua hàng tiêu đề)
        for (int i = 1; i < rows.size(); i++) { // Bắt đầu từ 1 để bỏ qua hàng tiêu đề
            ElementHandle row = rows.get(i); // Lấy hàng thứ i
            // Lấy tất cả các ô trong hàng
            List<ElementHandle> cells = row.querySelectorAll("td");
            // Kiểm tra xem hàng có ô nào không và có dữ liệu không
            if (!cells.isEmpty()) {
                boolean hasData = false; // Cờ kiểm tra xem hàng có dữ liệu không
                for (ElementHandle cell : cells) {
                    String cellData = cell.innerText(); // Lấy văn bản trong ô
                    System.out.println("Dữ liệu ô: " + cellData);
                    if (!cellData.trim().isEmpty()) { // Kiểm tra ô có dữ liệu không
                        hasData = true;
                    }
                }
            }
        }
// Tương tác với hàng đầu tiên nếu có dữ liệu
        if (rows.size() > 1) {
            ElementHandle firstRow = rows.get(2); // Hàng đầu tiên (chỉ số 1)
            firstRow.click(); // Click vào hàng đầu tiên

            // Kiểm tra enabled của nút xóa
            validateHelpersPlayWright.isButtonEnabled("//tbody/tr[2]/td[5]/div[1]/div[4]");

            // Click vào chỉnh sửa và lấy title
            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[1]"); // Hoặc bạn có thể thực hiện các tương tác khác tại đây
            validateHelpersPlayWright.assertElementText("//div[contains(text(),'Cập nhật loại trang bị mới')]","Cập nhật loại trang bị mới");

            // Kiểm tra nút "X" hoạt động
            validateHelpersPlayWright.clickElement("//div[@class='page-content']//div[2]//div[1]//div[1]//div[1]//div[3]//div[1]//div[1]//button[1]//span[1]");


            // Kiểm tra nút "Hủy" hoạt động
            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[1]"); // Hoặc bạn có thể thực hiện các tương tác khác tại đây
            validateHelpersPlayWright.clickElement("(//button[@aria-label='Close'])[2]");

            // kiem tra validate cap nhat
            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[1]"); // Hoặc bạn có thể thực hiện các tương tác khác tại đây
            validateHelpersPlayWright.clearText("(//input[@id='equipmentGroupCode'])[2]");
            validateHelpersPlayWright.clearText("(//input[@id='equipmentGroupName'])[2]");
            validateHelpersPlayWright.clickElement("//button[contains(text(),'Cập nhật')]");
            validateHelpersPlayWright.assertElementText("//div[@id='equipmentGroupCode_help']//div[1]","Vui lòng nhập mã loại trang bị!");
            validateHelpersPlayWright.assertElementText("//div[@id='equipmentGroupName_help']//div[1]","Vui lòng nhập tên loại trang bị!");
            validateHelpersPlayWright.setText("(//input[@id='equipmentGroupCode'])[2]","24800");
            validateHelpersPlayWright.assertElementDisappeared("//div[@id='equipmentGroupCode_help']//div[1]");
            validateHelpersPlayWright.setText("(//input[@id='equipmentGroupName'])[2]","SungMay247900");
            validateHelpersPlayWright.assertElementDisappeared("//div[@id='equipmentGroupName_help']//div[1]");
            validateHelpersPlayWright.clickElement("//button[contains(text(),'Cập nhật')]");

            // Kiểm tra nút cập nhật hoạt động
//            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[1]"); // Hoặc bạn có thể thực hiện các tương tác khác tại đây
//            validateHelpersPlayWright.clickElement("//button[contains(text(),'Cập nhật')]");
            validateHelpersPlayWright.assertElementText("//div[@class='ant-notification-notice-description']","Cập nhật loại trang bị thành công!");

            // Kiểm tra có thể nhấn nút hủy đổi trạng thái
            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[3]");
            validateHelpersPlayWright.clickElement("(//span[contains(text(),'Hủy')])[3]");

            // Lấy title và thông báo trạng thái trang bị
            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[3]");
            validateHelpersPlayWright.assertElementText("//div[@class='ant-popconfirm-title']","Khóa loại trang bị");
            validateHelpersPlayWright.assertElementText("//div[@class='ant-popconfirm-description']","Bạn có chắc chắn khóa loại trang bị này?");

            // Kiểm tra có thể nhấn đổi trạng thái
//            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[3]");
            validateHelpersPlayWright.clickElement("//span[contains(text(),'Đồng ý')]");
            validateHelpersPlayWright.assertElementText("//div[@class='ant-notification-notice-description']","Cập nhật loại trang bị thành công!");

            // Đổi lại trạng thái thêm 1 lần nữa
            validateHelpersPlayWright.clickElement("//td[@class='ant-table-cell ant-table-cell-fix-right ant-table-cell-fix-right-first ant-table-cell-row-hover']//div[2]");
            validateHelpersPlayWright.clickElement("//span[contains(text(),'Đồng ý')]");

            // Kiểm tra title và thông báo của nút "Xóa"
            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[4]");
            validateHelpersPlayWright.assertElementText("//div[contains(text(),'Bạn có chắc chắn xoá loại trang bị này?')]","Bạn có chắc chắn xoá loại trang bị này?");

            // Kiểm tra có thể xóa loại trang bị
            validateHelpersPlayWright.clickElement("//span[contains(text(),'Đồng ý')]");
        }
                //kiem tra co the them moi trang thai khong kich hoat
            try {
            // Kiểm tra xem element có tồn tại không
            boolean elementExists = page.isVisible("//span[@class='anticon anticon-close ant-notification-notice-close-icon']//*[name()='svg']");

            if (elementExists) {
                System.out.println("Tìm thấy element, thực hiện click để tắt");
                validateHelpersPlayWright.clickElement("//span[@class='anticon anticon-close ant-notification-notice-close-icon']//*[name()='svg']");
            } else {
                System.out.println("Không tìm thấy element, bỏ qua");
            }
            } catch (Exception e) {
            System.out.println("Không tìm thấy element hoặc không thể tương tác: " + e.getMessage());
            // Tiếp tục thực thi không dừng test
            }
            validateHelpersPlayWright.clickElement("//span[contains(text(),'Thêm mới')]");
            validateHelpersPlayWright.clickElement("//input[@value='VEHICLE']");
            validateHelpersPlayWright.setText("//input[@id='equipmentGroupCode']","2472");
            validateHelpersPlayWright.setText("//input[@id='equipmentGroupName']","SungMay2472");
            validateHelpersPlayWright.clickElement("//input[@id='equipmentGroupStatusCode']");
            validateHelpersPlayWright.clickElement("//div[@title='Kích hoạt']//div[1]");
            validateHelpersPlayWright.clickElement("//button[contains(text(),'Thêm mới')]");

    }
}
