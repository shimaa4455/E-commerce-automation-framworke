package E2ETestCases;

import Test_Component.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;
import java.util.List;

public class TestSubmitOrder extends Browser {

    @Test
    public void testSubmitOrder() throws Exception {
        String countryName = "Albania";
        String productName = "ADIDAS ORIGINAL";
        //============================E2E test case==================
        //validate the idem added to the cart successfully.

        //LoginPage loginPage = lunchApplication();
        //ProductsPages productspage = new ProductsPages(driver);
        ProductsPages productspage = loginPage.login("shimaa@gmail.com","swaNy4455");
        List<WebElement> products = productspage.getProducts();
        productspage.addProductToTheCard(productName);
        CartPage cartpage = productspage.goToCartPage();
        Boolean match = cartpage.verfiyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartpage.checkOut();
        checkOutPage.chooseCountry(countryName);
        ConfirmationPage confirmationPage= checkOutPage.submitOrder();
        String ConfirmationMessage = confirmationPage.getConfirmMessage();
        Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase("Thankyou for the order."));




    }

}
