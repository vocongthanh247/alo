package base;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.Pause;
import java.time.Duration;
import java.util.Collections;

public class W3CActions {
    private final AndroidDriver driver;
    private final Sequence sequence;
    private final PointerInput finger;

    public W3CActions(AndroidDriver driver) {
        this.driver = driver;
        this.finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        this.sequence = new Sequence(finger, 0);
    }

    public W3CActions press(int x, int y) {
        sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        return this;
    }

    public W3CActions moveTo(int x, int y) {
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), x, y));
        return this;
    }

    public W3CActions waitAction(Duration duration) {
        sequence.addAction(new Pause(finger, duration));
        return this;
    }

    public W3CActions release() {
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        return this;
    }

    public void perform() {
        driver.perform(Collections.singletonList(sequence));
    }
}
