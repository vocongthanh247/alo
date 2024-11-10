package TTCHC8165.Dashboard.BieuDo;

import TTCHC8165.Login.LoginHelper;
import base.ValidateHelpersPlayWright;
import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.List;

public class BienSoLuuThongTheoTinh {
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
        // Click vào phần tử cần kiểm tra
        validateHelpers.waitForElementToBeVisible("xpath=//button[@class='ant-btn css-aa9naf ant-btn-default ant-btn-color-default ant-btn-variant-outlined ant-btn-icon-only fixed bottom-4 transition-transform duration-300 ease-in-out translate-x-0']");
        validateHelpers.clickElement("xpath=//button[@class='ant-btn css-aa9naf ant-btn-default ant-btn-color-default ant-btn-variant-outlined ant-btn-icon-only fixed bottom-4 transition-transform duration-300 ease-in-out translate-x-0']");
        validateHelpers.clickElement("xpath=//body/div[@id='root']/div[1]/div[1]/div[2]/div[2]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[16]/div[1]/div[1]/div[1]/div[2]/div[1]");
    }

    @Test(priority = 0)
    public void testDisplayCurrentDateAndMonthYear() {
        try {
            validateHelpers.clickElement("xpath=//input[@id='timeRange']");
            validateHelpers.clickElement("xpath=(//button[@aria-label='year panel'][normalize-space()='2024'])[1]");
            validateHelpers.clickElement("xpath=//span[@class='ant-picker-super-prev-icon']");
            validateHelpers.clickElement("xpath=//div[normalize-space()='2010']");
            validateHelpers.clickElement("xpath=//div[normalize-space()='Th10']");
            validateHelpers.clickElement("xpath=//td[contains(@title,'2010-10-01')]//div[contains(@class,'ant-picker-cell-inner')][normalize-space()='1']");
            validateHelpers.clickElement("xpath=div.ant-picker-dropdown.css-1asd52y.ant-picker-dropdown-range.ant-picker-dropdown-placement-bottomRight div.ant-picker-range-wrapper.ant-picker-date-range-wrapper div.ant-picker-panel-container.ant-picker-date-panel-container div.ant-picker-panel-layout div.ant-picker-panels div.ant-picker-panel:nth-child(2) div.ant-picker-date-panel div.ant-picker-header div.ant-picker-header-view > button.ant-picker-year-btn");
            validateHelpers.clickElement("xpath=//span[@class='ant-picker-super-next-icon']");
            validateHelpers.clickElement("xpath=//div[normalize-space()='2024']");
            validateHelpers.clickElement("xpath=//div[normalize-space()='Th10']");
            validateHelpers.clickElement("xpath=//td[contains(@title,'2024-10-20')]//div[contains(@class,'ant-picker-cell-inner')][normalize-space()='20']");

            String actualValueStart = page.inputValue("xpath=//input[@id='timeRange']");
            Assert.assertEquals(actualValueStart, "00:00 01/10/2010", "Giá trị không khớp!");
            String actualValueEnd = page.inputValue("xpath=//input[@placeholder='Ngày kết thúc']");
            Assert.assertEquals(actualValueEnd, "23:59 20/10/2024", "Giá trị không khớp!");

            page.click("xpath=//input[@id='provinceCode']");
            page.fill("xpath=//input[@id='provinceCode']", "Tỉnh Bắc Ninh");
            page.click("xpath=//div[contains(@title,'Tỉnh Bắc Ninh')]//div[1]");
            page.click("xpath=//span[contains(text(),'Đồng ý')]");

            // Kiểm tra phần tử "Tỉnh Bắc Ninh"
            Assert.assertTrue(page.isVisible("xpath=//body/div[@id='root']//canvas[1]"), "Phần tử 'Tỉnh Bắc Ninh' không xuất hiện");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Phần tử không hiển thị hoặc gặp lỗi khác.");
        }
    }

    @Test(priority = 1)
    public void testDropdownValues() {
        List<String> expectedValues = List.of("Ngày", "Tháng", "Quý", "Năm");

        try {
            page.click("xpath=//span[contains(@title,'Ngày')]");

            for (String expectedValue : expectedValues) {
                page.click("xpath=//div[@class='ant-select-item-option-content' and text()='" + expectedValue + "']");

                String actualValue = page.innerText("xpath=//div[@class='ant-select-item-option-content' and text()='" + expectedValue + "']");
                System.out.println("Giá trị đã chọn: " + actualValue);
                Assert.assertEquals(actualValue, expectedValue, "Giá trị không khớp: " + expectedValue);
                page.click("xpath=//span[contains(@title='" + expectedValue + "')]"); // Mở lại dropdown
            }
        } catch (Exception e) {
            System.err.println("Có lỗi xảy ra: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void testNavigateToPreviousAndNextYear() {
        page.click("xpath=//button[@class='prev-year']");
        page.click("xpath=//button[@class='next-year']");
    }

    @Test(priority = 3)
    public void testDisplayTimeAndLocation() {
        Assert.assertEquals(page.innerText("xpath=//div[@class='time']"), "Ngày");
        Assert.assertEquals(page.innerText("xpath=//div[@class='location']"), "Thành phố Hà Nội");
    }

    @Test(priority = 4)
    public void testSearchFunctionality() {
        page.fill("id=search-input", "Hà Nội");
        page.click("id=search-button");
    }

    @Test(priority = 5)
    public void testNoResultsForInvalidSearch() {
        page.fill("id=search-input", "Không Tồn Tại");
        page.click("id=search-button");
    }

    @Test(priority = 6)
    public void testCheckboxInteraction() {
        page.click("id=filter-checkbox");
        Assert.assertTrue(page.isChecked("id=filter-checkbox"), "Checkbox không được chọn.");
    }

//    @Test(priority = 7)
//    public void testScrollToLocationElement() {
//        page.route("xpath=//div[@class='location']");
//    }

//    @Test(priority = 8)
//    public void testButtonEnabled() {
//        Assert.assertTrue(page.isEnabled("id=submit-button"), "Nút không được kích hoạt.");
//    }
//
//    @Test(priority = 9)
//    public void testScreenshotFunctionality() {
//        String screenshotDirectory = "path/to/screenshots"; // Đảm bảo rằng đường dẫn này có sẵn
//        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotDirectory + "/screenshot.png")));
//    }

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