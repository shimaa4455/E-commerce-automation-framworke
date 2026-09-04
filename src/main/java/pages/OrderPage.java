package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.CommonMethodes;

import java.util.List;

public class OrderPage extends CommonMethodes {

    WebDriver driver;

    public OrderPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
    }
    //============locators================

    public By orderAtHistory =By.xpath("//table/tbody/tr/td[2]");


    //================methodes==================
    public boolean verifyOrderDisplay(String productName)
    {
        waitForTheElemnetToAppear(orderAtHistory);
        List<WebElement> orders= driver.findElements(orderAtHistory);
        System.out.println(orders.size());
        boolean match = orders.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
        System.out.println(match);
        return match;
    }
}
