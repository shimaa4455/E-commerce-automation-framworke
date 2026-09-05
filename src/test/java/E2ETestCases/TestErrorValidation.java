package E2ETestCases;

import Test_Component.Browser;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPages;

import java.util.List;

public class TestErrorValidation extends Browser {

    @Test
    public void testLoginFailure() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("shimaa@gmail.com","swaNy4455@");
        Assert.assertEquals(loginPage.getErrorMessage(),"Incorrect email or password.");
    }

    @Test
    public void productErrorValidation() {
        String productName = "ADIDAS ORIGINAL";
        LoginPage loginPage = new LoginPage(getDriver());
        ProductsPages productspage = loginPage.login("shimaa@gmail.com","swaNy4455");
        List<WebElement> products = productspage.getProducts();
        productspage.addProductToTheCard(productName);
        CartPage cartpage = productspage.goToCartPage();
        Boolean match = cartpage.verfiyProductDisplay("ADIDAS ORIGINAL1");
        Assert.assertFalse(match);
    }
}
