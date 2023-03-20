package basic;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase {
    protected WebDriver driver;

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterEach
    void teardown() {
        driver.quit();

    }

    public void takeScreenshot() {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(src, new File("src/main/resources/" + System.currentTimeMillis()+" screenshot.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

