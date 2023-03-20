package basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;


public class TableTest extends TestBase {
    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/table.php"})
    @DisplayName("Table test")
    void shouldValidateTable(String url) {
        //GIVEN
        driver.get(url);
        List<WebElement> tableRows = driver.findElements(By.cssSelector("tbody tr"));
        assertThat(tableRows.isEmpty()).isFalse();

        //WHEN
        List<WebElement> filteredElements = tableRows.stream()
                .filter(element -> element.getText().contains("Switzerland"))
                .filter(element -> element.getText().contains("4"))
                .toList();

        //THEN
        for (WebElement filteredElement : filteredElements) {
            System.out.println(filteredElement.findElement(By.cssSelector("th")).getText());
            System.out.println(filteredElement.findElement(By.xpath("./td[1]")).getText());
            System.out.println(filteredElement.findElement(By.xpath("./td[2]")).getText());

        }

    }
}
