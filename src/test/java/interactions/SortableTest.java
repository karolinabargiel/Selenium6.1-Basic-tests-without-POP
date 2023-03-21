package interactions;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SortableTest extends TestBase {

    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/sortable.php"})
    @DisplayName("Sortable test")
    void shouldSortElements(String url) {
        driver.get(url);
        List<WebElement> sortableElements = driver.findElements(By.cssSelector("#sortable li"));
        assertThat(sortableElements.isEmpty()).isFalse();
        Collections.shuffle(sortableElements);
        for (WebElement sortableElement : sortableElements) {
            System.out.println(sortableElement.getText());
        }

    }
}
