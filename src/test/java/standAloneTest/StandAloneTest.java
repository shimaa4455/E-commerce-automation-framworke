package standAloneTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String productName = "ZARA COAT 3";
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("shimaa@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("swaNy4455");
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
    }
}
