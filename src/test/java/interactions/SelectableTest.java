package interactions;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SelectableTest extends TestBase {
    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/selectable.php"})
    @DisplayName("Selectable test")
    void shouldSelectElements (String url) {
        driver.get(url);
        Actions actions = new Actions(driver);
        WebElement item1 = driver.findElement(By.xpath("//li[normalize-space()='Item 1']"));
        WebElement item2 =driver.findElement(By.xpath("//li[normalize-space()='Item 2']"));
        WebElement item4 =driver.findElement(By.xpath("//li[normalize-space()='Item 4']"));
        actions.keyDown(Keys.LEFT_CONTROL)
                .click(item1)
                .click(item2)
                .click(item4)
                .keyUp(Keys.LEFT_CONTROL).build().perform();
        String feedback = driver.findElement(By.cssSelector("#feedback")).getText();
        assertThat(feedback).isEqualTo("You've selected: #1 #2 #4.");
    }
}
