import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class QAA {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/aleksandra/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost/index_film.html");

        WebElement searchBtn = driver.findElement(By.id("get_api_res_btn"));

        searchBtn.click();

        checkNegative(driver);
    }

    public static void checkNegative(WebDriver driver) {
        WebElement yearInfo = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(elementToBeClickable(By.id("result_year")));
        WebElement directorData = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(elementToBeClickable(By.id("result_director")));
        WebElement titleData = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(elementToBeClickable(By.id("result_title")));

        ArrayList<String> resultStrings = new ArrayList<String>();
        resultStrings.add(yearInfo.getText());
        resultStrings.add(directorData.getText());
        resultStrings.add(titleData.getText());

        AtomicBoolean isAllValuesUndefined = new AtomicBoolean(true);

        resultStrings.forEach((String text) -> {
            if (!text.equals("undefined")) {
                isAllValuesUndefined.set(false);
            }
        });

        Assert.assertEquals(isAllValuesUndefined.get(), true);
    }
}
