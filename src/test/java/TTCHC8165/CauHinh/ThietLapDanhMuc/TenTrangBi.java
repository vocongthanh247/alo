package TTCHC8165.CauHinh.ThietLapDanhMuc;

import TTCHC8165.Login.LoginHelper;
import base.ValidateHelpersPlayWright;
import com.microsoft.playwright.*;
import org.junit.Before;
import org.junit.BeforeClass;
//import org.junit.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TenTrangBi {
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private ValidateHelpersPlayWright validateHelpersPlayWright;
    private LoginHelper loginHelper;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        validateHelpersPlayWright = new ValidateHelpersPlayWright(page);
        loginHelper = new LoginHelper(page, validateHelpersPlayWright);
        page.navigate("http://10.0.0.218/");
        validateHelpersPlayWright.waitForPageLoaded();
        loginHelper.login("007007", "123456");
        validateHelpersPlayWright.waitForPageLoaded();
        // Additional setup code...
        validateHelpersPlayWright.clickElement("//body/div[@id='root']/div[1]/div[1]/div[2]/div[2]/aside[1]/div[1]/div[1]/ul[1]/li[11]/div[1]/span[1]"); // Click vào menu
        validateHelpersPlayWright.clickElement("//span[contains(text(),'Thiết lập danh mục')]"); // Click vào bản đồ phương tiện
        validateHelpersPlayWright.clickElement("//span[contains(text(),'Tên trang bị')]");
        validateHelpersPlayWright.isElementPresent("//div[@class='ant-space-item']//span[contains(text(),'Tên trang bị')]");
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
    public void testFullTenTrangBi() {
        //duyet so luong hang truoc khi them moi

        //nhan vao them moi
        validateHelpersPlayWright.clickElement("//span[contains(text(),'Thêm mới')]");
        //get title
        validateHelpersPlayWright.assertElementText("//div[@class='text-[16px]']","Tạo mới tên trang bị");
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
        validateHelpersPlayWright.assertElementText("//div[@id='equipmentTypeCode_help']//div[1]","Vui lòng chọn trạng tên trang bị!");
        validateHelpersPlayWright.assertElementText("//div[@id='equipmentTypeName_help']//div[1]","Vui lòng nhập tên tên trang bị");
        validateHelpersPlayWright.assertElementText("//div[@id='equipmentGroupCode_help']//div[1]","Vui lòng chọn loại trang bị!");
        validateHelpersPlayWright.assertElementText("//div[@id='equipmentTypeStatusCode_help']//div[1]","Vui lòng chọn trạng thái!");
        // kiem tra khi click vao phuong tien hoac cong cu thi validate bien mat
        //kiem tra khi nhap Ma tên trang bi thi validate bien mat
        validateHelpersPlayWright.setText("//input[@id='equipmentTypeCode']","2476");
        validateHelpersPlayWright.checkElementVisibility(page,"//div[@id='equipmentTypeCode_help']//div[1]");
        //kiem tra khi nhap Ten loai trang bi thi validate bien mat
        validateHelpersPlayWright.setText("//input[@id='equipmentTypeName']","SungMay2479");
        validateHelpersPlayWright.checkElementVisibility(page,"//div[@id='equipmentTypeName_help']//div[1]");
        //kiem tra khi chon loai thiet bi thi mat validate
        // Click để mở dropdown
        validateHelpersPlayWright.clickElement("//input[@id='equipmentGroupCode']");
        page.waitForTimeout(1000); // đợi dropdown hiện ra

        try {
            // Cách 1: Thông qua ul/li
            if (page.isVisible("//ul[contains(@class, 'dropdown-menu')]//li[1]")) {
                String optionText = page.textContent("//ul[contains(@class, 'dropdown-menu')]//li[1]");
                System.out.println("Đã chọn option (ul/li): " + optionText);
                validateHelpersPlayWright.clickElement("//ul[contains(@class, 'dropdown-menu')]//li[1]");
            }
            // Cách 2: Thông qua div role="option"
            else if (page.isVisible("//div[@role='option'][1]")) {
                String optionText = page.textContent("//div[@role='option'][1]");
                System.out.println("Đã chọn option (role=option): " + optionText);
                validateHelpersPlayWright.clickElement("//div[@role='option'][1]");
            }
            // Cách 3: Thông qua class phổ biến
            else if (page.isVisible("//div[contains(@class, 'select-item')][1]")) {
                String optionText = page.textContent("//div[contains(@class, 'select-item')][1]");
                System.out.println("Đã chọn option (select-item): " + optionText);
                validateHelpersPlayWright.clickElement("//div[contains(@class, 'select-item')][1]");
            }
            // Cách 4: Sử dụng keyboard
            else {
                System.out.println("Sử dụng keyboard để chọn option đầu tiên");
                page.keyboard().press("ArrowDown");
                page.waitForTimeout(500);
                String selectedValue = page.inputValue("//input[@id='equipmentGroupCode']");
                System.out.println("Giá trị được chọn: " + selectedValue);
                page.keyboard().press("Enter");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi chọn option: " + e.getMessage());
        }

        // Kiểm tra giá trị cuối cùng được chọn
        page.waitForTimeout(500);
        String finalValue = page.inputValue("//input[@id='equipmentGroupCode']");
        System.out.println("Giá trị cuối cùng trong input: " + finalValue);
        validateHelpersPlayWright.clickElement("//input[@id='equipmentTypeStatusCode']");
        validateHelpersPlayWright.clickElement("//div[@title='Kích hoạt']//div[1]");
        validateHelpersPlayWright.clickElement("//button[contains(text(),'Thêm mới')]");
        validateHelpersPlayWright.checkElementVisibility(page,"//div[@id='equipmentGroupStatusCode_help']//div[1]");
        //kiem tra loai trang bi da bi disable nut button xoa
        validateHelpersPlayWright.clickElement("//span[contains(text(),'Loại trang bị')]");
        validateHelpersPlayWright.checkButtonDisabled("//tbody/tr[2]/td[5]/div[1]/div[4]/button[1]");








        //click them moi trang thai kich hoat
//        validateHelpersPlayWright.clickElement("//button[contains(text(),'Thêm mới')]");
//        //nhan lam moi
//        validateHelpersPlayWright.clickElement("//span[@aria-label='reload']//*[name()='svg']");
//        // so luong hang sau khi them moi
//        // Đợi phần tử hiển thị
//        validateHelpersPlayWright.waitForElementVisible("//div[@id='equipment-group']", 60);
//        List<ElementHandle> rows = page.querySelectorAll("//div[@id='equipment-group']//tr"); // Lấy tất cả hàng trong bảng
//        System.out.println("Số hàng tìm thấy: " + rows.size());
//
//// Duyệt qua các hàng bắt đầu từ hàng thứ hai (bỏ qua hàng tiêu đề)
//        for (int i = 1; i < rows.size(); i++) { // Bắt đầu từ 1 để bỏ qua hàng tiêu đề
//            ElementHandle row = rows.get(i); // Lấy hàng thứ i
//
//            // Lấy tất cả các ô trong hàng
//            List<ElementHandle> cells = row.querySelectorAll("td");
//
//            // Kiểm tra xem hàng có ô nào không và có dữ liệu không
//            if (!cells.isEmpty()) {
//                boolean hasData = false; // Cờ kiểm tra xem hàng có dữ liệu không
//                for (ElementHandle cell : cells) {
//                    String cellData = cell.innerText(); // Lấy văn bản trong ô
//                    System.out.println("Dữ liệu ô: " + cellData);
//                    if (!cellData.trim().isEmpty()) { // Kiểm tra ô có dữ liệu không
//                        hasData = true;
//                    }
//                }
//            }
//        }
//
//// Tương tác với hàng đầu tiên nếu có dữ liệu
//        if (rows.size() > 1) {
//            ElementHandle firstRow = rows.get(2); // Hàng đầu tiên (chỉ số 1)
//            firstRow.click(); // Click vào hàng đầu tiên
//
//            // Kiểm tra enabled của nút xóa
//            validateHelpersPlayWright.isButtonEnabled("//tbody/tr[2]/td[5]/div[1]/div[4]");
//
//            // Click vào chỉnh sửa và lấy title
//            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[1]"); // Hoặc bạn có thể thực hiện các tương tác khác tại đây
//            validateHelpersPlayWright.assertElementText("//div[contains(text(),'Cập nhật loại trang bị mới')]","Cập nhật loại trang bị mới");
//
//            // Kiểm tra nút "X" hoạt động
//            validateHelpersPlayWright.clickElement("//div[@class='ant-modal-root css-1fey1ps']//div[2]//div[1]//div[1]//div[1]//button[1]//span[1]//span[1]//*[name()='svg']");
//
//            // Kiểm tra nút "Hủy" hoạt động
//            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[1]"); // Hoặc bạn có thể thực hiện các tương tác khác tại đây
//            validateHelpersPlayWright.clickElement("//div[@class='page-content']//div[2]//div[1]//div[1]//div[1]//div[3]//div[1]//div[1]//button[1]//span[1]");
//
//            // Kiểm tra nút cập nhật hoạt động
//            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[1]"); // Hoặc bạn có thể thực hiện các tương tác khác tại đây
//            validateHelpersPlayWright.clickElement("//button[contains(text(),'Cập nhật')]");
//            validateHelpersPlayWright.assertElementText("//div[@class='ant-notification-notice-description']","Thành công! Cập nhật loại trang bị thành công!");
//
//            // Kiểm tra có thể nhấn nút hủy đổi trạng thái
//            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[3]");
//            validateHelpersPlayWright.clickElement("//button[@class='ant-btn css-1fey1ps ant-btn-default ant-btn-color-default ant-btn-variant-outlined ant-btn-sm']//span[contains(text(),'Hủy')]");
//
//            // Lấy title và thông báo trạng thái trang bị
//            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[3]");
//            validateHelpersPlayWright.assertElementText("//div[@class='ant-popconfirm-title']","Khóa loại trang bị");
//            validateHelpersPlayWright.assertElementText("//div[@class='ant-popconfirm-description']","Bạn có chắc chắn khóa loại trang bị này?");
//
//            // Kiểm tra có thể nhấn đổi trạng thái
////            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[3]");
//            validateHelpersPlayWright.clickElement("//span[contains(text(),'Đồng ý')]");
//            validateHelpersPlayWright.assertElementText("//div[@class='ant-notification-notice-description']","Cập nhật trạng thái trang bị thành công!");
//
//            // Đổi lại trạng thái thêm 1 lần nữa
//            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[3])");
//            validateHelpersPlayWright.clickElement("//span[contains(text(),'Đồng ý')]");
//
//            // Kiểm tra title và thông báo của nút "Xóa"
//            validateHelpersPlayWright.clickElement("//tbody/tr[2]/td[5]/div[1]/div[4]");
//            validateHelpersPlayWright.assertElementText("//font[contains(text(),'Deleted page')]","Xóa trang bị");
//            validateHelpersPlayWright.assertElementText("//font[contains(text(),'Bạn có chắc chắn xóa loại trang này không?')]","Bạn có chắc chắn xóa loại trang này không?");
//
//            // Kiểm tra có thể tắt nút "Xóa" bằng nút "Hủy"
//            validateHelpersPlayWright.clickElement("//button[@class='ant-btn css-1fey1ps ant-btn-default ant-btn-color-default ant-btn-variant-outlined ant-btn-sm']//span//font//font[contains(text(),'Hủy')]");
//
//            // Kiểm tra có thể xóa loại trang bị
//            validateHelpersPlayWright.clickElement("//button[@class='ant-btn css-aa9naf ant-btn-primary ant-btn-color-primary ant-btn-variant-solid ant-btn-icon-only ant-tooltip-open']//span[@role='img']//*[name()='svg']");
//            validateHelpersPlayWright.clickElement("//font[contains(text(),'Đồng ý')]");
//        }
//        //kiem tra co the them moi trang thai khong kich hoat
//        validateHelpersPlayWright.clickElement("//span[contains(text(),'Thêm mới')]");
//        validateHelpersPlayWright.setText("//input[@id='equipmentGroupCode']","2472");
//        validateHelpersPlayWright.setText("//input[@id='equipmentGroupName']","SungMay2472");
//        validateHelpersPlayWright.clickElement("//input[@id='equipmentGroupStatusCode']");
//        validateHelpersPlayWright.clickElement("//div[@title='Chưa kích hoạt']//div[1]");
//        validateHelpersPlayWright.clickElement("//div[@class='ant-modal-root css-1fey1ps']//button[2]");








        // Your test logic here...
    }
}
