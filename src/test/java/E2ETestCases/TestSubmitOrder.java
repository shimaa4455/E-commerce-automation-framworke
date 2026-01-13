package E2ETestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPages;

import java.time.Duration;
import java.util.List;

public class TestSubmitOrder {

    @Test
    public void testSubmitOrder(){
        String countryName = "Albania";
        String productName = "ADIDAS ORIGINAL";
        //============================E2E test case==================
        //validate the idem added to the cart successfully.
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        LoginPage loginpage = new LoginPage(driver);
        loginpage.goTo();
        loginpage.login("shimaa@gmail.com","swaNy4455");
        ProductsPages productspage = new ProductsPages(driver);
        List<WebElement> products = productspage.getProducts();
        productspage.addProductToTheCard(productName);

    }

}
