package VneCSGT;

import base.User;
import base.ValidateHelpers;
import com.microsoft.playwright.Page;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.AppiumBy;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class XuLyTNGT {
    private AndroidDriver driver;
    private AppiumDriverLocalService service;
    private User.LoginHelper loginHelper;
    private ValidateHelpers validateHelpers;
    private Page page;

    @BeforeTest
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
        capabilities.setCapability("settings[enableAnimations]", false);


        // Khởi tạo driver
        URL url = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(url, capabilities);
        loginHelper = new User.LoginHelper(driver); // Khởi tạo LoginHelper
        validateHelpers = new ValidateHelpers(driver);


    }

    @Test(priority = 0)
    public void test1_testFullBienBan() throws IOException {
        System.out.println("Running fullBienBan test.");
        driver.activateApp("com.ots.c08.vnecsgt.cbcs");
        if (!loginHelper.isLoggedIn()) {
            loginHelper.performLogin();
        }

        // Thực hiện các bước kiểm tra khác ở đây
        System.out.println("Test steps for fullBienBan go here...");
    }
    //Các bước để vào tới phần tạo biên bản
    @Test(priority = 1)
    public void test2_stepBystepTNGT() throws IOException,InterruptedException  {
            //Các bước để vào biên bản
            // Click vào các phần tử bằng phương thức clickToElementMobile
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/ivMenu"));
//            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvTitle\" and @text=\"Sự vụ\"]"));
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnAddEvent"));
//            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvName\" and @text=\"Tin báo tai nạn giao thông\"]"));
//            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@text='Nhập nội dung']"), "Xảy ra tai nạn");
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/iconEnd"));
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/ivCamera"));
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnLibrary"));
//            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imageView\"])[1]"));
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnSaveAndSend"));
////             Kiểm tra xem btnClose có tồn tại
//            boolean isCloseButtonVisible = validateHelpers.isElementVisible(By.id("com.ots.c08.vnecsgt.cbcs:id/btnClose"));
//            // Luôn cố gắng click vào btnConfirm
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnConfirm"));
//            // Nếu nút đóng có thể click được, click vào nó
//            if (isCloseButtonVisible) {
//                validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnClose"));
//            }
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnViewDetail"));
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnConfirm"));
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnClose"));
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnConfirmAccident"));
//            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvTitle\" and @text=\"Thông tin ban đầu\"]"));
//            validateHelpers.clickEditSmarter();
            //1.Thông tin gửi bên tin báo
//            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632512\"]"));
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnConfirm"));
//            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632513\"]"));
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
//            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632514\"]"), "Võ Công Thành");
//            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632515\"]"), "0357412345");
//            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632516\"]"));
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
//            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632517\"]"));
//            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
//            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632518\"]"));
//            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[13]"));
//            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632519\"]"), "04620004852");
//            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@content-desc=\"1730865632520\"]"));
//            validateHelpers.checkAllProvinces();
//            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
//            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
//            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632521\"]"), "143 Võ Duy Ninh, Phường 22, Quận Bình Thạnh");
//            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@content-desc=\"1730865632522\"]"));
//            validateHelpers.checkAllProvinces();
//            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
//            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
//            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632523\"]"), "143 Võ Duy Ninh, Phường 22, Quận Bình Thạnh");
//            //2. Nội dung tin báo
//            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632529\"]"), ("Xảy ra tai nạn"));
//            // //3.Thời gian và địa điểm
////            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1731865632529\"]"));
////            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
//            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632530\"]"));
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
//            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632531\"]"));
//            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
//            validateHelpers.scrollDownOnce();
//            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@content-desc=\"1730865632534\"]"));
//            validateHelpers.checkAllProvinces();
//            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
//            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
//            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632535\"]"));
//            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
//            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632536\"]"), ("376a Đường Trường Sa, phường 2, Phú Nhuận, Hồ Chí Minh"));
//            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632537\"]"), ("86km"));
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/iconEnd"));
//            validateHelpers.scrollDownSmall();
//            //4. phương tiện bị thiệt hại
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnAddDamageVehicle"));
//            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632543\"]"));
//            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
//            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632544\"]"));
//            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
//            validateHelpers.scrollDownSmall();
//            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632545\"]"), ("26A67855"));
//            //5. Phương tiện không bị thiệt hại
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnAddNormalVehicle"));
//            validateHelpers.scrollDownSmall();
//            validateHelpers.scrollDownSmall();
//            validateHelpers.clickElement(By.xpath("(//android.widget.LinearLayout[@content-desc=\"1730865632543\"])[1]"));
//            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
//            validateHelpers.clickElement(By.xpath("(//android.widget.LinearLayout[@content-desc=\"1730865632544\"])[1]"));
//            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
            validateHelpers.clickAndInput(By.xpath("(//android.widget.EditText[@content-desc=\"1730865632545\"])[1]"), "59AG89222");
            //6. Thiệt hại tài sản ước tính
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632539\"]"), ("285"));
            //7. Phân loa tai nạn
            validateHelpers.scrollDownOnce();
            validateHelpers.clickElement(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632540\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
            //8. Tóm tắt diễn biến sơ bộ
            validateHelpers.clickAndInput(By.xpath("//android.widget.RelativeLayout[@content-desc=\"1730865632541\"]"), "Gây tổn thất lớn");
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnClose"));
            validateHelpers.clickElement(By.xpath("//android.widget.ImageButton"));
    }

            //Thêm biên bản vụ việc
            @Test(priority = 2)
            public void test3_BBVV() throws InterruptedException {
//            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvAdd\"])[2]"));
//            //1.Số biên bản
//            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632549\"]"), "247");
//            //2.Thời gian và địa điểm
////            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632550\"]"));
////            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnConfirm"));
//            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632551\"]"));
//            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
//            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632552\"]"));
//            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
//            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@content-desc=\"1730865632555\"]"));
//            validateHelpers.checkAllProvinces();
//            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
//            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
//            validateHelpers.scrollDownOnce();
//            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632556\"]"));
//            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
//            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632557\"]"), ("143 Võ Duy Ninh, Phường 22, Bình Thạnh"));
//            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632558\"]"), ("120"));
//            validateHelpers.clickElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/iconEnd\"]"));
            //3.Thông tin cán bộ
            validateHelpers.clickAndInput(By.xpath("//android.widget.RelativeLayout[@content-desc=\"1730865632575\"]"), ("Phan Tấn Trung"));
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632576\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[11]"));
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632577\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[3]"));
            validateHelpers.clickAndInput(By.xpath("//android.widget.RelativeLayout[@content-desc=\"1730865632578\"]"), ("385c, Nguyễn Trãi, Nguyễn Cư Trinh, Quận 1"));
            validateHelpers.scrollDownOnce();
            //4.Thông tin người chứng kiến
            validateHelpers.clickAndInput(By.xpath("//android.widget.RelativeLayout[@content-desc=\"1730865632560\"]"), ("Đào Duy Tuấn"));
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632561\"]"));
            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632562\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632563\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632564\"]"), ("0356712356"));
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632565\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[13]"));
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632566\"]"), ("0321686955"));
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632567\"]"));
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnChoose\"]"));
            validateHelpers.clickAndInput(By.xpath("//android.widget.RelativeLayout[@content-desc=\"1730865632568\"]"), ("Công an quận 1"));
            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@content-desc=\"1730865632569\"]"));
            validateHelpers.checkAllProvinces();
            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
            validateHelpers.clickAndInput(By.xpath("//android.widget.RelativeLayout[@content-desc=\"1730865632570\"]"), ("514 lê hồng phong phường 1 quận 10"));
            //5.Thông tin bên bị lập biên bản
            validateHelpers.clickElement(By.xpath("//android.widget.CheckBox[@content-desc=\"1730865632579\"]"));
            validateHelpers.clickElement(By.xpath("//android.widget.CheckBox[@content-desc=\"1730865632580\"]"));
            validateHelpers.clickElement(By.xpath("//android.widget.CheckBox[@content-desc=\"1730865632581\"]"));
            validateHelpers.scrollDownOnce();
            validateHelpers.clickAndInput(By.xpath("//android.widget.RelativeLayout[@content-desc=\"1730865632582\"]"),"OTS");
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632583\"]"));
            validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnChoose\"]"));
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632584\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632585\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632586\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632587\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
            validateHelpers.scrollDownSmall();
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632588\"]"),"0456712222");
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632589\"]"));
            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632590\"]"),"Công an quận 1");
            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@content-desc=\"1730865632591\"]"),"0356789705");
            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@content-desc=\"1730865632592\"]"));
            validateHelpers.checkAllProvinces();
            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
            validateHelpers.clickAndInput(By.xpath("//android.widget.RelativeLayout[@content-desc=\"1730865632593\"]"),"143 Võ Duy Ninh, phường 22, quận Bình Thạnh");
            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@content-desc=\"1730865632594\"]"));
            validateHelpers.checkAllProvinces();
            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Quận Ba Đình\"]"));
            validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"Phường Phúc Xá\"]"));
            validateHelpers.scrollDownSmall();
//            validateHelpers.scrollDownSmall();
            validateHelpers.clickAndInput(By.xpath("//android.widget.RelativeLayout[@content-desc=\"1730865632595\"]"), "385c, Nguyễn Trãi, Nguyễn Cư Trinh, Quận 1");
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632596\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632597\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632598\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
            validateHelpers.scrollDownSmall();
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632599\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
//            validateHelpers.clickElement(By.id("//android.widget.LinearLayout[@content-desc=\"1730865632600\"]"));
//            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
            validateHelpers.scrollDownSmall();
//            validateHelpers.scrollDownSmall();
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632601\"]"));
            validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
//            validateHelpers.scrollDownSmall();
//            validateHelpers.scrollDownSmall();
            validateHelpers.clickAndInput(By.xpath("//android.widget.RelativeLayout[@content-desc=\"1730865632602\"]"),"Không có");
            //6.Nội dung sơ bộ
            validateHelpers.scrollDownOnce();
            validateHelpers.clickAndInput(By.xpath("//android.widget.RelativeLayout[@content-desc=\"1730865632571\"]"),"Vào lúc 09:14 06/11/2024 đã xảy ra một vụ tai nạn giao thông va quệt gây thiệt hại về người gồm phương tiện xe máy 59AG89222 do ông Trần Hoàng Tuấn điều khiển chạy quá tốc độ quy định va cham với phương tiện xe máy 99FG28812 do Trần Huy Hoàng điều khiển tại 32 đường Võ Văn Kiệt Phường 10 Quận 6.");
            //7.
            validateHelpers.clickElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"1730865632573\"]"));
            validateHelpers.clickElement(By.id("com.ots.c08.vnecsgt.cbcs:id/btnChoose"));
//            validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContent\"]"), ("12"));
            //8. Đính kèm biên bản
                validateHelpers.clickElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/ivCamera\"]"));
                validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnLibrary\"]"));
                validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imageView\"])[1]"));
            //nút Lưu
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
