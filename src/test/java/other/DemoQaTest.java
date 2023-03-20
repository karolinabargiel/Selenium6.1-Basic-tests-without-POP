package other;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DemoQaTest extends TestBase {

    @ParameterizedTest
    @CsvSource({"https://demoqa.com/automation-practice-form"})
    @DisplayName("DemoQa select options")
    void shouldSelectOptions(String url) {
        driver.get(url);
        WebElement subjects = driver.findElement(By.cssSelector(".subjects-auto-complete__control.css-yk16xz-control"));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".subjects-auto-complete__control.css-yk16xz-control")));
        subjects.click();
        WebElement subjects2 = driver.findElement(By.cssSelector("#subjectsInput"));
        subjects2.sendKeys("m");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#react-select-2-option-0")));
        WebElement maths = driver.findElement(By.cssSelector("#react-select-2-option-0"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maths);
        maths.click();
        subjects2.sendKeys("a");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#react-select-2-option-2")));
        WebElement arts = driver.findElement(By.cssSelector("#react-select-2-option-2"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", arts);
        arts.click();
        assertThat(driver.findElement(By.cssSelector("[class*='subjects-auto-complete__value-container']")).getText()).contains("Maths", "Arts");


    }
}
