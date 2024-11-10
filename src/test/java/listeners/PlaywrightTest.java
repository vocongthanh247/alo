package listeners;

import com.microsoft.playwright.*;

public class PlaywrightTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // Hoặc true nếu bạn muốn chạy ẩn
        page = browser.newPage();
    }

    public void tearDown() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    public Page getPage() {
        return page;
    }
}