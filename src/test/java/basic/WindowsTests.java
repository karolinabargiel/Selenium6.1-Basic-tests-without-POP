package basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class WindowsTests extends TestBase {

    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/windows-tabs.php"})
    @DisplayName("Window test")
    void shouldNavigateToWindows(String url) throws InterruptedException {
        //GIVEN
        driver.get(url);
        String currentWindowHandle = driver.getWindowHandle();
        driver.findElement(By.cssSelector("#newBrowserWindow")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        driver.manage().window().maximize();
        List<WebElement> tableRows = driver.findElements(By.cssSelector("tbody tr"));
        assertThat(tableRows.isEmpty()).isFalse();
        List<WebElement> filteredElements = tableRows.stream()
                .filter(element -> element.getText().contains("Switzerland"))
                .filter(element -> element.getText().contains("4"))
                .toList();
        for (WebElement filteredElement : filteredElements) {
            System.out.println(filteredElement.findElement(By.cssSelector("th")).getText());
            System.out.println(filteredElement.findElement(By.xpath("./td[1]")).getText());
            System.out.println(filteredElement.findElement(By.xpath("./td[2]")).getText());

        }
        driver.close();
        driver.switchTo().window(currentWindowHandle);
        driver.findElement(By.cssSelector("#newMessageWindow")).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        driver.manage().window().maximize();
        System.out.println(driver.findElement(By.cssSelector("body")).getText());
        driver.close();
        driver.switchTo().window(currentWindowHandle);
        driver.findElement(By.cssSelector("#newBrowserTab")).click();
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        tableRows = driver.findElements(By.cssSelector("tbody tr"));
        assertThat(tableRows.isEmpty()).isFalse();
        filteredElements = tableRows.stream()
                .filter(element -> element.getText().contains("Switzerland"))
                .filter(element -> element.getText().contains("4"))
                .toList();
        for (WebElement filteredElement : filteredElements) {
            System.out.println(filteredElement.findElement(By.cssSelector("th")).getText());
            System.out.println(filteredElement.findElement(By.xpath("./td[1]")).getText());
            System.out.println(filteredElement.findElement(By.xpath("./td[2]")).getText());

        }

    }
}
