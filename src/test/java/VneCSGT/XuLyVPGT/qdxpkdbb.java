package VneCSGT.XuLyVPGT;

import base.User;
import base.ValidateHelpers;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.junit.Test;
//import org.junit.Before;
//import org.junit.After;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

public class qdxpkdbb{
    private AndroidDriver driver;
    private AppiumDriverLocalService service;
    private User.LoginHelper loginHelper;
    private ValidateHelpers validateHelpers;
@BeforeClass
public void setUp() throws IOException, InterruptedException {
    // Khởi tạo dịch vụ Appium
    service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(4723));
    service.start();

    // Thiết lập DesiredCapabilities
    DesiredCapabilities capabilities = new DesiredCapabilities();

    // Sử dụng chuỗi cho các capability
    capabilities.setCapability("deviceName", "RF8X50FF96M");
    capabilities.setCapability("udid", "RF8X50FF96M");
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("platformVersion", "14");
    capabilities.setCapability("automationName", "UiAutomator2");
    capabilities.setCapability("noReset", true);
    capabilities.setCapability("fullReset", false);
    capabilities.setCapability("autoGrantPermissions", true);
    capabilities.setCapability("autoDismissAlerts", true);
    capabilities.setCapability("skipLogCapture", true);


    // Khởi tạo driver
    URL url = new URL("http://127.0.0.1:4723");
    driver = new AndroidDriver(url, capabilities);
    loginHelper = new User.LoginHelper(driver); // Khởi tạo LoginHelper
    validateHelpers = new ValidateHelpers(driver);
    driver.activateApp("com.ots.c08.vnecsgt.cbcs");
    Thread.sleep(3000);
    if (!loginHelper.isLoggedIn()) {
        loginHelper.performLogin();
        Thread.sleep(2000);
    }


}

    @Test(priority = 0)
    public void validateQDXPKDBB() throws InterruptedException, IOException {
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/navigation_bar_item_icon_view\"])[4]"));
        validateHelpers.clickElement(By.xpath("//android.widget.ImageButton[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnAddViolation\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvAdd\"])[4]"));
        //1.Check validate Số quyết định
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.compareElementText("Vui lòng nhập số quyết định xử phạt");
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@text=\"Nhập số quyết định\"]"), "247");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.verifyTextNotPresent("Vui lòng nhập số quyết định xử phạt");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.compareElementText("Vui lòng nhập căn cứ xử phạt");
        validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/edtBaseAt"));
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@text=\"Nhập căn cứ\"]"), "385c, Nguyễn Cư Trinh, Nguyễn Trãi, Quận 1");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.verifyTextNotPresent("Vui lòng nhập căn cứ xử phạt");
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@text=\"Nhập căn cứ điều\"]"), "khoản 7 Mục V Phụ lục II ban hành kèm theo Nghị định về công tác văn thư");
        validateHelpers.clickAndInputNghiDinh("142/2024/NĐ-CP");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/tvIssueDate"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnBack"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/tvIssueDate"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
        validateHelpers.clickElement(By.xpath("//android.widget.EditText[@text=\"Nhập số QĐ\"]"));
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@text=\"Nhập số QĐ\"]"), "457");
        validateHelpers.clickElement(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvTime\"])[2]"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnBack"));
        validateHelpers.clickElement(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvTime\"])[2]"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
        validateHelpers.scrollDownSmall();
        //2. thời gian và địa điểm
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/pickerTime"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/tvCancel"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/pickerTime"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/tvConfirm"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/pickerDate"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnBack"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/pickerDate"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/ddRoad"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnBack"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/ddRoad"));
        validateHelpers.clickElement(AppiumBy.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.scrollDownSmall();
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.compareElementText("Vui lòng nhập địa điểm xử phạt");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/tvLocation"));
        validateHelpers.checkAllProvinces();
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.verifyTextNotPresent("Vui lòng nhập địa điểm xử phạt");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/ddRoadType"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnBack"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/ddRoadType"));
        validateHelpers.clickElement(AppiumBy.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
//        validateHelpers.scrollDownOnce();
        validateHelpers.clickElement(By.xpath("//android.widget.EditText[@text=\"Nhập tên đường\"]"));
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@text=\"Nhập tên đường\"]"), "143 Võ Duy Ninh, phường 22, quận Bình Thạnh");
        validateHelpers.clickElement(By.xpath("//android.widget.EditText[@text=\"Nhập km/ số nhà\"]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@text=\"Nhập km/ số nhà\"]"), "20");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.compareElementText("Vui lòng nhập địa chỉ xảy ra xử phạt");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/iconEnd"));
        Thread.sleep(2000);
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.verifyTextNotPresent("Vui lòng nhập địa chỉ xảy ra xử phạt");
        //3. Thông tin bên vi phạm
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.compareElementText("Vui lòng nhập họ và tên cá nhân vi phạm");
        validateHelpers.clickAndInput(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/edtPersonalName"), "Nguyễn Đặng Trường Duy");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.verifyTextNotPresent("Vui lòng nhập họ và tên cá nhân vi phạm");
        validateHelpers.clickElement(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvTime\"])[1]"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnBack"));
        validateHelpers.clickElement(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvTime\"])[1]"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/ddPersonalGender"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnBack"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/ddPersonalGender"));
        validateHelpers.clickElement(AppiumBy.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickElement(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\"])[2]"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnBack"));
        validateHelpers.clickElement(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\"])[2]"));
        validateHelpers.clickElement(AppiumBy.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/ddJob"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnBack"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/ddJob"));
        validateHelpers.clickElement(AppiumBy.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.compareElementText("Vui lòng nhập số CMND/CCCD cá nhân vi phạm");
        validateHelpers.clickAndInput(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/edtPersonalId"), "046200001234");
//        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
//        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/tvCancel"));
//        validateHelpers.verifyTextNotPresent("Vui lòng nhập số CMND/CCCD cá nhân vi phạm");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/pickerIssueDate"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnBack"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/pickerIssueDate"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
        validateHelpers.clickAndInput(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/edtIssuePlace"), "CA quân 1");
        validateHelpers.scrollDownOnce();
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnClose"));
        validateHelpers.compareElementText("Vui lòng nhập địa chỉ cá nhân vi phạm");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/tvPersonalAddress"));
        validateHelpers.checkAllProvinces();
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.verifyTextNotPresent("Vui lòng nhập địa chỉ cá nhân vi phạm");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.compareElementText("Vui lòng nhập nơi ở cá nhân vi phạm");
        validateHelpers.clickAndInput(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/edtPersonalCurrentAddress"), "356A Võ Văn Kiệt, Quận 4");
//        4.Vi phạm
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.compareElementText("Vui lòng chọn hành vi vi phạm");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnBack"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/ddViolation"));
        validateHelpers.clickElement(AppiumBy.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
//
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@text=\"Nhập biển số\"]"), "27A46712");
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập quy định tại \"]"), "Quy định chế độ quản lý tài chính, tài sản đối với Cơ quan Việt Nam ở nước ngoài ");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnClose"));
        validateHelpers.compareElementText("Vui lòng nhập địa điểm xảy ra vi phạm");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnAddress"));
        validateHelpers.checkAllProvinces();
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.verifyTextNotPresent("Vui lòng nhập địa điểm xảy ra vi phạm");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.compareElementText("Vui lòng nhập địa điểm chi tiết xảy ra vi phạm");
        validateHelpers.clickElement(By.xpath("//android.widget.EditText[contains(@text, 'Nhập địa điểm chi tiết')]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[contains(@text, 'Nhập địa điểm chi tiết')]"), "185 Trần Hưng Đạo, quận 5");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.verifyTextNotPresent("Vui lòng nhập địa điểm chi tiết xảy ra vi phạm");
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập các tình tiết liên quan\"]"), "Không có");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.compareElementText("Vui lòng chọn hình thức xử phạt");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/radFineWarning"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/radFineMoney"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.verifyTextNotPresent("Vui lòng chọn hình thức xử phạt");
//        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập mức tiền phạt\"]"), "Một triệu đồng");
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập bằng chữ mức tiền phạt\"]"), "Một triệu đồng");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.compareElementText("Vui lòng chọn hình thức xử phạt bổ sung");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/radLicense"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.verifyTextNotPresent("Vui lòng chọn hình thức xử phạt bổ sung");
        validateHelpers.clickElement(By.xpath("//android.widget.EditText[contains(@text, 'Nhập thời hạn')]"));
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[contains(@text, 'Nhập thời hạn')]"), "30 năm");
        validateHelpers.scrollDownSmall();
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/tvTime"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnBack"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/tvTime"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
//        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/radExhibit"));
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập tên tang vật\"]"), "Dao");
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập đơn vị\"]"), "ml");
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập số lượng\"]"), "25");
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập chủng loại \"]"), "Không có");
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập tình trạng, đặc điểm\"]"), "Không có");
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập số tiền\"]"), "1.000.000 Đồng");
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập số tiền bằng chữ\"]"), "Một triệu đồng");
        validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/radExpulsion"));
        validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/tvTime"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnBack"));
        validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/tvTime"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập nơi bị trục xuất đến\"]"), "Nhà tù quận 4");
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập cửa khẩu thi hành quyết định \"]"), "Không có");
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập nơi ở trong thời gian làm trục xuất\"]"), "không có");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/radAdditionalSanction"));
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập căn cứ\"]"), "Quận 3");
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập lý do không áp dụng \"]"), "Không có");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/tvTime"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnBack"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/tvTime"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập biện pháp khắc phục hậu quả \"]"), "Không có");
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập biện pháp\"]"), "Không có");
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập thời hạn\"]"), "Không có");
//        5. Quyết định này được giao
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[contains(@text, 'Nhập đối tượng chấp hành')]"), "Không có");
        validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[contains(@text, 'Nhập đối tượng bị cưỡng chế thi hành')])[1]"), "Không có");
        validateHelpers.clickAndInput(By.xpath("//android.widget.LinearLayout[2]//android.widget.EditText[contains(@text, 'Nhập đối tượng bị cưỡng chế thi hành')]"), "Không có");
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập đối tượng có quyền khiếu nại hoặc khởi kiện hành chính\"]"), "Không có");
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.compareElementText("Vui lòng chọn phương thức nộp phạt");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/radOfficer"));
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/radDirect"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.verifyTextNotPresent("Vui lòng chọn phương thức nộp phạt");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/ddDirectTreasuries"));
        validateHelpers.clickElement(AppiumBy.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.scrollDownOnce();
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/radTransfer"));
        validateHelpers.clickAndInput(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/edtContent"), "1.000.000");
        validateHelpers.clickElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\"]"));
        validateHelpers.clickElement(AppiumBy.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        //6. Nơi gửi
        validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/ddVehicle"));
        validateHelpers.clickElement(AppiumBy.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập tên cá nhân/ tổ chức \"]"), "OTS");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/radUnderagePerson"));
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập họ tên cha/mẹ hoặc người giám hộ\"]"), "Phạm Nhật Huy");
        validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/radForeigner"));
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập tên cơ quan lãnh sự/ đại diện \"]"), "Gtel");
        validateHelpers.clickAndInput(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập tên cơ quan công an quản lý \"]"), "Công an tỉnh");
        //7.Đính kèm biên bản
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.compareElementText("Vui lòng chọn hình ảnh/video");
        validateHelpers.clickElement(By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/itemView\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnLibrary\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imageView\"])[1]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnClose"));
    }

    @Test(priority = 1)
        public void edit() throws InterruptedException {
            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvTitle\" and @text=\"Quyết định xử phạt không dùng biên bản\"]"));
            validateHelpers.clickEditSmarter();
            validateHelpers.clearInputField(By.xpath("(//android.widget.EditText[@resource-id='com.ots.c08.vnecsgt.cbcs:id/edtContent'])[1]"));
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
            validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id='com.ots.c08.vnecsgt.cbcs:id/edtContent'])[1]"), "246");
            validateHelpers.clearInputField(By.xpath("(//android.widget.EditText[@resource-id='com.ots.c08.vnecsgt.cbcs:id/edtContent'])[2]"));
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
            validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id='com.ots.c08.vnecsgt.cbcs:id/edtContent'])[2]"), "246");
            validateHelpers.scrollDownOnce();
//            2.
            validateHelpers.clearInputField(By.id("com.ots.c08.vnecsgt.cbcs:id/edtAddress"));
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
            validateHelpers.clickElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/iconEnd"));
            validateHelpers.scrollDownOnce();
            validateHelpers.clearInputField(By.id("com.ots.c08.vnecsgt.cbcs:id/edtPersonalName"));
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
            validateHelpers.clickAndInput(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/edtPersonalName"),"Nguyễn Đặng Trường Duy");
            validateHelpers.clearInputField(By.id("com.ots.c08.vnecsgt.cbcs:id/edtPersonalId"));
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
            validateHelpers.clickAndInput(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/edtPersonalId"),"046200001234");
            validateHelpers.clearInputField(By.id("com.ots.c08.vnecsgt.cbcs:id/edtPersonalCurrentAddress"));
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
            validateHelpers.clickAndInput(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/edtPersonalCurrentAddress"),"356A Võ Văn Kiệt, Quận 4");
            validateHelpers.scrollDownOnce();
            validateHelpers.clearInputField(By.xpath("(//android.widget.EditText[@resource-id='com.ots.c08.vnecsgt.cbcs:id/edtContent'])[3]"));
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/edtAddress"));
            validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id='com.ots.c08.vnecsgt.cbcs:id/edtContent'])[3]"),"185 Trần Hưng Đạo, quận 5");
            validateHelpers.scrollDownOnce();
            validateHelpers.scrollDownOnce();
            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnClose"));
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
            validateHelpers.compareElementText("Vui lòng chọn hình ảnh/video");
            validateHelpers.clickElement(By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/itemView\"]"));
            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnLibrary\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imageView\"])[1]"));
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnClose"));


    }
    @AfterClass
    public void tearDown() {
        try {
            // Kiểm tra driver không null trước khi thực hiện các thao tác
            if (driver != null) {
                // Terminate app
                driver.terminateApp("com.ots.c08.vnecsgt.cbcs");
                Thread.sleep(1000); // đợi 1 giây để đảm bảo app đã đóng hoàn toàn

                // Quit driver
                driver.quit();
                driver = null; // set null để tránh memory leak
            }

            // Stop Appium service
            if (service != null && service.isRunning()) {
                service.stop();
                service = null; // set null để tránh memory leak
            }

            System.out.println("Cleanup completed successfully.");
        } catch (Exception e) {
            System.err.println("Error during cleanup: " + e.getMessage());
            e.printStackTrace();
        }
    }


    }