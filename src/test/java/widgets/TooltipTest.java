package widgets;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TooltipTest extends TestBase {

    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/tooltip.php"})
    @DisplayName("Tooltip test")
    void shouldValidateTooltip (String url) {
        driver.get(url);
        WebElement ageField = driver.findElement(By.cssSelector("#age"));
        String textTooltip = ageField.getAttribute("title");
        System.out.println(textTooltip);


    }
}
