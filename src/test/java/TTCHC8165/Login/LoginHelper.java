package TTCHC8165.Login;

import base.ValidateHelpersPlayWright;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LoginHelper {
    private Page page;
    private ValidateHelpersPlayWright validateHelpers;

    public LoginHelper(Page page, ValidateHelpersPlayWright validateHelpers) {
        this.page = page;
        this.validateHelpers = validateHelpers;
    }

    public void login(String username, String password) {
        validateHelpers.setText("input#ext-element-5", username); // Nhập tên đăng nhập
        validateHelpers.setText("input#ext-element-21", password); // Nhập mật khẩu
        validateHelpers.clickElement("div#ext-element-46"); // Nhấn nút đăng nhập
        validateHelpers.waitForPageLoaded();

        // Đợi cho đến khi URL thay đổi
//        page.waitForURL("https://sbots-ttch165-portal.gtelots.dev/dashboard"); // Chờ cho đến khi URL chứa "/dashboard"

        // Đợi cho phần tử xuất hiện và có thể nhấp được
        page.waitForSelector("div#ext-element-76", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        validateHelpers.clickElement("div#ext-element-76"); // Nhấp vào phần tử cần thiết
    }
}