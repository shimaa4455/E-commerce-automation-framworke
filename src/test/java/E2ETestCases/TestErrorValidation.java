package E2ETestCases;

import Test_Component.Browser;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductsPages;

import java.util.List;

public class TestErrorValidation extends Browser {
    @Test
    public void testLoginFailure()
    {
        loginPage.login("shimaa@gmail.com","swaNy4455@");
        System.out.println(loginPage.getErrorMessage());
        Assert.assertEquals(loginPage.getErrorMessage(),"Incorrect email or password.");

    }
    @Test
    public void productErrorValidation()
    {
        String countryName = "Albania";
        String productName = "ADIDAS ORIGINAL";
        ProductsPages productspage = loginPage.login("shimaa@gmail.com","swaNy4455");
        List<WebElement> products = productspage.getProducts();
        productspage.addProductToTheCard(productName);
        CartPage cartpage = productspage.goToCartPage();
        Boolean match = cartpage.verfiyProductDisplay("ADIDAS ORIGINAL1");
        Assert.assertFalse(match);
    }
}
