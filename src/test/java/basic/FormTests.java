package basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.io.File;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormTests extends TestBase {

   @ParameterizedTest
   @CsvFileSource(resources = "/testdata.csv")
   @DisplayName("Form test")
   void shouldValidateFormSend(String url, String firstName, String lastName, String email, String age) {
      //GIVEN
      Random rand = new Random();
      driver.get(url);
      WebElement firstNameInput = driver.findElement(By.cssSelector("#inputFirstName3"));
      firstNameInput.clear();
      firstNameInput.sendKeys(firstName);
      WebElement lastNameInput = driver.findElement(By.cssSelector("#inputLastName3"));
      lastNameInput.clear();
      lastNameInput.sendKeys(lastName);
      WebElement emailInput = driver.findElement(By.cssSelector("#inputEmail3"));
      emailInput.clear();
      emailInput.sendKeys(email);
      List<WebElement> genders = driver.findElements(By.cssSelector("[name='gridRadiosSex']"));
      var randomElementSex = genders.get(rand.nextInt(genders.size()));
      randomElementSex.click();
      driver.findElement(By.cssSelector("#gridRadiosFemale")).click();
      driver.findElement(By.cssSelector("#inputAge3")).sendKeys(age);
      List<WebElement> yearOfExperience = driver.findElements(By.cssSelector("[name*='gridRadiosExperience']"));
      var randomElement = yearOfExperience.get(rand.nextInt(yearOfExperience.size()));
      randomElement.click();
      driver.findElement(By.cssSelector("#gridCheckAutomationTester")).click();
      List<WebElement> continentsAll = driver.findElements(By.cssSelector("#selectContinents > :not(:first-child)"));
      WebElement randomContinent = continentsAll.get(rand.nextInt(continentsAll.size()));
      randomContinent.click();
      WebElement switchCommands = driver.findElement(By.cssSelector("option[value='switch-commands']"));
      WebElement waitCommands = driver.findElement(By.cssSelector("option[value='wait-commands']"));
      Actions actions = new Actions(driver);
      actions.keyDown(Keys.LEFT_CONTROL)
              .click(switchCommands)
              .click(waitCommands)
              .keyUp(Keys.LEFT_CONTROL).build().perform();
      File file = new File("src/main/resources/testfile.txt");
      driver.findElement(By.cssSelector("#chooseFile")).sendKeys(file.getAbsolutePath());
      //WHEN
      driver.findElement(By.cssSelector("button[type='submit']")).click();
      //THEN
      WebElement successMessage = driver.findElement(By.cssSelector("#validator-message"));
      assertTrue(successMessage.isDisplayed());

   }


}
