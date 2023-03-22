package interactions;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SortableTest extends TestBase {

    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/sortable.php"})
    @DisplayName("Sortable test")
    void shouldSortElements(String url) {
        List<Integer> order = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6));
        driver.get(url);
        List<WebElement> sortableElements = driver.findElements(By.cssSelector("#sortable li"));
        assertThat(sortableElements.isEmpty()).isFalse();
        Collections.shuffle(order);
        List<WebElement> sortedElements = new ArrayList<>(sortableElements.size());
        for (int index : order) {
            sortedElements.add(sortableElements.get(index));
        }

        Actions actions = new Actions(driver);
        WebElement target = driver.findElement(By.cssSelector("#sortable"));
        for (WebElement element : sortedElements) {
            actions.clickAndHold(element)
                    .moveToElement(target)
                    .release()
                    .perform();
        }

    }

}
