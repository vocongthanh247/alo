package base;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.MouseButton;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class ValidateHelpersPlayWright {
    private Page page;

    public ValidateHelpersPlayWright(Page page) {
        this.page = page;
    }

    public void waitForElement(String selector) {
        page.waitForSelector(selector);
    }

    public void setText(String selector, String value) {
        waitForElement(selector);
        page.fill(selector, value);
    }
    public void checkElementVisibility(Page page, String selector) {
        ElementHandle element = page.querySelector(selector);
        if (element == null) {
            System.out.println("Phần tử không tồn tại.");
        } else {
            boolean isVisible = element.isVisible();
            if (!isVisible) {
                System.out.println("Phần tử không hiển thị.");
            } else {
                System.out.println("Phần tử đang hiển thị.");
            }
        }
    }

    public String getCssValue(String selector, String propertyName) {
        waitForElement(selector);
        return page.locator(selector).evaluate("(element, property) => window.getComputedStyle(element).getPropertyValue(property)", propertyName).toString();
    }

    public void clickElement(String selector) {
        waitForElement(selector);
        page.click(selector);
    }
    public boolean isElementNotDisplayed(Page page, String selector) {
        try {
            ElementHandle element = page.querySelector(selector);
            if (element != null) {
                boolean isVisible = element.isVisible();
                return !isVisible; // Trả về true nếu phần tử không hiển thị
            }
            return true; // Nếu không tìm thấy phần tử, coi như nó không hiển thị
        } catch (Exception e) {
            System.err.println("Lỗi khi kiểm tra phần tử: " + e.getMessage());
            return false; // Trả về false trong trường hợp có lỗi
        }
    }
    public void assertElementDisappeared(String selector) {
        try {
            // Đợi tối đa 10 giây để element biến mất
            page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                    .setState(WaitForSelectorState.HIDDEN)
                    .setTimeout(10000));

            // Kiểm tra thêm lần nữa để chắc chắn element không còn
            boolean isVisible = page.isVisible(selector);
            Assert.assertFalse(isVisible, "Element vẫn còn hiển thị trên UI.");

            System.out.println("Element đã biến mất khỏi UI như mong đợi: " + selector);
        } catch (TimeoutError e) {
            Assert.fail("Element vẫn còn hiển thị sau thời gian chờ: " + selector);
        }
    }

    public void assertElementText(String selector, String expectedText) {
        waitForElement(selector);
        String actualText = page.textContent(selector);
        Assert.assertEquals(actualText, expectedText, "Văn bản không khớp với giá trị mong đợi.");

        // Nếu văn bản khớp, hiển thị văn bản hiện tại
        if (actualText.equals(expectedText)) {
            System.out.println("Văn bản hiển thị khớp với giá trị mong đợi: " + actualText);
        }
    }

    public void selectOptionByText(String selector, String text) {
        waitForElement(selector);
        page.selectOption(selector, text);
    }

    public boolean isCheckboxChecked(String checkboxSelector) {
        waitForElement(checkboxSelector);
        return page.isChecked(checkboxSelector);
    }
    // Phương thức để đợi cho một phần tử hiển thị
    public void waitForElementVisible(String selector, int timeout) {
        try {
            page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(timeout * 1000).setState(WaitForSelectorState.VISIBLE));
        } catch (Exception e) {
            System.out.println("Phần tử không hiển thị trong thời gian quy định: " + selector);
        }
    }

    public void scrollToElement(String selector) {
        waitForElement(selector);
        page.evaluate("element => element.scrollIntoView(true);", page.querySelector(selector));
    }

    public void waitForPageLoaded() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public String getElementAttribute(String selector, String attributeName) {
        waitForElement(selector);
        return page.getAttribute(selector, attributeName);
    }

    public void dragAndDrop(String sourceSelector, String targetSelector) {
        waitForElement(sourceSelector);
        waitForElement(targetSelector);
        page.hover(sourceSelector);
        page.dragAndDrop(sourceSelector, targetSelector);
    }


    public List<String> getElements(String elementsSelector) {
        waitForElement(elementsSelector);
        List<String> texts = new ArrayList<>();
        for (String element : page.locator(elementsSelector).allTextContents()) {
            texts.add(element);
        }
        return texts;
    }


    public void enterTextAndSubmit(String selector, String text) {
        waitForElement(selector);
        page.fill(selector, text);
        page.press(selector, "Enter");
    }

    public boolean isElementPresent(String selector) {
        try {
            waitForElement(selector);
            return page.isVisible(selector);
        } catch (Exception e) {
            return false;
        }
    }


    public void waitForElementToBeVisible(String selector) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public boolean isElementVisible(String selector) {
        try {
            return page.isVisible(selector);
        } catch (Exception e) {
            return false;
        }
    }

    public void clearText(String selector) {
        waitForElement(selector);
        page.fill(selector, "");
    }

    public boolean isButtonEnabled(String buttonSelector) {
        // Đợi cho phần tử có sẵn
        waitForElement(buttonSelector);

        // Kiểm tra trạng thái enabled của nút
        boolean isEnabled = page.isEnabled(buttonSelector);

        // Log trạng thái của nút
        if (isEnabled) {
            System.out.println("Nút với selector '" + buttonSelector + "' đang được kích hoạt.");
        } else {
            System.out.println("Nút với selector '" + buttonSelector + "' không được kích hoạt.");
        }

        return isEnabled;
    }

    public List<String> getElementsText(String elementsSelector) {
        List<String> texts = new ArrayList<>();
        for (String element : page.locator(elementsSelector).allTextContents()) {
            texts.add(element);
        }
        return texts;
    }

    public void switchToTab(int tabIndex) {
        List<Page> tabs = new ArrayList<>(page.context().pages());
        if (tabIndex < tabs.size()) {
            tabs.get(tabIndex).bringToFront();
        } else {
            throw new IndexOutOfBoundsException("Tab index out of range");
        }
    }
    public void checkButtonDisabled(String selector) {
        try {
            // Đợi element xuất hiện với timeout ngắn
            page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(5000));

            // Kiểm tra trạng thái disabled bằng nhiều cách
            boolean isDisabled = (boolean) page.evaluate("selector => {" +
                    "const element = document.evaluate(selector, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "if (!element) return false;" +
                    "return element.disabled === true || " +
                    "element.hasAttribute('disabled') || " +
                    "element.classList.contains('disabled') || " +
                    "element.getAttribute('aria-disabled') === 'true' || " +
                    "getComputedStyle(element).pointerEvents === 'none' || " +
                    "element.style.opacity === '0.5';" +
                    "}", selector);

            System.out.println("Kiểm tra button: " + selector);
            System.out.println("Trạng thái disabled: " + isDisabled);

            // Nếu button disabled thì test pass
            if (isDisabled) {
                Assert.assertTrue(true, "Button is disabled as expected");
            } else {
                // Thêm thông tin debug nếu button không disabled
                String buttonState = (String) page.evaluate("selector => {" +
                        "const element = document.evaluate(selector, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                        "return `Classes: ${element.className}, Attributes: ${element.outerHTML}`;" +
                        "}", selector);
                System.out.println("Button state details: " + buttonState);
                Assert.fail("Button is not disabled");
            }

        } catch (TimeoutError e) {
            System.out.println("Không tìm thấy button: " + selector);
            Assert.fail("Button not found: " + selector);
        } catch (Exception e) {
            System.out.println("Lỗi khi kiểm tra button: " + e.getMessage());
            Assert.fail("Error checking button state: " + e.getMessage());
        }
    }
    public void rightClick(String selector) {
        try {
            // Đợi element xuất hiện
            page.waitForSelector(selector);

            // Thực hiện click chuột phải
            page.click(selector, new Page.ClickOptions()
                    .setButton(MouseButton.RIGHT)
                    .setTimeout(5000));

            System.out.println("Đã click chuột phải thành công vào element: " + selector);
        } catch (TimeoutError e) {
            Assert.fail("Không thể click chuột phải vào element sau timeout: " + selector);
        } catch (Exception e) {
            Assert.fail("Lỗi khi click chuột phải: " + e.getMessage());
        }
    }

    public void waitForCondition(Function<Page, Boolean> condition) {
        page.waitForFunction("() => " + condition.apply(page));
    }

    public String getElementTextWhenVisible(String selector) {
        waitForElementToBeVisible(selector);
        return page.textContent(selector);
    }

    public void takeScreenshotWithTimestamp(String directory) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String filePath = directory + "/screenshot_" + timestamp + ".png";
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)));
    }

    public void doubleClickElement(String selector) {
        waitForElement(selector);
        page.dblclick(selector);
    }

//    public void assertElementsText(String elementsSelector, List<String> expectedTexts) {
//        List<String> actualTexts = getElementsText(elementsSelector);
//        Assert.assertEquals(actualTexts, expectedTexts, "Văn bản của các phần tử không khớp với giá trị mong đợi.");
//    }

    public void hoverOverElement(String selector) {
        waitForElement(selector);
        page.hover(selector);
    }

    public void hoverAndClick(String selector) {
        waitForElement(selector);
        page.hover(selector);
        page.click(selector);
    }
}