package E2ETestCases;

import Test_Component.Browser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckOutPage;
import pages.ConfirmationPage;
import pages.LoginPage;
import pages.OrderPage;
import pages.ProductsPages;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;

public class TestSubmitOrder extends Browser {

    //String productName = "ADIDAS ORIGINAL";

    @Test(dataProvider = "getData", groups = {"purchase"})
    public void testSubmitOrder(String email, String password, String productName) throws Exception {
        String countryName = "Albania";

        // 🔹 Create LoginPage with thread-safe driver
        LoginPage loginPage = new LoginPage(getDriver());
        ProductsPages productspage = loginPage.login(email, password);

        List<WebElement> products = productspage.getProducts();
        productspage.addProductToTheCard(productName);

        CartPage cartpage = productspage.goToCartPage();
        Assert.assertTrue(cartpage.verfiyProductDisplay(productName)); // fix spelling in CartPage

        CheckOutPage checkOutPage = cartpage.checkOut();
        checkOutPage.chooseCountry(countryName);

        ConfirmationPage confirmationPage = checkOutPage.submitOrder();
        String confirmationMessage = confirmationPage.getConfirmMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
    }

    @Test
    public void testOrderHistory() {
        // 🔹 Fresh LoginPage for this test
        LoginPage loginPage = new LoginPage(getDriver());
        ProductsPages productspage = loginPage.login("shimaa@gmail.com", "swaNy4455");

        OrderPage orderPage = productspage.goToOrderPage();
        Boolean match = orderPage.verifyOrderDisplay("ADIDAS ORIGINAL");
        Assert.assertTrue(match);
    }

    @DataProvider(name = "getData")
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\TestData\\loginData.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}
