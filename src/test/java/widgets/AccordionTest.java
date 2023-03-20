package widgets;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccordionTest extends TestBase {

    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/accordion.php"})
    @DisplayName("Accordion test")
    void shouldValidateAccordionNavigation(String url) {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        System.out.println(driver.findElement(By.cssSelector("div[id='ui-id-2'] p")).getText());
        WebElement section2 = driver.findElement(By.cssSelector("#ui-id-3"));
        section2.click();
        WebElement textSection2 = driver.findElement(By.cssSelector("div[id='ui-id-4'] p"));
        wait.until(ExpectedConditions.visibilityOf(textSection2));
        System.out.println(textSection2.getText());
        WebElement section3 = driver.findElement(By.cssSelector("#ui-id-5"));
        section3.click();
        WebElement textSection3 = (driver.findElement(By.cssSelector("#ui-id-6")));
        wait.until(ExpectedConditions.visibilityOf(textSection3));
        System.out.println(textSection3.getText());
        WebElement section4 = driver.findElement(By.cssSelector("#ui-id-7"));
        section4.click();
        WebElement textSection4 = driver.findElement(By.cssSelector("#ui-id-8"));
        wait.until(ExpectedConditions.visibilityOf(textSection4));
        System.out.println(textSection4.getText());



    }
}
