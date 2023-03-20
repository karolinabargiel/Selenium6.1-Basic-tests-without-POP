package other;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HighSiteTests extends TestBase {

    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/high-site.php"})
    @DisplayName("High site test actions")
    void shouldScrollPageAction(String url) {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Actions actions = new Actions(driver);
        WebElement submitButtonParent = driver.findElement(By.cssSelector(".lead.high-site-paragraph.show-button"));
        actions.moveToElement(submitButtonParent).perform();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 250)");
        WebElement submitButton = driver.findElement(By.cssSelector("#scroll-button"));
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        actions.moveToElement(submitButton).perform();
        takeScreenshot();

    }

    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/high-site.php"})
    @DisplayName("High site test JS")
    void shouldScrollPageJS (String url) {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement submitButtonParent = driver.findElement(By.cssSelector(".lead.high-site-paragraph.show-button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButtonParent);
        wait.until(ExpectedConditions.visibilityOf(submitButtonParent));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-250)");
        WebElement submitButton = driver.findElement(By.cssSelector("#scroll-button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        takeScreenshot();

    }
}
