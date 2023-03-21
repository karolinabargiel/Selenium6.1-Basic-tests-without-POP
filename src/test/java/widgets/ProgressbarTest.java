package widgets;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProgressbarTest extends TestBase {
    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/progressbar.php"})
    @DisplayName("Progressbar test")
    void shouldValidateProgressbar(String url) {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement progressbar = driver.findElement(By.cssSelector(".progress-label"));
        wait.until(ExpectedConditions.textToBePresentInElement(progressbar, "Complete!"));
        assertThat(progressbar.getText()).isEqualTo("Complete!");


    }

    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/progressbar.php"})
    @DisplayName("Progressbar test")
    void shouldValidateProgressbarClass (String url) {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement progressbar = driver.findElement(By.cssSelector("[class*= 'ui-progressbar-value']"));
        wait.until(ExpectedConditions.attributeContains(progressbar, "class", "ui-progressbar-complete"));
        assertThat(progressbar.getAttribute("class")).contains("ui-progressbar-complete");

    }

}
