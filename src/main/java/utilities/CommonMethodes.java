package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonMethodes {

    WebDriver driver;

    public CommonMethodes(WebDriver driver)
    {
        this.driver = driver;
    }
    //=========Locators============
    By cartIcon = By.xpath("//button[@routerlink='/dashboard/cart']");
    //=====================methodes======================
    public void waitForTheElemnetToAppear(By element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public void goToCartPage()
    {
        driver.findElement(cartIcon).click();

    }
    public void waitForElemntToDisappear(By element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }
    public void scrollDown(By element)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }



}
