package listeners;

import listeners.PlaywrightTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SigninTest extends PlaywrightTest {

    @BeforeMethod
    public void setUp() {
        super.setUp(); // Gọi phương thức setUp() của lớp cơ sở
        page.navigate("http://10.0.0.218/");
        // Thực hiện các bước đăng nhập hoặc thao tác khác
    }

    @Test
    public void testSignin() {
        // Thực hiện các bước kiểm tra đăng nhập
    }

    @AfterClass
    public void tearDown() {
        super.tearDown(); // Gọi phương thức tearDown() của lớp cơ sở
    }
}