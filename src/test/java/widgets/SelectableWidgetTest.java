package widgets;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectableWidgetTest extends TestBase {
    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/selectmenu.php"})
    @DisplayName("Select test")
    void shouldSelectOptions (String url) {
        driver.get(url);
        Random rand = new Random();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement selectSpeed = driver.findElement(By.cssSelector("#speed-button"));
        selectSpeed.click();
        List<WebElement> selectSpeedOptions = driver.findElements(By.cssSelector("[class='ui-menu-item']"));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[class='ui-menu-item']")));
        assertThat(selectSpeedOptions.isEmpty()).isFalse();
        WebElement randomSpeed = selectSpeedOptions.get(rand.nextInt(selectSpeedOptions.size()));
        randomSpeed.click();
        driver.findElement(By.cssSelector("#files-button")).click();
        List<WebElement> selectFile = driver.findElements(By.cssSelector("[class='ui-menu-item']"));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[class='ui-menu-item']")));
        for (WebElement element : selectFile) {
            if (element.getText().equals("jQuery.js")) {
                element.click();
                break;
            }
        }
        driver.findElement(By.cssSelector("#number-button")).click();
        List<WebElement> selectNumber = driver.findElements(By.xpath("//ul[@id='number-menu']/*"));
        assertThat(selectNumber.isEmpty()).isFalse();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='number-menu']/*")));
        WebElement element = selectNumber.get(3);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        driver.findElement(By.cssSelector("#salutation-button")).click();
        List<WebElement> selectTitle = driver.findElements(By.xpath("//ul[@id='salutation-menu']/*[not(position()=1)]"));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='salutation-menu']/*[not(position()=1)]")));
        assertThat(selectTitle.isEmpty()).isFalse();
        WebElement randomTitle = selectTitle.get(rand.nextInt(selectTitle.size()));
        wait.until(ExpectedConditions.elementToBeClickable(randomTitle));
        randomTitle.click();


    }
}
