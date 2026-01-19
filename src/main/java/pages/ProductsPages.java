package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonMethodes;

import java.util.List;

public class ProductsPages extends CommonMethodes {
    WebDriver driver;
    public ProductsPages(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //===========Locators=====================
    public By products = By.cssSelector(".mb-3");
    public By addToCard = By.cssSelector(".card-body button:last-of-type");
    public By toastMessage =By.cssSelector("#toast-container");
    public By shadow =By.cssSelector(".ngx-spinner-overlay");


    //=========methodes===================
    public  List<WebElement> getProducts()
    {
        waitForTheElemnetToAppear(products);
        System.out.println(driver.findElements(products).size());
        return driver.findElements(products);
    }
    private WebElement getProductByName(String productName)
    {
        WebElement prod = getProducts().stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }
    public void addProductToTheCard(String productName)
    {
        WebElement theProduct = getProductByName(productName);
        theProduct .findElement(addToCard).click();
        waitForTheElemnetToAppear(toastMessage);
        waitForElemntToDisappear(shadow);
    }


}
