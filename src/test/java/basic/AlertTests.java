package basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.assertj.core.api.Assertions.assertThat;

public class AlertTests extends TestBase {
    String url = "http://www.seleniumui.moderntester.pl/alerts.php";

    @ParameterizedTest
    @CsvSource({"OK button pressed"})
    @DisplayName("Simple alert")
    void simpleAlert(String expectedText) {
        //GIVEN
        driver.get(url);
        driver.findElement(By.cssSelector("#simple-alert")).click();
        //WHEN
        driver.switchTo().alert().accept();
        //THEN
        assertThat(driver.findElement(By.cssSelector("#simple-alert-label")).getText()).isEqualTo(expectedText);

    }

    @ParameterizedTest
    @CsvSource({"Hello Lord Vader! How are you today?, Lord Vader"})
    @DisplayName("Prompt alert")
    void promptAlert(String expectedText, String inputName) {
        //GIVEN
        driver.get(url);
        driver.findElement(By.cssSelector("#prompt-alert")).click();
        //WHEN
        driver.switchTo().alert().sendKeys(inputName);
        driver.switchTo().alert().accept();
        //THEN
        assertThat(driver.findElement(By.cssSelector("#prompt-label")).getText()).isEqualTo(expectedText);

    }

    @ParameterizedTest
    @ValueSource(strings = {"You pressed OK!"})
    @DisplayName("Confirm alert")
    void confirmAlert(String expectedText) {
        //GIVEN
        driver.get(url);
        driver.findElement(By.cssSelector("#confirm-alert")).click();
        //WHEN
        driver.switchTo().alert().accept();
        //THEN
        assertThat(driver.findElement(By.cssSelector("#confirm-label")).getText()).isEqualTo(expectedText);
        //GIVEN
        driver.findElement(By.cssSelector("#confirm-alert")).click();
        //WHEN
        driver.switchTo().alert().dismiss();
        expectedText = "You pressed Cancel!";
        //THEN
        assertThat(driver.findElement(By.cssSelector("#confirm-label")).getText()).isEqualTo(expectedText);

    }

    @ParameterizedTest
    @CsvSource({"OK button pressed"})
    @DisplayName("Delayed alert")
    void delayedAlert(String expectedText) {
        //GIVEN
        driver.get(url);
        driver.findElement(By.cssSelector("#delayed-alert")).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        //WHEN
        driver.switchTo().alert().accept();
        //THEN
        assertThat(driver.findElement(By.cssSelector("#delayed-alert-label")).getText()).isEqualTo(expectedText);

    }


}
