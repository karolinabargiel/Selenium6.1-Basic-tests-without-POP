package widgets;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SliderTest extends TestBase {

    String sliderStatus;
    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/slider.php"})
    @DisplayName("Slider test")
    void shouldMoveSlider (String url) throws InterruptedException {
        driver.get(url);
        moveSlider(50);
        assertTrue(sliderStatus.contains("left: 50%"));
        setSliderToZero();
        moveSlider(80);
        assertTrue(sliderStatus.contains("left: 80%"));
        setSliderToZero();
        moveSlider(80);
        assertTrue(sliderStatus.contains("left: 80%"));
        setSliderToZero();
        moveSlider(20);
        assertTrue(sliderStatus.contains("left: 20%"));
        setSliderToZero();
        assertTrue(sliderStatus.contains("left: 0%"));


    }

    private void moveSlider(int targetValue) {
        Actions actions = new Actions(driver);
        WebElement sliderPicker = driver.findElement(By.id("custom-handle"));
        int minValue = 0;
        int maxValue = 100;
        int pixelRange = 1108;
        double pixelsPerValue = (double) pixelRange / (maxValue - minValue);
        int pixelsToMove = (int) Math.round((targetValue - minValue) * pixelsPerValue-16);
        actions.dragAndDropBy(sliderPicker, pixelsToMove, 0).perform();
        sliderStatus = sliderPicker.getAttribute("style");

    }

    private void setSliderToZero () {
        Actions actions = new Actions(driver);
        WebElement sliderPicker = driver.findElement(By.id("custom-handle"));
        int zeroPoint = 204-16;
        int currentLocation = sliderPicker.getLocation().getX();
        int targetLocation = currentLocation - zeroPoint;
        actions.moveToElement(sliderPicker,0,0);
        actions.clickAndHold();
        actions.moveByOffset(-targetLocation,0);
        actions.build().perform();
        sliderStatus = sliderPicker.getAttribute("style");

    }
}
