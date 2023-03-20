package widgets;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MenuTest extends TestBase {

    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/menu-item.php"})
    @DisplayName("Menu test")
    void shouldValidateWidgetMenu (String url) {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Actions action = new Actions(driver);
        WebElement music = driver.findElement(By.cssSelector("#ui-id-9"));
        action.moveToElement(music).perform();
        WebElement jazz = driver.findElement(By.xpath("(//li[@class='ui-menu-item'])[11]"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='ui-menu-item'])[11]")));
        action.moveToElement(jazz).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#ui-id-16"))).click();

    }

    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/modal-dialog.php"})
    @DisplayName("Modal dialog test")
    void shouldValidateModalDialog (String url) {
        driver.get(url);
        driver.findElement(By.cssSelector("#create-user")).click();
        var name = driver.findElement(By.cssSelector("#name"));
        name.clear();
        name.sendKeys("test");
        var email = driver.findElement(By.cssSelector("#email"));
        email.clear();
        email.sendKeys("test@test.com");
        var pass = driver.findElement(By.cssSelector("#password"));
        pass.clear();
        pass.sendKeys("Test12345");
        driver.findElement(By.xpath("//button[normalize-space()='Create an account']")).click();
        List<WebElement> tableRows = driver.findElements(By.cssSelector("tbody tr"));
        for (WebElement tableRow : tableRows) {
            System.out.println(tableRow.getText());
        }
        assertThat(tableRows.size()).isEqualTo(2);


    }


}
