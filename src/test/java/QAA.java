import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class QAA {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/aleksandra/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost/index_film.html");

        String expectedDirector = "John McTiernan";
        String expectedYear = "1988";

        WebElement movieSearch = driver.findElement(By.id("search_bar"));
        WebElement searchBtn = driver.findElement(By.id("get_api_res_btn"));
        WebElement movieYear = driver.findElement(By.id("result_year"));
        WebElement directorName = driver.findElement(By.id("result_director"));

        movieSearch.sendKeys("Die Hard");
        searchBtn.click();

        checkYear(driver, expectedYear);
        checkDirector(driver, expectedDirector);
    }

    public static void checkYear(WebDriver driver, String expectedYear) {
        WebElement yearInfo = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(elementToBeClickable(By.id("result_year")));
        System.out.println("Release " + yearInfo.getText());
        Assert.assertEquals(yearInfo.getText().substring(5), expectedYear);
    }

    public static void checkDirector(WebDriver driver, String expectedDirector) {
        WebElement directorData = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(elementToBeClickable(By.id("result_director")));
        System.out.println("Name " + directorData.getText());

        Assert.assertEquals(directorData.getText().substring(9),expectedDirector);
    }
}
