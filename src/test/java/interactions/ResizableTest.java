package interactions;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResizableTest extends TestBase {
    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/resizable.php"})
    @DisplayName("Resizable test")
    void shouldDragAndDropElement(String url) {
        driver.get(url);
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement resizable = driver.findElement(By.cssSelector(".ui-resizable-handle.ui-resizable-se.ui-icon.ui-icon-gripsmall-diagonal-se"));
        wait.until(ExpectedConditions.elementToBeClickable(resizable));
        int xOffSet = 10 + 16;
        int yOffSet = 10 + 16;
        actions.clickAndHold(resizable).moveByOffset(xOffSet, 0).release().build().perform();
        actions.clickAndHold(resizable).moveByOffset(0, yOffSet).release().build().perform();
        actions.clickAndHold(resizable).moveByOffset(xOffSet, yOffSet).release().build().perform();

    }
}
