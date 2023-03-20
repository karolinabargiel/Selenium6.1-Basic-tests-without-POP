package basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IframeTest extends TestBase {
    @ParameterizedTest
    @CsvSource({"https://demoqa.com/frames"})
    @DisplayName("Iframe test")
    void iframeTest (String url) {
        //GIVEN
        driver.get(url);
        //WHEN
        driver.switchTo().frame("frame1");
        System.out.println(driver.findElement(By.cssSelector("#sampleHeading")).getText());
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame2");
        System.out.println(driver.findElement(By.cssSelector("#sampleHeading")).getText());
        driver.switchTo().parentFrame();
        //THEN
        try {
            driver.findElement(By.cssSelector(".left-pannel"));
        } catch (NoSuchElementException ex) {
            fail ("Element not found");
        }


    }

}
