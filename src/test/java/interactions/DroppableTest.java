package interactions;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DroppableTest extends TestBase  {
    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/droppable.php"})
    @DisplayName("Drag and drop test")
    void shouldDragAndDropElement(String url) {
        driver.get(url);
        Actions actions = new Actions(driver);
        WebElement from = driver.findElement(By.id("draggable"));
        WebElement to = driver.findElement(By.id("droppable"));
        actions.dragAndDrop(from, to).perform();
        String textTo = to.getText();
        assertThat(textTo).isEqualTo("Dropped!");

    }
}
