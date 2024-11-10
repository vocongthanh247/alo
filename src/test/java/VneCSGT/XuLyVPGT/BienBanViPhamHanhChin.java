package VneCSGT.XuLyVPGT;

import base.User;
import base.ValidateHelpers;
import com.microsoft.playwright.Page;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BienBanViPhamHanhChin {
    private AndroidDriver driver;
    private AppiumDriverLocalService service;
    private User.LoginHelper loginHelper;
    private ValidateHelpers validateHelpers;
    private Page page;

    @Before
    public void setUp() throws MalformedURLException {
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


        // Khởi tạo driver
        URL url = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(url, capabilities);
        loginHelper = new User.LoginHelper(driver); // Khởi tạo LoginHelper
        validateHelpers = new ValidateHelpers(driver);


    }

    @Test
    public void testFullBienBan() throws IOException {
        System.out.println("Running fullBienBan test.");
        Runtime.getRuntime().exec("adb shell monkey -p com.ots.c08.vnecsgt.cbcs -c android.intent.category.LAUNCHER 1");
        if (!loginHelper.isLoggedIn()) {
            loginHelper.performLogin();
        }

        // Thực hiện các bước kiểm tra khác ở đây
        System.out.println("Test steps for fullBienBan go here...");
    }
    @Test
    public void BienBanHanhChinh() throws IOException, InterruptedException {
        //cac buoc vao Bien ban hanh chinh
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/navigation_bar_item_icon_view\"])[4]"));
        validateHelpers.clickElement(By.xpath("//android.widget.ImageButton[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnAddViolation\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvAdd\"])[1]"));
        //1. So bien ban
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập số biên bản\"]"),"247");
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập lý do lập biên bản\"]"),"Vi pham giao thông");
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập căn cứ\"]"),"385c Nguyễn Trãi, Nguyễn Cư Trinh, Quận 1");
//        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnExpand\"])[0]"));

        //2. Thời gian và địa diểm
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
//        validateHelpers.scrollToElementMobile("Chọn địa điểm");
        validateHelpers.scrollDownOnce();
        validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnAddress"));
        validateHelpers.checkAllProvinces();
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@text=\"Nhập tên đường\"]"),"143 Võ Duy Ninh, phường 22, quận Bình Thạnh");
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@text=\"Nhập km/ số nhà\"]"),"87");
        validateHelpers.clickElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/iconEnd\"]"));
//        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnExpand\"])[0]"));
        //3.thông tin cán bộ
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id='com.ots.c08.vnecsgt.cbcs:id/edtContent']"), "Võ Công Thành");
        validateHelpers.clickElement(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập chức vụ\"]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập chức vụ\"]"),"Trưởng phòng");
        validateHelpers.clickElement(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập đơn vị công tác\"]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập đơn vị công tác\"]"),"385c Nguyễn Trãi, Nguyễn Cư Trinh, Quận 1");
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnExpand\"])[1]"));
        //4.Thông tin người chứng kiến
        validateHelpers.clickElement(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập họ và tên\"]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập họ và tên\"]"),"Võ Công Thành");
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[13]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnAddress\"]"));
        validateHelpers.checkAllProvinces();
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập nơi đăng ký thường trú/nơi ở hiện tại\"]"),"143 Võ Duy Ninh, phường 22, quận Bình Thạnh");
//        validateHelpers.clickElement(By.xpath("validateHelpers.clickElement(By.xpath(\"(//android.widget.ImageView[@resource-id=\\\"com.ots.c08.vnecsgt.cbcs:id/btnExpand\\\"])[3]\"));"));
//        validateHelpers.clickElement(By.xpath("validateHelpers.clickElement(By.xpath(\"(//android.widget.ImageView[@resource-id=\\\"com.ots.c08.vnecsgt.cbcs:id/btnExpand\\\"])[4]\"));"));
//        validateHelpers.clickElement(By.xpath("validateHelpers.clickElement(By.xpath(\"(//android.widget.ImageView[@resource-id=\\\"com.ots.c08.vnecsgt.cbcs:id/btnExpand\\\"])[3]\"));"));
//        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnExpand\"])[2]"));
//        validateHelpers.scrollToElementMobile("3. Thông tin cán bộ");
//        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnExpand\"])[2]"));

        //5.Thông tin người phiên dịch
//        validateHelpers.clickElement(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[1]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập họ và tên\"]"),"Trường Duy");
        validateHelpers.scrollDownOnce();
        validateHelpers.clickElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\"])[1]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnAddress\"]"));
        validateHelpers.checkAllProvinces();
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@text=\"Nhập nơi đăng ký thường trú/nơi ở hiện tại \"]")," 785/1, Lũy Bán Bích, P. Phú Thọ Hòa, Q. Tân Phú");
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnExpand\"])[3]"));
        //6. Thông tin bên vi phạm
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"]"),"Trường Duy");
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\" and @text=\"Chọn giới tính\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\" and @text=\"Chọn quốc tịch\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtUserName\"]"),"Công nghệ thông tin");
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập số định danh cá nhân/CCCD\"]"),"0567804213");
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtIssuedDate\"]"),"Công an quận 1");
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnAddress\"]"));
        validateHelpers.checkAllProvinces();
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập nơi ĐK thường trú/nơi ở hiện tại \"]"),"467/1 Lê Đức Thọ, P. 16 , Quận Gò Vấp , TP. HCM");
        //7.Hành vi vi phạm
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[4]"));
        validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
        validateHelpers.scrollDownOnce();
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập biển số\"]"),"26A57812");
        validateHelpers.clickElement(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[3]"));
        validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[2]"),"Điểm b khoản 2 điều 20 luật cư trú");
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập thông tin cá nhân/tổ chức thiệt hại \"]"),"Trường Duy");
        validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[3]"),"Không có");
        validateHelpers.clickElement(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[4]"));
        validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[4]"),"Không có");
        validateHelpers.clickElement(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[5]"));
        validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[5]"),"Không có");
//        validateHelpers.clickElement(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập biện pháp ngăn chặn\"]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập biện pháp ngăn chặn\"]"),"Không có");
//        validateHelpers.clickElement(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[2]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập cơ quan có thẩm quyền xử phạt\"]"),"Không có");
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập nơi dự kiến làm việc\"]"),"Trụ sở công an quận 1");
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvTime\" and @text=\"dd/mm/yyyy\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnChoose\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.CheckBox[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/checkBox\"]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập lý do bên vi phạm không ký biên bản\"]"),"Không chấp hành");
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\" and @text=\"Nhập lý do người chứng kiến không ký biên bản \"]"),"Gây phiền phức");
        validateHelpers.scrollDownOnce();
        //10. biên bản đính kèm
        validateHelpers.clickElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/ivCamera\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnLibrary\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imageView\"])[1]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnConfirm"));
        validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnClose"));
    }
        //Biên bản tạm giữ
        @Test
        public void BBTG() throws InterruptedException {
        //Thêm mới
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvAdd\"])[1]"));
        //1. số biên bản
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtReportNumber\"]"),"468");
        //2. Tuyến đường và địa điểm
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnAddress\"]"));
        validateHelpers.checkAllProvinces();
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\" and @text=\"Đường bộ-sắt\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@text=\"Nhập km/ số nhà\"]"),"90");
        validateHelpers.clickElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/iconEnd\"]"));
        //3. Thông tin người lập biên bản
        validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[1]"),"Võ Duy Ninh");
        validateHelpers.clickElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\"])[1]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[2]"),"Công an quận 1");
        //4. Thông tin người chứng kiến
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtName\"]"),"Tuấn Tú");
        validateHelpers.clickElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\"])[2]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnAddress\"]"));
        validateHelpers.checkAllProvinces();
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\" and @text=\"Đường bộ-sắt\"]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtAddress\"]"),"143 Võ Duy ninh, phươờng 22, quận Bình Thạnh");
        //5. Thông tin bên vi phạm
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtPersonalName\"]"),"Lê Văn Lâm");
        validateHelpers.clickElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\"])[2]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/main\"]/android.view.View"));
        validateHelpers.clickElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\"])[4]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[13]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtPersonalId\"]"),"0456717654");
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtIssuePlace\"]"),"Công an quận 1");
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnAddress\"]"));
        validateHelpers.checkAllProvinces();
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtPersonalCurrentAddress\"]"),"143 Võ Duy Ninh, phường 22, quận Bình Thạnh");
        //6.TT tang vật, phương tiện
        validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[1]"),"Lê Văn Lâm");
        validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[2]"),"km");
        validateHelpers.clickAndInput(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/rvSceneInfo\"]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText"),"257");
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[3]"),"Hoàn toàn bình thường");
        validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[4]"),"Không có");
        //7.Ý kiến trình bày
        validateHelpers.clickAndInput(By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtViolatorOpinion\"]/android.widget.RelativeLayout"),"Không có");
        validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[2]"),"Không có");
        validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[3]"),"Không có");
        validateHelpers.clickAndInput(By.xpath("//android.widget.CheckBox[@text=\"Người vi phạm không ký \"]"),"Không có");
        validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[4]"),"Không có");
        validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"])[5]"),"Không có");
        validateHelpers.clickAndInput(By.xpath("//android.widget.LinearLayout[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/content\"]/android.widget.EditText"),"Không có");
        //10.Đính kèm biên bản
        validateHelpers.clickElement(By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/itemView\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnLibrary\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imageView\"])[1]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
        validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnClose"));



    }
    @After
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