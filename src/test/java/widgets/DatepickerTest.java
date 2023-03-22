package widgets;

import basic.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DatepickerTest extends TestBase {



    @ParameterizedTest
    @CsvSource({"http://www.seleniumui.moderntester.pl/datepicker.php"})
    @DisplayName("Datepicker test")
    void shouldValidateDatepicker (String url) throws InterruptedException {
        driver.get(url);
        clickGivenDay(getCurrentDay());
        assertThat(driver.findElement(By.cssSelector("#datepicker")).getAttribute("value")).isEqualTo(getCurrentDate());
        Thread.sleep(5000);
        driver.navigate().refresh();
        selectDateByInput("04/01/2023");
        assertThat(driver.findElement(By.cssSelector("#datepicker")).getAttribute("value")).isEqualTo("04/01/2023");
        Thread.sleep(5000);
        selectDateByClicking("2023", "March", "19");
        Thread.sleep(5000);


    }

    public String getCurrentDay() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        return Integer.toString(todayInt);
    }

    public String getCurrentDate() {
        LocalDate currentdate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return currentdate.format(dateTimeFormatter);
    }


    public void clickGivenDay(String day) {
        WebElement datepicker = driver.findElement(By.cssSelector("#datepicker"));
        datepicker.click();
        List<WebElement> elementList = driver.findElements(By.cssSelector("tbody tr"));
        elementList.stream()
                .filter(element -> element.getText().contains(day))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void selectDateByInput (String inputValue) {
        WebElement datepicker = driver.findElement(By.cssSelector("#datepicker"));
        datepicker.click();
        datepicker.sendKeys(inputValue);
        datepicker.sendKeys(Keys.ENTER);

    }

    public void selectDateByClicking (String targetYear, String targetMonth, String targetDate) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement datepicker = driver.findElement(By.cssSelector("#datepicker"));
        datepicker.click();
        String targetMonthYearStr = targetMonth + " " + targetYear;
        WebElement buttonNext = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]"));
        WebElement buttonPrevious = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]"));
        String monthSelected = driver.findElement(By.cssSelector(".ui-datepicker-month")).getText();
        String yearSelected = driver.findElement(By.cssSelector(".ui-datepicker-year")).getText();
        String selectedMonthYearStr = monthSelected + " " + yearSelected;
        while (!selectedMonthYearStr.equals(targetMonthYearStr)) {
            if (Integer.parseInt(targetYear) < Integer.parseInt(yearSelected)) {
                try {
                    buttonPrevious.click();
                    wait.until(ExpectedConditions.elementToBeClickable(buttonPrevious));
                } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                    buttonPrevious.click();
                }
            } else {
                buttonNext.click();
                wait.until(ExpectedConditions.elementToBeClickable(buttonNext));
            }
        }
        WebElement elemDate = driver.findElement(By.xpath("//td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()='" + targetDate + "']"));
        elemDate.click();

    }



}
