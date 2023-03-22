package widgets;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class AutocompleteTest extends TestBase {
    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/autocomplete.php"})
    @DisplayName("Autocomplete test")
    void shouldValidateAutocomplete (String url) {
        driver.get(url);
        Random rand = new Random();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement search = driver.findElement(By.cssSelector("#search"));
        search.sendKeys("a");
        List<WebElement> options = driver.findElements(By.cssSelector("[class='ui-menu-item-wrapper']"));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[class='ui-menu-item-wrapper']")));
        assertThat(options.isEmpty()).isFalse();
        for (WebElement option : options) {
            System.out.println(option.getText());
        }
        var randomElement= options.get(rand.nextInt(options.size()));
        wait.until(ExpectedConditions.elementToBeClickable(randomElement));
        String choosedElement = randomElement.getText();
        randomElement.click();
        String textInSearch = search.getAttribute("value");
        assertThat(choosedElement).isEqualTo(textInSearch);


    }
}
