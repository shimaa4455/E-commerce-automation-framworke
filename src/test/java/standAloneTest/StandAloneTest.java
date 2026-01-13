package standAloneTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args) {
        //=========test data===============================
        String countryName = "Albania";
        String productName = "ADIDAS ORIGINAL";
        String email ="shimaa@gmail.com";
        String password="swaNy4455";
        //============================E2E test case==================
        //validate the idem added to the cart successfully.
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List <WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
        System.out.println(products.size());
        WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        assert prod != null;
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection")));
        List <WebElement> cartProducts= driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
        System.out.println(cartProducts.size());
        boolean match = cartProducts.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
        System.out.println(match);
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();
        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),countryName).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        //driver.findElement(By.xpath("//input[@placeholder='Select Country']/following-sibling::section//button/span[normalize-space(text())='India']")).click();
        String xpath = String.format("//input[@placeholder='Select Country']/following-sibling::section//button/span[normalize-space(text())='%s']", countryName);
        driver.findElement(By.xpath(xpath)).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(".action__submit")));
        driver.findElement(By.cssSelector(".action__submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
        String message= driver.findElement(By.cssSelector(".hero-primary")).getText();
        System.out.println(message);
        Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));


    }
}
