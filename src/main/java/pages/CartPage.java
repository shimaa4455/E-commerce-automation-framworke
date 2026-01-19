package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.CommonMethodes;

import java.util.List;

public class CartPage extends CommonMethodes {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //============locators================
    public By cartSection= By.cssSelector(".cartSection");
    public By cartProductslocator= By.xpath("//div[@class='cartSection']/h3");
    public By checkoutButton =By.cssSelector(".totalRow button");


    //================methodes==================
    public boolean verfiyProductDisplay(String productName)
    {
        waitForTheElemnetToAppear(cartSection);
        List <WebElement> cartProducts= driver.findElements(cartProductslocator);
        System.out.println(cartProducts.size());
        boolean match = cartProducts.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
        System.out.println(match);
        return match;
    }

    public void checkOut()
    {
        driver.findElement(checkoutButton).click();
    }
}
