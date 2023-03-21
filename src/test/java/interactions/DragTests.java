package interactions;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragTests extends TestBase {

    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/draggable.php"})
    @DisplayName("Drag test")
    void shouldDragElement (String url) {
        driver.get(url);
        Actions actions = new Actions(driver);
        WebElement from = driver.findElement(By.id("draggable"));
        int xLocationOfElement = from.getLocation().getX();
        int yLocationOfElement =  from.getLocation().getY();
        System.out.println("xOffset1--->"+xLocationOfElement+" yOffset1--->"+yLocationOfElement);
        int xOffsetOfBrowser = 1528 - 150;
        int yOffsetOfBrowser = 0;
        int xOffset = xOffsetOfBrowser - xLocationOfElement;
        int yOffset= yOffsetOfBrowser - yLocationOfElement;
        actions.dragAndDropBy(from, xOffset, yOffset).perform();
        xLocationOfElement = from.getLocation().getX();
        yLocationOfElement =  from.getLocation().getY();
        System.out.println("xOffset1--->"+xLocationOfElement+" yOffset1--->"+yLocationOfElement);
        xOffsetOfBrowser = 0;
        yOffsetOfBrowser = 700-150;
        actions.dragAndDropBy(from, xOffsetOfBrowser, yOffsetOfBrowser).perform();
        xLocationOfElement = from.getLocation().getX();
        yLocationOfElement =  from.getLocation().getY();
        System.out.println("xOffset1--->"+xLocationOfElement+" yOffset1--->"+yLocationOfElement);
        int centreX = driver.manage().window().getSize().getWidth() / 2;
        int centreY = driver.manage().window().getSize().getHeight() / 2;
        xOffset = centreX - xLocationOfElement - 75;
        yOffset = centreY - yLocationOfElement - 75;
        actions.dragAndDropBy(from, xOffset, yOffset).perform();
        xLocationOfElement = from.getLocation().getX();
        yLocationOfElement =  from.getLocation().getY();
        System.out.println("xOffset1--->"+xLocationOfElement+" yOffset1--->"+yLocationOfElement);
        int newX = 0;
        int newY = 720 - 150;
        xOffset = newX - xLocationOfElement;
        yOffset = newY - yLocationOfElement;
        actions.dragAndDropBy(from, xOffset, yOffset).perform();


    }
}
