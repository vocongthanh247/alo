package VneCSGT;

import base.TestBase;
import base.ValidateHelpers;
import com.microsoft.playwright.Page;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DanhSachDen extends TestBase {
    @Test
    public void blackList() throws InterruptedException {
        validateHelpers.clickElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/ivMenu\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvTitle\" and @text=\"Danh sách đen\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.ImageButton[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnAdd\"]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtPlate\"]"),"28A67812");
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\" and @text=\"Chọn loại danh sách đen\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\" and @text=\"Chọn loại phương tiện\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickAndInput(By.xpath("//android.widget.EditText[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/edtContact\"]"),"113");
        validateHelpers.clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/dropTextView\" and @text=\"Chọn lỗi vi phạm\"]"));
        validateHelpers.clickElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/imvCheck\"])[1]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnChoose\"]"));
        validateHelpers.clickElement(By.xpath("//android.widget.Button[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/btnSave\"]"));
    }
}
