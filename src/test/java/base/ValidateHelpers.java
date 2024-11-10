package base;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;

import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
//import org.openqa.selenium.devtools.v105.page.Page;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.apache.commons.io.FileUtils;

import javax.swing.text.Element;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.time.Duration;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static base.VPATestBase.SCREENSHOT_PATH;

public class ValidateHelpers {

    private AndroidDriver driver; // Chỉ sử dụng AndroidDriver
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public ValidateHelpers(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) this.driver;
    }

    public void waitForElement(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void setText(By element, String text) {
        waitForElement(element);
        WebElement webElement = driver.findElement(element);
        webElement.clear();
        webElement.sendKeys(text);
    }

    public String getCssValue(By element, String propertyName) {
        waitForElement(element);
        return driver.findElement(element).getCssValue(propertyName);
    }
    public boolean verifyElementDisabled(By by) {
        try {
            WebElement element = driver.findElement(by);
            boolean isDisabled = !element.isEnabled();

            if (isDisabled) {
                System.out.println("PASSED: Element is disabled as expected");
                return true;
            } else {
                System.err.println("FAILED: Element should be disabled but is enabled");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error checking disabled state: " + e.getMessage());
            return false;
        }
    }
    public void clickAndCheckButton(By locator) {
        try {
            // Click vào nút
            clickElement1(locator);
            Thread.sleep(1000); // Thêm thời gian chờ để đảm bảo nút đã được click

            // Lấy thuộc tính 'text' của nút
            String buttonText = driver.findElement(locator).getAttribute("text");
            String isEnabled = driver.findElement(locator).getAttribute("enabled");

            if (buttonText != null && !buttonText.isEmpty()) {
                System.out.println("Nút có văn bản: " + buttonText);
                System.out.println("Nút có khả năng kích hoạt: " + isEnabled);

                if (isEnabled.equals("true")) {
                    // Nếu nút có thể click, thực hiện click
                    driver.findElement(locator).click();
                    System.out.println("Đã click vào nút: " + buttonText);
                } else {
                    System.out.println("Nút không khả dụng để click.");
                }
            } else {
                System.out.println("Nút không có văn bản.");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi kiểm tra và click vào nút: " + e.getMessage());
        }
    }



//    public void clickAtCoordinates(int x, int y) {
//
//        // Lệnh ADB để nhấp vào tọa độ
//        String command = String.format("adb shell input tap %d %d", x, y);
//
//        try {
//            // Thực thi lệnh ADB
//            Process process = Runtime.getRuntime().exec(command);
//            process.waitFor(); // Chờ lệnh hoàn thành
//            System.out.println("Clicked at (" + x + ", " + y + ")");
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
public void scrollDownOnce() {
    ((AndroidDriver) driver).findElement(AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(1).scrollForward()"
    ));
}
    public void inputTextSafely(String resourceId, String text) {
        driver.findElement(AppiumBy.androidUIAutomator(
                String.format(
                        "new UiSelector().resourceId(\"%s\").setText(\"%s\")",
                        resourceId,
                        text
                )
        ));
    }
    public void softReset() {
        driver.resetApp();
    }


    public void scrollDownSmall() {
        // Lấy kích thước màn hình
        Dimension size = driver.manage().window().getSize();

        // Tính toán điểm bắt đầu và kết thúc
        Point start = new Point(size.width / 2, (int) (size.height * 0.6));
        Point end = new Point(size.width / 2, (int) (size.height * 0.4));

        // Tạo chuỗi action W3C
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), end.x, end.y))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }







    private WebElement findElement(By locator) {
        int maxAttempts = 3;
        int attempt = 0;
        while (attempt < maxAttempts) {
            try {
                return driver.findElement(locator);
            } catch (Exception e) {
                attempt++;
                if (attempt == maxAttempts) {
                    throw new RuntimeException("Could not find element after " + maxAttempts + " attempts");
                }
                sleep(1000);
            }
        }
        return null;
    }

    public void clickAndInput(By locator, String inputText) {
        try {
            // Tìm element
            WebElement element = findElement(locator);

            // Click để focus
            clickWithRetry(element);

            // Clear và input
            clearAndInputText(element, inputText);

            // Đóng keyboard
            closeKeyboard();

        } catch (Exception e) {
            System.err.println("Failed to input text: " + e.getMessage());
            closeKeyboard();
        }
    }

    // Helper method để log trạng thái của element
    private void logElementState(WebElement element) {
        try {
            System.out.println("Element state:");
            System.out.println("- Displayed: " + element.isDisplayed());
            System.out.println("- Enabled: " + element.isEnabled());
            System.out.println("- Selected: " + element.isSelected());
            System.out.println("- Location: " + element.getLocation());
            System.out.println("- Size: " + element.getSize());
            System.out.println("- Class: " + element.getAttribute("class"));
            System.out.println("- Content-desc: " + element.getAttribute("content-desc"));
        } catch (Exception e) {
            System.err.println("Could not log element state: " + e.getMessage());
        }
    }
    public void clickAndInputNghiDinh(String inputText) {
        try {
            // Tìm EditText có text "NĐ-CP"
            String xpath = "//android.widget.EditText[contains(@text, 'NĐ-CP')]";
            WebElement element = driver.findElement(By.xpath(xpath));

            // Click để focus
            element.click();
            sleep(1000);

            // Clear text hiện tại
            for(int i = 0; i < 20; i++) {
                driver.pressKey(new KeyEvent(AndroidKey.DEL));
            }
            sleep(500);

            // Thử các cách input khác nhau
            try {
                // Cách 1: Input trực tiếp
                element.sendKeys(inputText);
            } catch (Exception e1) {
                try {
                    // Cách 2: Input từng ký tự
                    for(char c : inputText.toCharArray()) {
                        element.sendKeys(String.valueOf(c));
                        sleep(100);
                    }
                } catch (Exception e2) {
                    // Cách 3: Dùng KeyEvents cho số
                    for(char c : inputText.toCharArray()) {
                        switch(c) {
                            case '1': driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1)); break;
                            case '2': driver.pressKey(new KeyEvent(AndroidKey.DIGIT_2)); break;
                            case '4': driver.pressKey(new KeyEvent(AndroidKey.DIGIT_4)); break;
                            case '0': driver.pressKey(new KeyEvent(AndroidKey.DIGIT_0)); break;
                            case '/': driver.pressKey(new KeyEvent(AndroidKey.SLASH)); break;
                            default: element.sendKeys(String.valueOf(c));
                        }
                        sleep(100);
                    }
                }
            }

            closeKeyboard();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }



    private boolean tryInputText(WebElement element, String inputText) {
        try {
            // Method 1: Direct sendKeys
            element.sendKeys(inputText);
            return true;
        } catch (Exception e1) {
            try {
                // Method 2: Set value using JavaScript
                driver.executeScript("arguments[0].value=arguments[1]", element, inputText);
                return true;
            } catch (Exception e2) {
                try {
                    // Method 3: Character by character
                    for (char c : inputText.toCharArray()) {
                        element.sendKeys(String.valueOf(c));
                        sleep(100);
                    }
                    return true;
                } catch (Exception e3) {
                    return false;
                }
            }
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void clickElement1(By locator) {
        if (driver == null) {
            throw new IllegalStateException("AndroidDriver is not initialized.");
        }

        try {
            // Đợi phần tử hiển thị
            waitForElementToBeVisible(locator);

            WebElement webElement = driver.findElement(locator);

            // Kiểm tra xem phần tử có hiển thị và có thể click được không
            if (webElement.isDisplayed() && webElement.isEnabled()) {
                webElement.click();
            } else {
                System.out.println("Phần tử không hiển thị hoặc không thể click: " + locator);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Không tìm thấy phần tử: " + locator);
        } catch (ElementClickInterceptedException e) {
            System.out.println("Có phần tử khác che khuất phần tử: " + locator);
        } catch (StaleElementReferenceException e) {
            System.out.println("Phần tử không còn tham chiếu: " + locator);
            // Tìm lại phần tử và click
            clickElement1(locator); // Gọi lại nếu cần thiết
        } catch (Exception e) {
            System.out.println("Lỗi khi click vào phần tử: " + e.getMessage());
        }
    }
    public void clickEditSmarter() {
        System.out.println("Bắt đầu tìm nút Edit...");

        // Thử click trực tiếp trước
        try {
            driver.findElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnEdit")).click();
            System.out.println("Click nhanh thành công!");
            return;
        } catch (Exception e) {
            System.out.println("Click nhanh thất bại, chuyển sang chế độ đợi...");
        }

        // Nếu không được thì đợi
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnEdit")
        )).click();

        System.out.println("Đã click sau khi đợi!");
    }
    public void quickClickEdit() {
        try {
            // Tìm và click trực tiếp bằng UiAutomator2
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().resourceId(\"com.ots.c08.vnecsgt.cbcs:id/btnEdit\")" +
                            ".enabled(true).clickable(true)"
            )).click();
        } catch (Exception e) {
            System.out.println("Không thể click nhanh: " + e.getMessage());
        }
    }

    public void openDropdownAndClick(By dropdownLocator, By optionLocator) {
        try {
            // Mở dropdown
            WebElement dropdown = driver.findElement(dropdownLocator);
            dropdown.click(); // Click để mở dropdown

            // Chờ một chút để dropdown mở ra
            Thread.sleep(500);

            // Click vào tùy chọn trong dropdown
            WebElement option = driver.findElement(optionLocator);
            option.click();
        } catch (NoSuchElementException e) {
            System.out.println("Không tìm thấy phần tử: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Lỗi khi chờ: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi khi click: " + e.getMessage());
        }
    }


    public void checkCity(String cityName) {
        By locator = By.xpath("//android.widget.TextView[@resource-id=\"com.ots.c08.vnecsgt.cbcs:id/tvValue\" and @text=\"" + cityName + "\"]");

        try {
            waitForElementToBeVisible(locator, 10); // Chờ tối đa 5 giây
            WebElement element = driver.findElement(locator);

            if (element.isDisplayed()) {
                element.click(); // Click vào phần tử
                System.out.println("Đã click vào: " + cityName);
            } else {
                System.out.println("Phần tử không hiển thị: " + cityName);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Không tìm thấy phần tử: " + cityName);
        } catch (TimeoutException e) {
            System.out.println("Thời gian chờ đã hết cho phần tử: " + cityName);
        } catch (Exception e) {
            System.out.println("Lỗi khi kiểm tra phần tử " + cityName + ": " + e.getMessage());
        }
    }
    public void clickEditButton(By buttonLocator, String buttonText) {
        try {
            // Chờ cho danh sách các nút hiển thị
            waitForElementToBeVisible(buttonLocator, 10);
            List<WebElement> buttons = driver.findElements(buttonLocator);

            for (WebElement button : buttons) {
                // Kiểm tra văn bản của nút
                if (button.getText().equals(buttonText) && button.isDisplayed() && button.isEnabled()) {
                    button.click();
                    System.out.println("Đã click vào nút: " + buttonText);
                    return;
                }
            }

            System.out.println("Không tìm thấy nút: " + buttonText);
        } catch (NoSuchElementException e) {
            System.out.println("Không tìm thấy danh sách các nút.");
        } catch (TimeoutException e) {
            System.out.println("Thời gian chờ đã hết cho danh sách các nút.");
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm kiếm nút: " + e.getMessage());
        }
    }
    public void waitAndClickEdit() {
        // Đầu tiên đợi màn hình ổn định
        try {
            Thread.sleep(2000); // Đợi 2s cho UI ổn định
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Sau đó mới scroll và tìm button
        ((AndroidDriver) driver).findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"
        ));

        // Log để debug
        System.out.println("Bắt đầu tìm button Edit...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
        WebElement editButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnEdit")
        ));

        System.out.println("Đã tìm thấy button Edit!");
        editButton.click();
    }
    public void findElementById(String elementId){
        WebElement element = driver.findElement(By.id(elementId));
        element.click();
    }

    // Sử dụng hàm

    public void  waitForElementWithPolling(By locator, int timeout, int pollingInterval) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(pollingInterval));

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            if (element.isEnabled()) {
                element.click();
                System.out.println("Đã click vào phần tử.");
            } else {
                throw new IllegalStateException("Phần tử không thể tương tác.");
            }
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Không tìm thấy phần tử.", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Quá thời gian chờ mà không tìm thấy phần tử.", e);
        }
    }
    public void scrollAndFindEdit() {
        int maxScrolls = 3;
        int scrollCount = 0;

        while (scrollCount < maxScrolls) {
            try {
                // Scroll
                ((AndroidDriver) driver).findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"
                ));

                // Tìm button
                WebElement editButton = driver.findElement(AppiumBy.id("com.ots.c08.vnecsgt.cbcs:id/btnEdit"));
                if (editButton.isDisplayed()) {
                    editButton.click();
                    return;
                }
            } catch (Exception e) {
                scrollCount++;
                if (scrollCount == maxScrolls) {
                    throw new RuntimeException("Không tìm thấy nút Edit sau " + maxScrolls + " lần scroll");
                }
            }
        }
    }
    public void clickAndInputById(String elementId, String inputText) {
        try {
            // Tìm phần tử bằng resource ID
            WebElement element = driver.findElement(By.id(elementId));

            // Nhấp vào phần tử
            element.click();

            // Nhập văn bản
            element.clear(); // Xóa nội dung trước khi nhập, nếu cần thiết
            element.sendKeys(inputText);

            // Kiểm tra xem văn bản đã được nhập hay chưa
            String currentText = element.getAttribute("text"); // Dùng "text" cho EditText

            // Nếu văn bản không được nhập, thử lại
            if (currentText == null || currentText.isEmpty() || currentText.equals("Nhập nội dung")) {
                System.out.println("Văn bản không được nhập, thử lại...");
                element.clear(); // Xóa nội dung nếu cần thiết
                element.sendKeys(inputText);
                currentText = element.getAttribute("text");
            }

            // Kiểm tra lại lần nữa
            if (currentText != null && !currentText.isEmpty() && !currentText.equals("Nhập nội dung")) {
                System.out.println("Văn bản đã được nhập: " + currentText);
            } else {
                throw new RuntimeException("Không thể nhập văn bản.");
            }

            // Đóng bàn phím ảo
            closeKeyboard();

        } catch (Exception e) {
            System.err.println("Lỗi khi click và nhập: " + e.getMessage());
            // Đảm bảo đóng bàn phím trong trường hợp có lỗi
            closeKeyboard();
        }
    }
    public void checkEditButton(By buttonLocator, String buttonText) {
        try {
            waitForElementToBeVisible(buttonLocator, 5); // Chờ tối đa 5 giây
            WebElement button = driver.findElement(buttonLocator);

            if (button.isDisplayed() && button.isEnabled()) {
                button.click(); // Click vào nút
                System.out.println("Đã click vào nút: " + buttonText);
            } else {
                System.out.println("Nút không hiển thị hoặc không khả dụng: " + buttonText);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Không tìm thấy nút: " + buttonText);
        } catch (TimeoutException e) {
            System.out.println("Thời gian chờ đã hết cho nút: " + buttonText);
        } catch (Exception e) {
            System.out.println("Lỗi khi kiểm tra nút " + buttonText + ": " + e.getMessage());
        }
    }

    // Sử dụng hàm checkEditButton
    public void checkAllEditButtons() {
        By buttonLocator = By.id("com.ots.c08.vnecsgt.cbcs:id/btnEdit"); // Locator cho nút "Chỉnh sửa"
        String buttonText = "Chỉnh sửa"; // Văn bản của nút "Chỉnh sửa"
        checkEditButton(buttonLocator, buttonText);
    }





    // Sử dụng hàm checkCity
    public void checkAllProvinces() {
        String[] provinces = {
                "Thành phố Hà Nội", // ... (thêm các tỉnh thành khác)
        };

        for (String province : provinces) {
            checkCity(province);
        }
    }





//    public void scrollToDropdown(String dropdownText) {
//        try {
//            // Sử dụng AndroidUIAutomator để cuộn đến phần tử với text tương ứng
//            String uiAutomatorCommand = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + dropdownText + "\"))";
//            driver.findElement(MobileBy.AndroidUIAutomator(uiAutomatorCommand));
//        } catch (NoSuchElementException e) {
//            System.out.println("Không tìm thấy dropdown với text: " + dropdownText);
//        } catch (Exception e) {
//            System.err.println("Lỗi khi cuộn tới dropdown: " + e.getMessage());
//        }
//    }




    public void clickElement(By element) {
        if (driver == null) {
            throw new IllegalStateException("AndroidDriver is not initialized.");
        }

        int maxRetries = 5;
        int retryCount = 0;
        int waitBetweenRetries = 1000; // 1 giây

        while (retryCount < maxRetries) {
            try {
                // Đợi phần tử hiển thị
                waitForElementToBeVisible(element);

                // Thêm độ trễ nhỏ để đảm bảo element ổn định
                Thread.sleep(500);

                WebElement webElement = driver.findElement(element);

                // Kiểm tra xem phần tử có hiển thị và có thể click được không
                if (webElement.isDisplayed() && webElement.isEnabled()) {
                    // Thêm độ trễ nhỏ trước khi click
                    Thread.sleep(500);
                    webElement.click();
                    return; // Thoát khỏi vòng lặp nếu click thành công
                } else {
                    System.out.println("Lần thử " + (retryCount + 1) + ": Phần tử không hiển thị hoặc không thể click: " + element);
                }

            } catch (NoSuchElementException e) {
                System.out.println("Lần thử " + (retryCount + 1) + ": Không tìm thấy phần tử: " + element);
            } catch (ElementClickInterceptedException e) {
                System.out.println("Lần thử " + (retryCount + 1) + ": Có phần tử khác che khuất phần tử: " + element);
            } catch (StaleElementReferenceException e) {
                System.out.println("Lần thử " + (retryCount + 1) + ": Phần tử không còn tham chiếu: " + element);
            } catch (Exception e) {
                System.out.println("Lần thử " + (retryCount + 1) + ": Lỗi khi click vào phần tử: " + e.getMessage());
            }

            retryCount++;
            if (retryCount < maxRetries) {
                try {
                    // Đợi một khoảng thời gian trước khi thử lại
                    Thread.sleep(waitBetweenRetries);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }

        // Nếu đã thử hết số lần mà vẫn không thành công
        System.out.println("Không thể click vào phần tử sau " + maxRetries + " lần thử: " + element);
    }
    

    private void clearAndInputTextNormal(WebElement element, String text) throws InterruptedException {
        element.clear();
        Thread.sleep(500);
        element.sendKeys(text);
        Thread.sleep(500);
    }

    private void clearAndInputTextWithActions(WebElement element, String text) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.click(element)
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(text)
                .perform();
        Thread.sleep(500);
    }

    private void clearAndInputTextWithADB(WebElement element, String text) throws InterruptedException, IOException {
        // Lấy vị trí của element
        Point location = element.getLocation();
        Dimension size = element.getSize();
        int centerX = location.getX() + size.getWidth() / 2;
        int centerY = location.getY() + size.getHeight() / 2;

        // Click vào vị trí của element
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(PointOption.point(centerX, centerY)).perform();
        Thread.sleep(500);

        // Sử dụng ADB để nhập text
        String adbCommand = String.format("adb shell input text \"%s\"", text.replace(" ", "%s"));
        Runtime.getRuntime().exec(adbCommand);
        Thread.sleep(1000);
    }

    private boolean verifyTextEntered(WebElement element, String expectedText) {
        try {
            // Đợi text được cập nhật
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(driver -> {
                String actualText = element.getText();
                if (actualText.isEmpty()) {
                    actualText = element.getAttribute("value");
                }
                return actualText.equals(expectedText);
            });
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private void clickWithRetry(WebElement element) throws InterruptedException {
        int maxRetries = 3;
        int retryCount = 0;
        boolean clicked = false;

        while (!clicked && retryCount < maxRetries) {
            try {
                // Thử click thông thường
                element.click();
                clicked = true;
            } catch (Exception e1) {
                try {
                    // Thử dùng JavaScript
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    clicked = true;
                } catch (Exception e2) {
                    try {
                        // Thử dùng TouchAction
                        Point location = element.getLocation();
                        Dimension size = element.getSize();
                        int centerX = location.getX() + size.getWidth() / 2;
                        int centerY = location.getY() + size.getHeight() / 2;

                        TouchAction touchAction = new TouchAction(driver);
                        touchAction.tap(PointOption.point(centerX, centerY)).perform();
                        clicked = true;
                    } catch (Exception e3) {
                        retryCount++;
                        if (retryCount == maxRetries) {
                            throw new RuntimeException("Failed to click element after " + maxRetries + " attempts");
                        }
                        Thread.sleep(1000);
                    }
                }
            }
        }
    }



    private void clearAndInputText(WebElement element, String inputText) {
        try {
            String currentText = element.getAttribute("text");
            if (currentText != null && !currentText.isEmpty()) {
                element.clear();
            }
            element.sendKeys(inputText);
        } catch (Exception e) {
            throw new RuntimeException("Failed to clear and input text", e);
        }
    }
    public void clickAndInputVPA(By locator, String inputText) {
        try {
            // Tìm element
            WebElement element = findElement(locator);

            // Click để focus
            clickWithRetry(element);

    // Clear và input
    clearAndInputTextVPA(element, inputText);

    // Đóng keyboard
    closeKeyboard();

} catch (NoSuchElementException e) {
String screenshotPath = captureScreenshotVPA("element_not_found_input");
        System.err.println("Không tìm thấy element để nhập text: " + locator);
        System.err.println("Screenshot saved at: " + screenshotPath);
closeKeyboard();
    } catch (ElementNotInteractableException e) {
String screenshotPath = captureScreenshotVPA("element_not_interactable_input");
        System.err.println("Element không thể tương tác để nhập text: " + locator);
        System.err.println("Screenshot saved at: " + screenshotPath);
closeKeyboard();
    } catch (StaleElementReferenceException e) {
String screenshotPath = captureScreenshotVPA("stale_element_input");
        System.err.println("Element không còn tồn tại trong DOM: " + locator);
        System.err.println("Screenshot saved at: " + screenshotPath);
closeKeyboard();
    } catch (Exception e) {
String screenshotPath = captureScreenshotVPA("input_error");
        System.err.println("Lỗi khi nhập text: " + e.getMessage());
        System.err.println("Locator: " + locator);
        System.err.println("Input text: " + inputText);
        System.err.println("Screenshot saved at: " + screenshotPath);
closeKeyboard();
    }
            }
    private void clearAndInputTextVPA(WebElement element, String inputText) {
        try {
            String currentText = element.getAttribute("text");
            if (currentText != null && !currentText.isEmpty()) {
                element.clear();
            }
            element.sendKeys(inputText);
        } catch (Exception e) {
            throw new RuntimeException("Failed to clear and input text", e);
        }
    }


    public void compareElementText(String expectedText) {
        try {
            WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text='" + expectedText + "']"));
            String actualText = element.getText();

            if (actualText.equals(expectedText)) {
                System.out.println("Văn bản khớp với giá trị");
            } else {
                System.out.println("Văn bản không khớp. Expected: '" + expectedText + "', Actual: '" + actualText + "'");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Không tìm thấy element với text: " + expectedText);
        }
    }
    public void verifyTextNotPresent(String text) {
        try {
            // Đợi một chút để message biến mất
            Thread.sleep(1000);

            // Tìm element với text
            List<WebElement> elements = driver.findElements(By.xpath("//android.widget.TextView[@text='" + text + "']"));

            // Kiểm tra element không còn
            if (elements.isEmpty()) {
                System.out.println("Pass - Text validate đã biến mất: " + text);
            } else {
                System.out.println("Fail - Text validate vẫn còn hiển thị: " + text);
                Assert.fail("Text validate vẫn còn hiển thị: " + text);
            }
        } catch (Exception e) {
            System.out.println("Pass - Text validate đã biến mất: " + text);
        }
    }


    public boolean isElementDisplayed(By element) {
        try {
            waitForElement(element);  // Sử dụng waitForElement có sẵn
            WebElement webElement = driver.findElement(element);

            // Kiểm tra và log trạng thái
            boolean isDisplayed = webElement.isDisplayed();
            System.out.println("Element " + element + " displayed: " + isDisplayed);

            return isDisplayed;

        } catch (Exception e) {
            System.out.println("Element không hiển thị hoặc không tồn tại: " + element);
            return false;
        }
    }

    public void assertElementText(By element, String expectedText) {
        try {
            // Thêm explicit wait cho element
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));

            // Thêm thời gian chờ ngắn để đảm bảo element ổn định
            Thread.sleep(500);

            // Kiểm tra lại visibility sau khi đã wait
            if (!isElementDisplayed(element)) {
                String screenshot = captureFailureScreenshot("element_not_displayed");
                Assert.fail("Element vẫn không hiển thị sau khi chờ: " + element + "\nScreenshot: " + screenshot);
                return;
            }

            // Lấy text và normalize
            String actualText = driver.findElement(element).getText()
                    .trim()
                    .replaceAll("\\s+", " ")
                    .replaceAll("[\\p{M}]", "");

            String normalizedExpected = expectedText
                    .trim()
                    .replaceAll("\\s+", " ")
                    .replaceAll("[\\p{M}]", "");

            // Chuyển đổi các ký tự có dấu thành không dấu để so sánh
            actualText = removeDiacritics(actualText);
            normalizedExpected = removeDiacritics(normalizedExpected);

            System.out.println("Normalized Expected text: '" + normalizedExpected + "' (length: " + normalizedExpected.length() + ")");
            System.out.println("Normalized Actual text: '" + actualText + "' (length: " + actualText.length() + ")");

            // So sánh sau khi đã normalize
            boolean isEqual = actualText.equalsIgnoreCase(normalizedExpected);

            if (!isEqual) {
                String screenshot = captureFailureScreenshot("text_mismatch");
                Assert.fail(String.format("Văn bản không khớp sau khi chuẩn hóa.\nMong đợi: '%s'\nThực tế: '%s'\nScreenshot: %s",
                        normalizedExpected, actualText, screenshot));
            }

            System.out.println("Text validation passed for element: " + element);

        } catch (TimeoutException e) {
            String screenshot = captureFailureScreenshot("element_timeout");
            Assert.fail("Timeout chờ element hiển thị: " + element + "\nScreenshot: " + screenshot);
        } catch (Exception e) {
            String screenshot = captureFailureScreenshot("text_assertion_error");
            Assert.fail("Lỗi khi kiểm tra text của element: " + e.getMessage() +
                    "\nScreenshot: " + screenshot);
        }
    }

    // Helper method để kiểm tra element có hiển thị không


    // Helper method để loại bỏ dấu
    private String removeDiacritics(String text) {
        String nfdNormalizedString = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
    public boolean verifyElementNotPresent(By by) {
        try {
            List<WebElement> elements = driver.findElements(by);
            boolean isNotPresent = elements.isEmpty();

            if (isNotPresent) {
                System.out.println("PASSED: Element is not present as expected");
                return true;
            } else {
                System.err.println("FAILED: Element should not be present but still exists");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error checking element presence: " + e.getMessage());
            return false;
        }
    }

    // Helper method để loại bỏ dấu
    private String captureFailureScreenshot(String name) {
        if (driver == null) {
            return null;
        }

        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = SCREENSHOT_PATH + "FAIL_" + name + "_" + timestamp + ".png";
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(fileName);
            FileUtils.copyFile(screenshot, destFile);
            System.out.println("Failure screenshot saved at: " + destFile.getAbsolutePath());
            return destFile.getAbsolutePath();
        } catch (Exception e) {
            System.err.println("Failed to capture failure screenshot: " + e.getMessage());
            return null;
        }
    }



    public void selectOptionByText(By element, String text) {
        waitForElement(element);
        Select select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);
    }

    public boolean isCheckboxChecked(By checkboxElement) {
        waitForElement(checkboxElement);
        return driver.findElement(checkboxElement).isSelected();
    }

//    public boolean scrollToElement(By element) {
//        waitForElement(element);
//        WebElement webElement = driver.findElement(element);
//        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
//        return false;
//    }

    public void waitForPageLoaded(By id) {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void closeKeyboard() {
        try {
            Runtime.getRuntime().exec("adb shell input keyevent 4"); // Gửi sự kiện KEYCODE_APP_SWITCH để đóng bàn phím
            System.out.println("Keyboard closed successfully using ADB.");
        } catch (IOException e) {
            System.err.println("Failed to close the keyboard using ADB: " + e.getMessage());
        }
    }
//    public void clickOnText(String text) {
//        try {
//            // Tìm phần tử theo text và nhấp vào nó
//            driver.findElement(MobileBy.AndroidUIAutomator(
//                    "new UiSelector().text(\"" + text + "\")")).click();
//        } catch (NoSuchElementException e) {
//            System.out.println("Không tìm thấy phần tử với text: " + text);
//        }
//    }
private String captureScreenshotVneCSGT(String screenshotName) {
    try {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";

        // Tạo thư mục VneCSGT nếu chưa có
        File directory = new File("screenshots/VneCSGT");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String destination = "screenshots/VneCSGT/" + fileName;
        FileUtils.copyFile(source, new File(destination));

        return destination;
    } catch (Exception e) {
        System.err.println("Lỗi khi chụp screenshot: " + e.getMessage());
        return null;
    }
}


    public boolean pressKey_Android(AndroidKey key) {
        try {
            driver.pressKey(new KeyEvent(key));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public void clearTextWithKeyboard(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            element.click();
            Thread.sleep(200); // Đợi element được focus

            // Select All
            driver.pressKey(new KeyEvent(AndroidKey.META_LEFT));
            driver.pressKey(new KeyEvent(AndroidKey.A));
            Thread.sleep(100); // Đợi text được select

            // Delete
            driver.pressKey(new KeyEvent(AndroidKey.DEL));

        } catch (Exception e) {
            System.out.println("Không thể xóa text: " + e.getMessage());
        }
    }
    public String captureScreenshot(String screenshotName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = screenshotName + "_" + timestamp + ".png";

            File directory = new File("screenshots");
            if (!directory.exists()) {
                directory.mkdir();
            }

            File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String destination = "screenshots/" + fileName;
            FileUtils.copyFile(source, new File(destination));

            return destination;
        } catch (Exception e) {
            System.err.println("Exception while taking screenshot: " + e.getMessage());
            return null;
        }
    }

    // Phương thức xóa văn bản trong ô nhập liệu
    public void clearInputField(By element) {
        try {
            WebElement inputElement = driver.findElement(element);

            // Click để focus vào element
            inputElement.click();
            Thread.sleep(500);

            String currentText = inputElement.getAttribute("text");
            if (currentText != null && !currentText.isEmpty()) {
                inputElement.clear();

                String afterClearText = inputElement.getAttribute("text");
                if (afterClearText != null && !afterClearText.isEmpty()) {
                    String screenshotPath = captureScreenshotVneCSGT("clear_failed");
                    System.err.println("Clear text không thành công: " + element);
                    System.err.println("Screenshot saved at: " + screenshotPath);
                }
            }
        } catch (Exception e) {
            String screenshotPath = captureScreenshotVneCSGT("clear_error");
            System.err.println("Lỗi khi clear text: " + e.getMessage());
            System.err.println("Screenshot saved at: " + screenshotPath);
        }
    }
    public void clickElementVPA(By element) {
        if (driver == null) {
            throw new IllegalStateException("AndroidDriver is not initialized.");
        }

        try {
            WebElement webElement = driver.findElement(element);
            webElement.click();
        } catch (Exception e) {
            String screenshotPath = captureScreenshotVPA("click_error");
            System.err.println("Lỗi khi click element: " + e.getMessage());
            System.err.println("Screenshot saved at: " + screenshotPath);
        }
    }

    public void clearInputFieldVPA(By element) {
        try {
            WebElement inputElement = driver.findElement(element);

            // Click để focus vào element
            inputElement.click();
            Thread.sleep(500);

            String currentText = inputElement.getAttribute("text");
            if (currentText != null && !currentText.isEmpty()) {
                inputElement.clear();

                String afterClearText = inputElement.getAttribute("text");
                if (afterClearText != null && !afterClearText.isEmpty()) {
                    String screenshotPath = captureScreenshotVPA("clear_failed");
                    System.err.println("Clear text không thành công: " + element);
                    System.err.println("Screenshot saved at: " + screenshotPath);
                }
            }
        } catch (Exception e) {
            String screenshotPath = captureScreenshotVPA("clear_error");
            System.err.println("Lỗi khi clear text: " + e.getMessage());
            System.err.println("Screenshot saved at: " + screenshotPath);
        }
    }

    private String captureScreenshotVPA(String screenshotName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = screenshotName + "_" + timestamp + ".png";

            // Tạo thư mục VPA nếu chưa có
            File directory = new File("screenshots/VPA");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String destination = "screenshots/VPA/" + fileName;
            FileUtils.copyFile(source, new File(destination));

            return destination;
        } catch (Exception e) {
            System.err.println("Lỗi khi chụp screenshot: " + e.getMessage());
            return null;
        }
    }




        // Scroll bằng content-desc từ XPath
        public WebElement scrollToElementByXPathContentDesc(String description) {
            try {
                return driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().description(\"" + description + "\"))"));
            } catch (Exception e) {
                throw new NoSuchElementException("Could not find element with description: " + description);
            }
        }






    public void scrollToElementMobile(String text) {
        try {
            // Cuộn đến phần tử với text
            driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(10).scrollIntoView(new UiSelector().text(\"" + text + "\"))"));
        } catch (NoSuchElementException e) {
            System.out.println("Không thể cuộn đến phần tử với text: " + text);
        }
    }

    public void clickAtCoordinates(int x, int y) {
        // Lệnh ADB để nhấp vào tọa độ
        String command = String.format("adb shell input tap %d %d", x, y);

        try {
            // Thực thi lệnh ADB
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor(); // Chờ lệnh hoàn thành
            System.out.println("Clicked at (" + x + ", " + y + ")");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickElementByText(String text) {
        if (driver == null) {
            System.err.println("Driver is not initialized.");
            return;
        }

        try {
            driver.findElement(By.xpath("//*[@text='" + text + "']")).click();
            System.out.println("Clicked on element with text: " + text);
        } catch (Exception e) {
            System.err.println("Failed to click on element with text: " + text);
            e.printStackTrace();
        }
    }
    public void clickChildElement(String parentXpath, String childId) {
        if (driver == null) {
            System.err.println("Driver is not initialized.");
            return;
        }

        try {
            // Tìm phần tử cha theo XPath
            WebElement parentElement = driver.findElement(By.xpath(parentXpath));

            // Tìm phần tử con theo ID trong phạm vi của phần tử cha
            WebElement childElement = parentElement.findElement(By.id(childId));

            // Click vào phần tử con
            childElement.click();
            System.out.println("Clicked on child element with ID: " + childId);
        } catch (NoSuchElementException e) {
            System.err.println("Element not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Failed to click on child element.");
            e.printStackTrace();
        }
    }

    public void swipeAndClick(int x, int y) {
        // Lệnh ADB để vuốt (nhấp) vào tọa độ
        String command = String.format("adb shell input swipe %d %d %d %d", x, y, x, y);

        try {
            // Thực thi lệnh ADB
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor(); // Chờ lệnh hoàn thành
            System.out.println("Swiped (clicked) at (" + x + ", " + y + ")");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }





    public void clickAndInputText(By locator, String text,By element) {
        waitForElementToBeVisible(locator);

        clickElement(element);
//        inputText(text);
    }
    public void clickToElementMobile(By locator,By element) {
        waitForElementToBeVisible(locator);

        clickElement(element);
    }
//    public void scrollToInput(By locator, String text) {
//        waitForElementToBeVisible(locator);
//        clickElement(locator); // Nhấp vào trường để đảm bảo nó được chọn
//        inputText(text);
//    }

//    private void inputText(String text) {
//
//        // Chờ cho phần tử hiển thị và nhấp vào trường nhập liệu
//
//        // Đợi một chút để đảm bảo trường đã sẵn sàng nhận dữ liệu
//        try {
//            Thread.sleep(1000); // Thời gian có thể điều chỉnh
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            Thread.sleep(500); // Chờ nửa giây
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Thay thế khoảng trắng
//        String formattedText = text.replace(" ", "%20").replace("'", "\\'");
//
//        // Sử dụng ADB để nhập văn bản
//        String command = "adb shell input text " + formattedText;
//
//        try {
//            Process process = Runtime.getRuntime().exec(command);
//            process.waitFor(); // Chờ cho lệnh thực hiện xong
//        } catch (Exception e) {
//            System.err.println("ADB command failed: " + e.getMessage());
//        }
//    }



//    public void dragAndDrop(By source, By target) {
//        TouchAction action = new TouchAction(driver);
//        WebElement sourceElement = driver.findElement(source);
//        WebElement targetElement = driver.findElement(target);
//        action.longPress(PointOption.point(sourceElement.getCenter()))
//                .moveTo(PointOption.point(targetElement.getCenter()))
//                .release()
//                .perform();
//    }

    public List<WebElement> getElements(By element) {
        waitForElement(element);
        return driver.findElements(element);
    }

    public void enterTextAndSubmit(By element, String text) {
        waitForElement(element);
        WebElement inputElement = driver.findElement(element);
        inputElement.clear();
        inputElement.sendKeys(text);
        inputElement.sendKeys(Keys.RETURN);
    }

    public boolean isElementPresent(By element) {
        try {
            waitForElement(element);
            return driver.findElement(element).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public void waitForElementToBeVisible(By element, int timeout) {
        if (driver == null) {
            throw new IllegalStateException("AndroidDriver is not initialized.");
        }
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }


    public void clickElementWithTouchAction(By element) {
        try {
            // Sử dụng phương thức chờ mới với thời gian chờ mặc định (5 giây)
            waitForElementToBeVisible(element, 60);

            WebElement webElement = driver.findElement(element);

            if (webElement != null && webElement.isDisplayed() && webElement.isEnabled()) {
                new TouchAction<>(driver)
                        .tap(PointOption.point(webElement.getLocation().getX() + webElement.getSize().getWidth() / 2,
                                webElement.getLocation().getY() + webElement.getSize().getHeight() / 2))
                        .perform();
            } else {
                System.out.println("Phần tử không thể click được: " + element);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Không tìm thấy phần tử: " + element);
        } catch (TimeoutException e) {
            System.out.println("Thời gian chờ đã hết: " + element);
        } catch (Exception e) {
            System.out.println("Lỗi khi click: " + e.getMessage());
        }
    }
    // Tìm và nhấn vào nút "Chỉnh sửa"
    public void clickEditButton() {
        By editButtonLocator = By.id("com.ots.c08.vnecsgt.cbcs:id/btnEdit");
        clickElementWithTouchAction(editButtonLocator);
    }
    public void clickElementWithRetry(By element, int retries) {
        while (retries > 0) {
            try {
                waitForElementToBeVisible(element, 5);
                WebElement webElement = driver.findElement(element);

                if (webElement.isDisplayed() && webElement.isEnabled()) {
                    webElement.click();
                    return; // Click thành công, thoát khỏi hàm
                } else {
                    System.out.println("Phần tử không thể click được: " + element);
                }
            } catch (Exception e) {
                System.out.println("Lỗi khi click: " + e.getMessage());
            }

            // Đợi một chút trước khi thử lại
            try {
                Thread.sleep(1000); // Chờ 1 giây
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            retries--; // Giảm số lần thử
        }

        System.out.println("Không thể click vào phần tử sau " + retries + " lần thử: " + element);
    }
    public void clickElementIfVisible(By element) {
        long startTime = System.currentTimeMillis(); // Thời gian bắt đầu
        boolean isVisible = false;

        try {
            // Chờ cho phần tử có thể hiển thị
            waitForElementToBeVisible(element, 5);
            WebElement webElement = driver.findElement(element);

            // Kiểm tra xem phần tử có hiển thị không
            if (webElement.isDisplayed() && webElement.isEnabled()) {
                webElement.click();
                isVisible = true; // Đánh dấu rằng phần tử đã được click
                System.out.println("Đã click vào phần tử: " + element);
            } else {
                System.out.println("Phần tử không thể click được: " + element);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi kiểm tra phần tử: " + e.getMessage());
        } finally {
            long endTime = System.currentTimeMillis(); // Thời gian kết thúc
            long duration = (endTime - startTime) / 1000; // Đếm thời gian hiển thị (giây)

            if (isVisible) {
                System.out.println("Thời gian phần tử hiển thị trước khi click: " + duration + " giây.");
            } else {
                System.out.println("Phần tử không hiển thị hoặc không thể click.");
            }
        }
    }
    public void clickElementWithFluentWait(By element) {
        long startTime = System.currentTimeMillis(); // Bắt đầu thời gian
        try {
            FluentWait<AndroidDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(TimeoutException.class);

            WebElement webElement = wait.until(driver -> {
                List<WebElement> elements = driver.findElements(element);
                if (!elements.isEmpty() && elements.get(0).isDisplayed() && elements.get(0).isEnabled()) {
                    return elements.get(0);
                } else {
                    return null;
                }
            });

            if (webElement != null) {
                webElement.click();
                long endTime = System.currentTimeMillis(); // Kết thúc thời gian
                long duration = endTime - startTime; // Thời gian chờ
                System.out.println("Đã click vào phần tử: " + element);
                System.out.println("Thời gian phần tử hiển thị trước khi click: " + duration + " ms.");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi click vào phần tử: " + e.getMessage());
        }
    }









    public void waitForElementToBeVisible(By element) {
        if (driver == null) {
            throw new IllegalStateException("AndroidDriver is not initialized.");
        }
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public boolean isElementVisible(By element) {
        try {
            return driver.findElement(element).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clearText(By element) {
        waitForElement(element);
        WebElement webElement = driver.findElement(element);
        webElement.clear();
    }

    public boolean isButtonEnabled(By buttonElement) {
        waitForElement(buttonElement);
        return driver.findElement(buttonElement).isEnabled();
    }

    public void switchToTab(int tabIndex) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabIndex < tabs.size()) {
            driver.switchTo().window(tabs.get(tabIndex));
        } else {
            throw new IndexOutOfBoundsException("Tab index out of range");
        }
    }

//    public void waitForCondition(Function<AndroidDriver, Boolean> condition) {
//        wait.until(condition);
//    }

    public String getElementTextWhenVisible(By element) {
        waitForElementToBeVisible(element);
        return driver.findElement(element).getText();
    }

    public void takeScreenshotWithTimestamp(String directory) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File destFile = new File(directory + "/screenshot_" + timestamp + ".png");
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doubleClickElement(By element) {
        waitForElement(element);
        WebElement webElement = driver.findElement(element);
        Actions actions = new Actions(driver);
        actions.doubleClick(webElement).perform();
    }

    public void hoverOverElement(By element) {
        waitForElement(element);
        WebElement webElement = driver.findElement(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).perform();
    }

    public void hoverAndClick(By element) {
        waitForElement(element);
        WebElement webElement = driver.findElement(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).click().perform();
    }
}
